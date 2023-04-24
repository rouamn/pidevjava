/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;



import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javax.mail.Message;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import static javax.mail.Transport.send;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import Utils.MyDB;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entities.Events;
import entities.Reclamation;
import java.net.Authenticator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 *
 * @author LENOVO
 */
public class ReclamationService implements IService <Reclamation> {

    Statement Ste;
    Connection con;
   

   
 static Session sesh;
        static Properties prop = new Properties();
   

    public ReclamationService() {
           con = MyDB.getInstance().getcon();
   
    
    }
    
    public int getExistingCount(String email) {
    int count = 0;
    String countReq = "SELECT COUNT(*) FROM `gestionsalledesport`.`reclamation` WHERE `email_reclamation`='" + email + "'";
    try {
        Ste = con.createStatement();
        ResultSet rs = Ste.executeQuery(countReq);
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
    }
    return count;
}

  @Override
public void Ajouter(Reclamation r) {
    String email = r.getEmail_reclamation();
    int existingCount = 0;
    
    // Vérifier le nombre de réclamations existantes pour l'adresse e-mail donnée
    String countReq = "SELECT COUNT(*) FROM `gestionsalledesport`.`reclamation` WHERE `email_reclamation`='" + email + "'";
    try {
        Ste = con.createStatement();
        ResultSet rs = Ste.executeQuery(countReq);
        if (rs.next()) {
            existingCount = rs.getInt(1);
        }
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
    }

    // Si le nombre de réclamations existantes pour cette adresse e-mail est supérieur ou égal à 3, ne pas insérer la nouvelle
    if (existingCount >= 3) {
        System.out.println("Impossible d'ajouter plus de 3 réclamations pour l'adresse e-mail: " + email);
    } else {
        // Insérer la nouvelle réclamation dans la table reclamation
        String req = "INSERT INTO `gestionsalledesport`.`reclamation` (`email_reclamation`, `objet_reclamation`, `contenue_reclamation`) VALUES ('" + r.getEmail_reclamation() + "','" + r.getObjet_reclamation() + "','" + r.getContenue_reclamation() + "')";

        try {
            Ste = con.createStatement();
            Ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Err" + ex.getLocalizedMessage());
        }
        System.out.println("Réclamation ajoutée avec succès.");
    }
}
   


  @Override
public void suprrimer(Reclamation r) {
    String req = "DELETE FROM `gestionsalledesport`.`reclamation` WHERE  `id`="+r.getIdReclamation();
    try {
        Ste=con.createStatement();
        Ste.executeUpdate(req);
        System.out.println("La Reclamation avec l'id = "+r.getIdReclamation()+" a été supprimer avec succès...");
    } catch (SQLException ex) {
        System.out.println("Err"+ex.getLocalizedMessage());
    }
}
@Override
public void modifier(Reclamation r) {
    String req = "UPDATE gestionsalledesport.reclamation SET email_reclamation='" + r.getEmail_reclamation() + "', objet_reclamation='" + r.getObjet_reclamation() + "', contenue_reclamation='" + r.getContenue_reclamation() + "' WHERE id=" + r.getIdReclamation();

    try {
        Ste = con.createStatement();
        Ste.executeUpdate(req);
        System.out.println("Reclamation modifiée avec succès...");
    } catch (SQLException ex) {
        System.out.println("Erreur: " + ex.getLocalizedMessage());
    }
}

   @Override
public ArrayList<Reclamation> afficher() {
    ArrayList<Reclamation> pers = new ArrayList<>();
    try {
        Ste = con.createStatement();

        String req = "SELECT * FROM `gestionsalledesport`.`reclamation` ";
        ResultSet res = Ste.executeQuery(req);
        while (res.next()) {
            int idReclamation = res.getInt(1);
            String email_reclamation = res.getString("email_reclamation");
            String objet_reclamation = res.getString("objet_reclamation");
            String contenue_reclamation = res.getString("contenue_reclamation");
           

            Reclamation rec = new Reclamation(idReclamation, email_reclamation, objet_reclamation, contenue_reclamation);
            pers.add(rec);
        }
    } catch (SQLException ex) {
        System.out.println("err" + ex.getMessage());
    }
    return pers;
}


public List<Reclamation> rechercherAvancee(String email) {
    List<Reclamation> reclamations = new ArrayList<>();

    String req = "SELECT * FROM `gestionsalledesport`.`reclamation` WHERE email_reclamation = '" + email + "'";

 
    try {
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(req);

        while (resultSet.next()) {
            Reclamation reclamation = new Reclamation();
            reclamation.setIdReclamation(resultSet.getInt("id"));
            reclamation.setEmail_reclamation(resultSet.getString("email_reclamation"));
            reclamation.setObjet_reclamation(resultSet.getString("objet_reclamation"));
            reclamation.setContenue_reclamation(resultSet.getString("contenue_reclamation"));
            reclamations.add(reclamation);
        }
        statement.close();
    } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
    }
    return reclamations;
}


 


public void envoyerEmailAdminNouvelleReclamation(Reclamation reclamation) throws MessagingException, javax.mail.MessagingException {
    String sujet = "Nouvelle réclamation ajoutée";
    String corps = "Une nouvelle réclamation a été ajoutée avec l'ID " + reclamation.getIdReclamation()
            + " pour l'adresse e-mail " + reclamation.getEmail_reclamation();

    String destinataire = "zaabarsalsabil@mail.com"; // l'adresse e-mail de l'administrateur
    String expediteur = "salsabil.zaabar@esprit.tn"; // l'adresse e-mail de l'expéditeur
     String serveurSMTP = "sandbox.smtp.mailtrap.io";
    int portSMTP = 2525;
    String utilisateurSMTP = "9715683c01a3e1";
    String motDePasseSMTP = "f8753bd9293ecc";

    // Créer une propriété avec les informations de connexion SMTP
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", serveurSMTP);
    props.put("mail.smtp.port", portSMTP);

    // Créer une session de courrier avec les informations de connexion SMTP
    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(utilisateurSMTP, motDePasseSMTP);
                }
            });

    // Créer un objet Message avec les informations de l'e-mail
    Message message = new MimeMessage(session);
    message.setFrom(new InternetAddress(expediteur));
    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
    message.setSubject(sujet);
    message.setText(corps);

    // Envoyer le message
    Transport.send(message);
}

}

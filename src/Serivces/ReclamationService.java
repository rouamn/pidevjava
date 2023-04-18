/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entities.Events;
import entities.Reclamation;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import sun.rmi.transport.Transport;

/**
 *
 * @author LENOVO
 */
public class ReclamationService implements IService <Reclamation> {

    Statement Ste;
    Connection con;
   

   

   

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
    // Check the number of existing reclamations for the given email address
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

    // If there are already 3 reclamations, do not insert the new one
    if (existingCount >= 3) {
        System.out.println("Cannot add more than 3 reclamations for email: " + email);
        
    }else{
String req = "INSERT INTO `gestionsalledesport`.`reclamation` (`email_reclamation`, `objet_reclamation`, `contenue_reclamation`) VALUES ('"+r.getEmail_reclamation() +"','"+r.getObjet_reclamation()+"','"+r.getContenue_reclamation()+"')";

        try {
            Ste=con.createStatement();
            Ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Err"+ex.getLocalizedMessage());
        }
        
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
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `gestionsalledesport`.`reclamation`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int idReclamation = res.getInt(1);
                String email_reclamation = res.getString("email_reclamation");
                String objet_reclamation = res.getString("objet_reclamation");
                String contenue_reclamation= res.getString("contenue_reclamation");
                
                
                
                Reclamation rec =new Reclamation(idReclamation , email_reclamation ,  objet_reclamation, contenue_reclamation);
                pers.add(rec);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
      
    }


   
}
   

 

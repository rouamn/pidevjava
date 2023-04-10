/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import entities.Events;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    @Override
    public void Ajouter(Reclamation r) {
String req = "INSERT INTO `gestionsalledesport`.`reclamation` (`email_reclamation`, `objet_reclamation`, `contenue_reclamation`) VALUES ('"+r.getEmail_reclamation() +"','"+r.getObjet_reclamation()+"','"+r.getContenue_reclamation()+"')";

        try {
            Ste=con.createStatement();
            Ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Err"+ex.getLocalizedMessage());
        }
        
    }

  @Override
public void suprrimer(Reclamation r) {
    String req = "DELETE FROM `gestionsalledesport`.`reclamation` WHERE  `id`="+r.getIdReclamation();
    try {
        Ste=con.createStatement();
        Ste.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("Err"+ex.getLocalizedMessage());
    }
}


    @Override
    public void modifier(Reclamation r) {
      String req = "UPDATE `gestionsalledesport`.`reclamation` SET `email_reclamation`='" + r.getEmail_reclamation() + "', `objet_reclamation`='" + r.getObjet_reclamation() + "', `contenue_reclamation`='" + r.getContenue_reclamation();

    try {
        Ste = con.createStatement();
        Ste.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
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
   

 

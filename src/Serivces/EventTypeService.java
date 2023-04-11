/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import entities.Events;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.event.EventType;

/**
 *
 * @author DELL
 */
public class EventTypeService implements IService<Type> {
    Statement Ste;
    Connection con;

    public EventTypeService() {
        con = MyDB.getInstance().getcon();
    }

    @Override
    public void Ajouter(Type t) {
       String req = "INSERT INTO `gestionsalledesport`.`type_evenement` (`type_name` ) VALUES ('"+t.getNameType()+"')";

        try {
            Ste=con.createStatement();
            Ste.executeUpdate(req);
            System.out.println("event ajouter avec succès...");
        } catch (SQLException ex) {
            System.out.println("Err"+ex.getLocalizedMessage());
        }
        
    }

    @Override
    public void suprrimer(Type t) {
       String req = "DELETE FROM `gestionsalledesport`.`type_evenement` WHERE  `id`="+t.getIdType();
    try {
        Ste=con.createStatement();
        Ste.executeUpdate(req);
           System.out.println("event supprimer avec succès...");
    } catch (SQLException ex) {
        System.out.println("Err"+ex.getLocalizedMessage());
    }
    }

    @Override
    public void modifier(Type t) {
         String req = "UPDATE `gestionsalledesport`.`type_evenement` SET `titre_event`='" + t.nameType  + "' WHERE `id`=" + t.getIdType() ;

    try {
        Ste = con.createStatement();
        Ste.executeUpdate(req);
        System.out.println("event modifier  avec succès...");
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
    }
    }

    @Override
    public ArrayList<Type> afficher() {
       ArrayList<Type> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `gestionsalledesport`.`type_evenement`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int idType = res.getInt(1);
                String typeName = res.getString("type_name");
              
                
              
                Type e =new Type(idType,typeName);
                pers.add(e);
             
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
      
    }}
    


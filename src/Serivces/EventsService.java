/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import entities.Events;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class EventsService  implements IService<Events>{
    Statement Ste;
    Connection con;

    public EventsService() {
           con = MyDB.getInstance().getcon();
   
    
    }
  
    
    @Override
    public void Ajouter(Events t) {
String req = "INSERT INTO `gestionsalledesport`.`evenement` (`titre_event`, `date_debut_event`, `date_fin_event`, `place_event`, `description_event`) VALUES ('"+t.getTitreEvent()+"','"+t.getDateDebut()+"','"+t.getDateFin()+"','"+t.getPlaceEvent()+"','"+t.getDescriptionEvent()+"')";

        try {
            Ste=con.createStatement();
            Ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Err"+ex.getLocalizedMessage());
        }
        
    }

  @Override
public void suprrimer(Events t) {
    String req = "DELETE FROM `gestionsalledesport`.`evenement` WHERE  `id`="+t.getIdEvent();
    try {
        Ste=con.createStatement();
        Ste.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("Err"+ex.getLocalizedMessage());
    }
}


    @Override
    public void modifier(Events t) {
      String req = "UPDATE `gestionsalledesport`.`evenement` SET `titre_event`='" + t.getTitreEvent() + "', `date_debut_event`='" + t.getDateDebut() + "', `date_fin_event`='" + t.getDateFin() + "', `place_event`='" + t.getPlaceEvent() + "', `description_event`='" + t.getDescriptionEvent() + "' WHERE `id`=" + t.getIdEvent();

    try {
        Ste = con.createStatement();
        Ste.executeUpdate(req);
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
    }
    }

    @Override
    public ArrayList<Events> afficher() {
       ArrayList<Events> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `gestionsalledesport`.`evenement`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int idEvent = res.getInt(1);
                String TitreEvent = res.getString("titre_event");
                String DateDebut = res.getString("date_debut_event");
                String DateFin= res.getString("date_fin_event");
                String PlaceEvent = res.getString("place_event");
                String DescriptionEvent = res.getString("description_event");
                
                
                Events e =new Events(idEvent , TitreEvent ,  DateDebut, DateFin ,PlaceEvent,DescriptionEvent);
                pers.add(e);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
      
    }

    }
    


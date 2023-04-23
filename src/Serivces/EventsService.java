/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import entities.Events;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.image.Image;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author DELL
 */
public class EventsService implements IService<Events> {

    Statement Ste;
    Connection con;

    public EventsService() {
        con = MyDB.getInstance().getcon();

    }

    /*@Override
public void Ajouter(Events t) {
    try {
        // Insert the new event into the evenement table and retrieve the generated id value
        String req1 = "INSERT INTO `gestionsalledesport`.`evenement` (`titre_event`, `date_debut_event`, `date_fin_event`, `place_event`, `description_event`, `image_evenement`) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(req1, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, t.getTitreEvent());
        ps.setDate(2, new java.sql.Date(t.getDateDebut().getTime()));
        ps.setDate(3, new java.sql.Date(t.getDateFin().getTime()));
        ps.setString(4, t.getPlaceEvent());
        ps.setString(5, t.getDescriptionEvent());
        if (t.getImage() != null) {
            ps.setString(6, t.getImage());
        } else {
            ps.setNull(6, java.sql.Types.VARCHAR);
        }
     
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            int eventId = rs.getInt(1);

            System.out.println("Event added successfully.");
        }
    } catch (SQLException ex) {
        System.out.println("Error while adding event: " + ex.getLocalizedMessage());
    }

}*/

 @Override
public void Ajouter(Events t) {
    try {
        // Retrieve the id of the type of event from the type_evenement table based on the typeName value
        String req = "SELECT `id` FROM `gestionsalledesport`.`type_evenement` WHERE `type_name` = ?";
        PreparedStatement ps = con.prepareStatement(req);
        ps.setString(1, t.getTypeName());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int typeId = rs.getInt("id");

            // Insert the new event into the evenement table
            String req1 = "INSERT INTO `gestionsalledesport`.`evenement` (`titre_event`, `date_debut_event`, `date_fin_event`, `place_event`, `description_event`, `image_evenement`, `type_id`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps1 = con.prepareStatement(req1);
            ps1.setString(1, t.getTitreEvent());
            ps1.setDate(2, new java.sql.Date(t.getDateDebut().getTime()));
            ps1.setDate(3, new java.sql.Date(t.getDateFin().getTime()));
            ps1.setString(4, t.getPlaceEvent());
            ps1.setString(5, t.getDescriptionEvent());
            if (t.getImage() != null) {
                ps1.setString(6, t.getImage());
            } else {
                ps1.setNull(6, java.sql.Types.VARCHAR);
            }
            ps1.setInt(7, typeId);

            ps1.executeUpdate();

            System.out.println("Event added successfully. Type of event: " + t.getTypeName());
        } else {
            System.out.println("Error while adding event: Type of event not found.");
        }
    } catch (SQLException ex) {
        System.out.println("Error while adding event: " + ex.getLocalizedMessage());
    }
}
    @Override
    public void suprrimer(Events t) {
        String req = "DELETE FROM `gestionsalledesport`.`evenement` WHERE  `id`=" + t.getIdEvent();
        try {
            Ste = con.createStatement();
            Ste.executeUpdate(req);
            System.out.println("event supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println("Err" + ex.getLocalizedMessage());
        }
    }

 /*   @Override
    public void modifier(Events t) {
        String req = "UPDATE `gestionsalledesport`.`evenement` SET `titre_event`='" + t.getTitreEvent() + "', `date_debut_event`='" + t.getDateDebut() + "', `date_fin_event`='" + t.getDateFin() + "', `place_event`='" + t.getPlaceEvent() + "', `description_event`='" + t.getDescriptionEvent() + "' WHERE `id`=" + t.getIdEvent();

        try {
            Ste = con.createStatement();
            Ste.executeUpdate(req);
            System.out.println("event modifier  avec succès...");
        } catch (SQLException ex) {
            System.out.println("Err" + ex.getLocalizedMessage());
        }
    }
*/
 @Override
public void modifier(Events t) {
    try {
        // Retrieve the id of the type of event from the type_evenement table based on the typeName value
        String req1 = "SELECT `id` FROM `gestionsalledesport`.`type_evenement` WHERE `type_name` = ?";
        PreparedStatement ps1 = con.prepareStatement(req1);
        ps1.setString(1, t.getTypeName());
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            int typeId = rs.getInt("id");

            // Update the event in the evenement table
            String req2 = "UPDATE `gestionsalledesport`.`evenement` SET `titre_event`=?, `date_debut_event`=?, `date_fin_event`=?, `place_event`=?, `description_event`=?, `image_evenement`=?, `type_id`=? WHERE `id`=?";
            PreparedStatement ps2 = con.prepareStatement(req2);
            ps2.setString(1, t.getTitreEvent());
            ps2.setDate(2, new java.sql.Date(t.getDateDebut().getTime()));
            ps2.setDate(3, new java.sql.Date(t.getDateFin().getTime()));
            ps2.setString(4, t.getPlaceEvent());
            ps2.setString(5, t.getDescriptionEvent());
            if (t.getImage() != null) {
                ps2.setString(6, t.getImage());
            } else {
                ps2.setNull(6, java.sql.Types.VARCHAR);
            }
            ps2.setInt(7, typeId);
            ps2.setInt(8, t.getIdEvent());

            ps2.executeUpdate();

            System.out.println("Event updated successfully. ID: " + t.getIdEvent());
        } else {
            System.out.println("Error while updating event: Type of event not found.");
        }
    } catch (SQLException ex) {
        System.out.println("Error while updating event: " + ex.getLocalizedMessage());
    }
}
    /*@Override
    public ArrayList<Events> afficher() {
        ArrayList<Events> pers = new ArrayList<>();
        try {
            Ste = con.createStatement();

            String req = "SELECT * FROM `gestionsalledesport`.`evenement`";
            ResultSet res = Ste.executeQuery(req);
            while (res.next()) {
                int idEvent = res.getInt(1);
                String TitreEvent = res.getString("titre_event");
                Date DateDebut = res.getDate("date_debut_event");
                Date DateFin = res.getDate("date_fin_event");
                String PlaceEvent = res.getString("place_event");
                String DescriptionEvent = res.getString("description_event");
                String image = res.getString("image_evenement");
               ///  String nameT = res.getString("type_name");

                Events e = new Events(idEvent, TitreEvent, DateDebut, DateFin, PlaceEvent, DescriptionEvent, image);
                pers.add(e);

            }

        } catch (SQLException ex) {
            System.out.println("err" + ex.getMessage());
        }
        return pers;

    }
*/
    @Override
public ArrayList<Events> afficher() {
    ArrayList<Events> pers = new ArrayList<>();
    try {
        Ste = con.createStatement();

        String req = "SELECT * FROM `gestionsalledesport`.`evenement` e JOIN `gestionsalledesport`.`type_evenement` t ON e.`type_id` = t.`id`";
        ResultSet res = Ste.executeQuery(req);
        while (res.next()) {
            int idEvent = res.getInt(1);
            String titreEvent = res.getString("titre_event");
            Date dateDebut = res.getDate("date_debut_event");
            Date dateFin = res.getDate("date_fin_event");
            String placeEvent = res.getString("place_event");
            String descriptionEvent = res.getString("description_event");
            String image = res.getString("image_evenement");
            String typeName = res.getString("type_name");

            Events e = new Events(idEvent, titreEvent, dateDebut, dateFin, placeEvent, descriptionEvent, image);
            e.setTypeName(typeName);
            pers.add(e);
        }

    } catch (SQLException ex) {
        System.out.println("err" + ex.getMessage());
    }
    return pers;

}

  public List<String> getEventTypes() {
    List<String> types = new ArrayList<>();
    try {
        String req = "SELECT `type_name` FROM `gestionsalledesport`.`type_evenement`";
        PreparedStatement ps = con.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String typeName = rs.getString("type_name");
            types.add(typeName);
        }
    } catch (SQLException ex) {
        System.out.println("Error while getting event types: " + ex.getLocalizedMessage());
    }
    return types;
}

   public Events findById(int eventId) {
    Events event = null;
    try {
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM evenement e JOIN type_evenement t ON e.type_id = t.id WHERE e.id = ?");
        stmt.setInt(1, eventId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String titreEvent = rs.getString("titre_event");
            Date dateDebut = rs.getDate("date_debut_event");
            Date dateFin = rs.getDate("date_fin_event");
            String placeEvent = rs.getString("place_event");
            String descriptionEvent = rs.getString("description_event");
            String image = rs.getString("image_evenement");
            String typeName = rs.getString("type_name");

            event = new Events(eventId, titreEvent, dateDebut, dateFin, placeEvent, descriptionEvent, image);
            event.setTypeName(typeName);
        }
    } catch (SQLException ex) {
        System.out.println("err" + ex.getMessage());
    }
    return event;
}
}

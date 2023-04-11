/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.DatePicker;

/**
 *
 * @author DELL
 */
public class Events {
    int idEvent ;
    String titreEvent;
    Date dateDebut ,dateFin;
    String placeEvent, DescriptionEvent, image;
    String dateFinStr ,dateDebutStr;
    int idType ;

    public Events(String titreEvent, Date dateDebut, Date dateFin, String placeEvent, String DescriptionEvent ,int idType) {
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
     
        this.idType = idType;
    }

 

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }
     
java.sql.Date  DateDebut ,  DateFin;
    public Events() {
    }

    public Events(int idEvent, String titreEvent, Date dateDebut, Date dateFin, String placeEvent, String DescriptionEvent, String image) {
        this.idEvent = idEvent;
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.image = image;
    }

    public Events(String titreEvent, Date dateDebut, Date dateFin, String placeEvent, String DescriptionEvent, String image) {
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
        this.image = image;
    }

    public Events(String titreEvent, Date dateDebut, Date dateFin, String placeEvent, String DescriptionEvent) {
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    public Events(String titreEvent, DatePicker tfDateDebut, DatePicker tfDateFin, String  placeEvent, String DescriptionEvent) {
         this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    public Events(String titreEvent, String dateDebutStr, String dateFinStr, String placeEvent, String  DescriptionEvent) {
        this.titreEvent = titreEvent;
        this.dateDebutStr = dateDebutStr;
        this.dateFinStr = dateFinStr;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    public Events(int idEvent, String TitreEvent, java.sql.Date DateDebut,java.sql.Date DateFin, String PlaceEvent, String DescriptionEvent ) {
         this.titreEvent = titreEvent;
        this.DateDebut =DateDebut;
        this.DateFin = DateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    public Events(int idEvent, String TitreEvent, String DateDebut, String DateFin, String PlaceEvent, String DescriptionEvent, String image) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

  

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitreEvent() {
        return titreEvent;
    }

    public void setTitreEvent(String titreEvent) {
        this.titreEvent = titreEvent;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getPlaceEvent() {
        return placeEvent;
    }

    public void setPlaceEvent(String placeEvent) {
        this.placeEvent = placeEvent;
    }

    public String getDescriptionEvent() {
        return DescriptionEvent;
    }

    public void setDescriptionEvent(String DescriptionEvent) {
        this.DescriptionEvent = DescriptionEvent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Events{" + "idEvent=" + idEvent + ", titreEvent=" + titreEvent + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", placeEvent=" + placeEvent + ", DescriptionEvent=" + DescriptionEvent + ", image=" + image + '}';
    }

    public String getDateFinStr() {
        return dateFinStr;
    }

    public void setDateFinStr(String dateFinStr) {
        this.dateFinStr = dateFinStr;
    }

    public String getDateDebutStr() {
        return dateDebutStr;
    }

    public void setDateDebutStr(String dateDebutStr) {
        this.dateDebutStr = dateDebutStr;
    }

   
   

   

}

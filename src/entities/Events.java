/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author DELL
 */
public class Events {
    int idEvent ;
    String titreEvent, dateDebut ,dateFin,placeEvent, DescriptionEvent;
  

    public Events() {
    }

    public Events(int idEvent, String titreEvent, String dateDebut, String dateFin, String placeEvent, String DescriptionEvent) {
        this.idEvent = idEvent;
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
    }

    public Events(String titreEvent, String dateDebut, String dateFin, String placeEvent, String DescriptionEvent) {
        this.titreEvent = titreEvent;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.placeEvent = placeEvent;
        this.DescriptionEvent = DescriptionEvent;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
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

    @Override
    public String toString() {
        return "Events{" + "idEvent=" + idEvent + ", titreEvent=" + titreEvent + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", placeEvent=" + placeEvent + ", DescriptionEvent=" + DescriptionEvent + '}';
    }

   

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author DELL
 */
public class EventType {
     int idTypeEvent;
     String nameType;

    public EventType(int idTypeEvent, String nameType) {
        this.idTypeEvent = idTypeEvent;
        this.nameType = nameType;
    }

    public EventType() {
    }

    public EventType(String nameType) {
        this.nameType = nameType;
    }

    public int getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(int idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    @Override
    public String toString() {
        return "EventType{" + "idTypeEvent=" + idTypeEvent + ", nameType=" + nameType + '}';
    }
     
}

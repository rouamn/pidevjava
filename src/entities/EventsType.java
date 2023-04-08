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
public class EventsType {
    int idTypeEvent;
    String nameType;

    public EventsType() {
    }

    public EventsType(String nameType) {
        this.nameType = nameType;
    }

    public EventsType(int idTypeEvent, String nameType) {
        this.idTypeEvent = idTypeEvent;
        this.nameType = nameType;
    }
    
    
}

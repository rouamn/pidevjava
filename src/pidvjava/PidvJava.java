/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidvjava;

import Serivces.EventsService;
import Utils.MyDB;
import entities.Events;

/**
 *
 * @author DELL
 */
public class PidvJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
            
        MyDB.getInstance();
        
        Events e1 =new  Events( "evenement sportif ", "2023-03-02 00:00:00", "2023-03-02 00:00:00", "sousse","hhhh");
        
        Events e2 = new Events("nouveau titre", "2023-04-01 00:00:00", "2023-04-01 00:00:00", "tunis", "nouvelle description");
      Events e3 =new  Events("evenement2 ", "2023-03-02 00:00:00", "2023-03-02 00:00:00", "sfax","bj");


      EventsService sp = new  EventsService();
     /// sp.Ajouter(e1);
     // sp.Ajouter(e2);
   //  sp.Ajouter(e3);
      System.out.println(sp.afficher());
      //modifier 
    // e2.setIdEvent(e1.getIdEvent()); // set the ID of the new event to the ID of the old event
     // sp.modifier(e2);
      //supprimer
      

       sp.suprrimer(e3);
        
        System.out.println(sp.afficher());
    }
    
}

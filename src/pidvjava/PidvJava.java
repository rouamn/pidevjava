/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidvjava;

import Serivces.EventsService;
import Serivces.ReclamationService;
import Utils.MyDB;
import entities.Events;
import entities.Reclamation;

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
        
       /* Events e1 =new  Events( "evenement sportif ", "2023-03-02 00:00:00", "2023-03-02 00:00:00", "sousse","hhhh");
        
        Events e2 = new Events("nouveau titre", "2023-04-01 00:00:00", "2023-04-01 00:00:00", "tunis", "nouvelle description");
      //Events e3 =new  Events(85,"evenement2 ", "2023-03-02 00:00:00", "2023-03-02 00:00:00", "sfax","bj);
*/

      EventsService sp = new  EventsService();
     /// sp.Ajouter(e1);
     // sp.Ajouter(e2);
   //  sp.Ajouter(e3);
   //   System.out.println(sp.afficher());
      //modifier 

   //  sp.modifier(e2);
      //supprimer
      

      // sp.suprrimer(e2);
        
      //  System.out.println(sp.afficher());
        
    
        
        Reclamation rec1 =new  Reclamation("ss@gmail.com ", "caoch", " le coach ali n'est pas pro");
        ReclamationService recsv = new ReclamationService();
        recsv.Ajouter(rec1);
         System.out.println(recsv.afficher());
         
          recsv.modifier(rec1);
    }
    
}

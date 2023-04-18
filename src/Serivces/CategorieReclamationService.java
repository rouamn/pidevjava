/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serivces;

import Utils.MyDB;
import entities.CategorieReclamation;
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
public class CategorieReclamationService implements IService <CategorieReclamation>{

    Statement Ste;
    Connection con;

    public CategorieReclamationService() {
           con = MyDB.getInstance().getcon();
   
    
    }

    @Override
    public void Ajouter(CategorieReclamation t) {
        String req = "INSERT INTO `gestionsalledesport`.`categorie_reclamation` (`libelle`) VALUES ('"+t.getLibelle() +"')";

        try {
            Ste=con.createStatement();
            Ste.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Err"+ex.getLocalizedMessage());
        }
          }

    @Override
    public void suprrimer(CategorieReclamation t) {
         String req = "DELETE FROM `gestionsalledesport`.`categorie_reclamation` WHERE  `id`="+t.getIdCategorieReclamation();
    try {
        Ste=con.createStatement();
        Ste.executeUpdate(req);
        System.out.println("La Reclamation avec l'id = "+t.getIdCategorieReclamation()+" a été supprimer avec succès...");
    } catch (SQLException ex) {
        System.out.println("Err"+ex.getLocalizedMessage());
    }
         }

    @Override
    public void modifier(CategorieReclamation t) {
     String req = "UPDATE gestionsalledesport.`categorie_reclamation` SET libelle`='" + t.getLibelle() + "' WHERE id`=" + t.getIdCategorieReclamation();

    try {
        Ste = con.createStatement();
        Ste.executeUpdate(req);
        System.out.println("catrec modifier  avec succès...");
    } catch (SQLException ex) {
        System.out.println("Err" + ex.getLocalizedMessage());
    }  }

    @Override
    public ArrayList<CategorieReclamation> afficher() {
      ArrayList<CategorieReclamation> pers = new ArrayList<>();
        try {
            Ste= con.createStatement();
       
        String req = "SELECT * FROM `gestionsalledesport`.`categorie_reclamation`";
            ResultSet res= Ste.executeQuery(req);
            while(res.next()){
                int idCategorieReclamation = res.getInt(1);
                String libelle = res.getString("libelle");
                
                
                
                
                CategorieReclamation catrec =new CategorieReclamation(idCategorieReclamation,libelle);
                pers.add(catrec);
            }
            
            
         } catch (SQLException ex) {
             System.out.println("err"+ex.getMessage());
        }
        return pers;
    }
    
}

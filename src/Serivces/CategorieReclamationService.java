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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void suprrimer(CategorieReclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(CategorieReclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<CategorieReclamation> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DELL
 */
public class MyDB {
      String url = "jdbc:mysql://localhost:3306/gestionsalledesport";
    String login = "root";
    String pwd = "";
    
    Connection con;
    
    public static MyDB instance;
    private MyDB() {
        try {
            System.out.println("connexion en cours");
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            
            System.out.println("Err "+ ex.getMessage());
        }
        
        
    }
    
    public static MyDB getInstance(){
        if(instance == null ){
            instance = new MyDB();
        }
        return instance;
        
    }
    
    public Connection getcon(){
        return con;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;
import Serivces.ReclamationService;
import Utils.MyDB;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;

import entities.Reclamation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.net.URL;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import javax.swing.JDialog;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatistiqueController implements Initializable {
   @FXML
    private PieChart rec_stat;

    private Statement st;
    private ResultSet rs;
    private Connection cnx;
        ObservableList<PieChart.Data>data=FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      cnx = MyDB.getInstance().getcon();
    stat();
     
    }    
      
    
    // Méthode appelée lorsque le bouton de retour est cliqué
    @FXML
    public void btnretour(ActionEvent event) throws IOException {
        // code pour retourner à la page précédente
         Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    
    
    // Méthode pour récupérer les données de la base de données et les afficher dans le graphique
   
     private void stat() {
          try{
           // String query ="select COUNT(*),reservation_voyage.travel_class from voyage INNER JOIN reservation_voyage on reservation_voyage.voyage_id =voyage.id GROUP BY travel_class;";
           String req ="SELECT objet_reclamation, COUNT(*) AS nombre FROM `gestionsalledesport`.`reclamation` GROUP BY objet_reclamation";
           //String query ="select COUNT(*),prix from voyage GROUP BY valabilite;";
            PreparedStatement PreparedStatement = cnx.prepareStatement(req);
           Statement statement = cnx.createStatement();
           ResultSet rs = statement.executeQuery(req);
             rs = PreparedStatement.executeQuery();
             while (rs.next()){               
               data.add(new PieChart.Data(rs.getString("objet_reclamation"),rs.getInt("nombre")));
            }  
             
        } catch (SQLException ex) {
              System.out.println(ex.getMessage());
        }
        
         rec_stat.setTitle("**Statistique des Reclamtions**");
        rec_stat.setLegendSide(Side.LEFT);
        rec_stat.setData(data);
    }
    

}



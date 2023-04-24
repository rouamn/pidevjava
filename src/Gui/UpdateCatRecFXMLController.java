/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.CategorieReclamationService;
import Serivces.ReclamationService;
import entities.CategorieReclamation;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UpdateCatRecFXMLController implements Initializable {
 @FXML
    private TextField textFieldlibelle;
   
    
  @FXML private TableView<CategorieReclamation> tableCat;
@FXML private TableColumn<CategorieReclamation, Integer> idCatRec;
@FXML private TableColumn<CategorieReclamation, String> libelleCatRec;


String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation rec = null ;
    
     ObservableList<CategorieReclamation>  CatReclamationList = FXCollections.observableArrayList();
    /**
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
     private void btnBackCat(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListCatRecFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
      public static CategorieReclamation staticCatRec;
      @FXML
private void btnUpdateCatReclam(ActionEvent event) throws IOException, SQLException {
   

    // Check if all input fields are filled
    if ( textFieldlibelle.getText().isEmpty()) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Erreur");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Veuillez remplir tous les champs.");
        errorAlert.showAndWait();
        return;
    }  else{

    
 CategorieReclamationService csp = new CategorieReclamationService();
    
   CategorieReclamation cr = new CategorieReclamation();
   
    cr.setLibelle(textFieldlibelle.getText());
    


    // Update the 'staticEvent' object with the new values
    staticCatRec.setLibelle(cr.getLibelle());
  

    // Debug prints to check the 'staticRect' object
    System.out.println("staticRec: " +staticCatRec );

    // Update the record in the database
    csp.modifier(staticCatRec);

    // Debug prints to check the SQL query
    //System.out.println("SQL query: " + eventService.getModifierQuery(staticRec));

    // Close the window
    ((Node) event.getSource()).getScene().getWindow().hide();

    // Refresh the table view
    }
}

public void setCatReclam(CategorieReclamation cr) {
       staticCatRec = cr;
        this.textFieldlibelle.setText(staticCatRec.getLibelle());
       
             
    }







}

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
import java.util.ResourceBundle;
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
public class AddCatRecFXMLController implements Initializable {
@FXML
    private TextField libelle;
   
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void btnAddCat(ActionEvent event) {
         String libelle = this.libelle.getText();
  
        if (libelle.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            CategorieReclamation crec1 = new CategorieReclamation(libelle);
             CategorieReclamationService sp = new CategorieReclamationService();
             sp.Ajouter(crec1);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Succès");
              alert.setHeaderText(null);
              alert.setContentText("Votre categorie est ajouté avec succée!");
              alert.showAndWait();
       }
}
    
    
     @FXML
     private void btnBack(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("LiscattRecFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
}

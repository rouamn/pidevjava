/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.ReclamationService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjouterReclamationFXMLController implements Initializable {
    
     @FXML
    private TextField email;
    @FXML
    private TextField objet;
    @FXML
    private TextArea contenue;
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void btnAjouter(ActionEvent event) {
         String email = this.email.getText();
    String objet = this.objet.getText();
    String contenue = this.contenue.getText();
        if (email.isEmpty() || objet.isEmpty() || contenue.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All DATA");
            alert.showAndWait();

        } else {
            Reclamation rec1 = new Reclamation(email, objet,contenue);
             ReclamationService sp = new ReclamationService();
             sp.Ajouter(rec1);
              Alert alert = new Alert(Alert.AlertType.INFORMATION);
              alert.setTitle("Succès");
              alert.setHeaderText(null);
              alert.setContentText("Votre Reclamation est envoyé avec succée!");
              alert.showAndWait();
       }
}
    
    
     @FXML
     private void btnBack(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

   

    }
    
      





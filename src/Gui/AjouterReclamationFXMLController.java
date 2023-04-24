/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.ReclamationService;
import static com.sun.corba.se.impl.protocol.giopmsgheaders.AddressingDispositionHelper.type;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
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
private void btnAjouter(ActionEvent event) throws MessagingException, javax.mail.MessagingException {
    String email = this.email.getText();
    String objet = this.objet.getText();
    String contenue = this.contenue.getText();

    if (email.isEmpty() || objet.isEmpty() || contenue.isEmpty()) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Please Fill All DATA");
        alert.showAndWait();
    } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("l'email n'est pas valide");
        alert.showAndWait();
    } else {
        // Check the number of existing reclamations for the given email address
        ReclamationService sp = new ReclamationService();
        int existingCount = sp.getExistingCount(email);

        // If there are already 3 reclamations, do not insert the new one
        if (existingCount >= 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cannot add more than 3 reclamations for email: " + email);
            alert.showAndWait();
        } else {
            // Check if the contenue contains inappropriate words
            List<String> inappropriateWords = Arrays.asList("sex", "fuck","merde");
            boolean containsInappropriateWords = false;
            for (String word : inappropriateWords) {
                if (contenue.contains(word)) {
                    containsInappropriateWords = true;
                    break;
                }
            }
            if (containsInappropriateWords) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Le contenu de la réclamation ne peut pas contenir de mots inappropriés.");
                alert.showAndWait();
            } else {
                Reclamation rec1 = new Reclamation(email, objet, contenue);
          sp.envoyerEmailAdminNouvelleReclamation(rec1);
                sp.Ajouter(rec1);

                // Alert the user that the reclamations was added successfully
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Succès");
                alert.setHeaderText(null);
                alert.setContentText("Votre Reclamation est envoyé avec succée!");
                alert.showAndWait();
            }
        }
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
    
      






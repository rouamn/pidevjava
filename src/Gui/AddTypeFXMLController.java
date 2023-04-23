/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventTypeService;
import Serivces.Type;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AddTypeFXMLController implements Initializable {

    @FXML
    private TextField tfType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAfficher(ActionEvent event) {
    }

    @FXML
    private void btnType(ActionEvent event) {
    }

  
    //ajouter un type
@FXML
    private void btnAjouterT(ActionEvent event) {

        
        Type e1 = new Type(tfType.getText() );
        EventTypeService sp = new EventTypeService();
        sp.Ajouter(e1);

        // Show a success message to the user
        Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
        successAlert.setTitle("Success");
        successAlert.setHeaderText(null);
        successAlert.setContentText("The event has been successfully addet");
        successAlert.showAndWait();
    }
    @FXML
    private void btnRetourt(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TypeEventFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}

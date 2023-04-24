/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

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
import javafx.stage.Stage;
import Gui.ListReclamationFXMLController;
import static Gui.ListReclamationFXMLController.staticRec;
import Serivces.ReclamationService;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class UpdateRecFXMLController implements Initializable {
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldObjet;
    @FXML
    private TextArea textFieldContenue;
  
     
   
String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation rec = null ;
    
    ObservableList<Reclamation>  ReclamationList = FXCollections.observableArrayList();
    private Reclamation selectedReclamation;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
         
    }  

 
      @FXML
     private void btnBackList(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

   
    public static Reclamation staticRec;
      @FXML
private void btnUpdateReclam(ActionEvent event) throws IOException, SQLException {
   

    // Check if all input fields are filled
    if (textFieldEmail.getText().isEmpty() 
            || textFieldObjet.getText().isEmpty()
            || textFieldContenue.getText().isEmpty()) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Erreur");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Veuillez remplir tous les champs.");
        errorAlert.showAndWait();
        return;
    }  else if(!textFieldEmail.getText().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
    // gérer le cas où l'email n'est pas valide
     Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("l'email n'est pas valide");
        alert.showAndWait();
}

    
 ReclamationService RecService = new ReclamationService();
    
    Reclamation r = new Reclamation();
   
    r.setEmail_reclamation(textFieldEmail.getText());
    r.setObjet_reclamation(textFieldObjet.getText());
    r.setContenue_reclamation(textFieldContenue.getText());


    // Update the 'staticEvent' object with the new values
    staticRec.setEmail_reclamation(r.getEmail_reclamation());
    staticRec.setObjet_reclamation(r.getObjet_reclamation());
    staticRec.setContenue_reclamation(r.getContenue_reclamation());
    

    // Debug prints to check the 'staticRect' object
    System.out.println("staticRec: " + staticRec);

    // Update the record in the database
    RecService.modifier(staticRec);

    // Debug prints to check the SQL query
    //System.out.println("SQL query: " + eventService.getModifierQuery(staticRec));

    // Close the window
    ((Node) event.getSource()).getScene().getWindow().hide();

    // Refresh the table view
   
}

public void setReclam(Reclamation rec) {
       staticRec = rec;

        this.textFieldEmail.setText(staticRec.getEmail_reclamation());
        this.textFieldObjet.setText(staticRec.getObjet_reclamation());
        this.textFieldContenue.setText(staticRec.getContenue_reclamation()); 
             
    }

    }


         
    


      


 

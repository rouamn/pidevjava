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
import Serivces.ReclamationService;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TextField textFieldContenue;
  

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
     private void btnBackU(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
   


     
     
      @FXML
     private void btnBackList(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}

    public void setSelectedReclamation(Reclamation reclamation) {
    selectedReclamation = reclamation;
}
    
      @FXML
     private void btnUpdateRec(ActionEvent event) {
     // Vérifier si une réclamation est sélectionnée
      if (selectedReclamation != null) {
        String em = "";
        String ob = ""; // replace textFieldObjet with the actual name of your text field
        String con = ""; // replace textFieldContenue with the actual name of your text field

        // Mettre à jour les propriétés de la réclamation avec les nouvelles valeurs
        selectedReclamation.setEmail_reclamation(em);
        selectedReclamation.setObjet_reclamation(ob);
        selectedReclamation.setContenue_reclamation(con);

        // Appeler la méthode modifier() de votre service ReclamationService pour mettre à jour les données dans votre base de données
        ReclamationService sp = new ReclamationService();
        sp.modifier(selectedReclamation);
    }
}

         
    
}

      


 

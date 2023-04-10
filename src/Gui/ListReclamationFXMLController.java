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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListReclamationFXMLController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField objet;
    @FXML
    private TextField contenue;
    
  @FXML private TableView<Reclamation> table;
@FXML private TableColumn<Reclamation, Integer> idRec;
@FXML private TableColumn<Reclamation, String> emailRec;
@FXML private TableColumn<Reclamation, String> objetRec;
@FXML private TableColumn<Reclamation, String> contenueRec;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
   private void btnAfficherReclamation(ActionEvent event) {
    ReclamationService sp = new ReclamationService();
    List<Reclamation> reclamation = sp.afficher();
    ObservableList<Reclamation> observableEvents = FXCollections.observableArrayList(reclamation);

    idRec.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
    emailRec.setCellValueFactory(new PropertyValueFactory<>("email_reclamation"));
    objetRec.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
    contenueRec.setCellValueFactory(new PropertyValueFactory<>("contenue_reclamation"));
   
    table.setItems(observableEvents);
}
    @FXML
   private void btnAddRec(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
 
  }


   



    

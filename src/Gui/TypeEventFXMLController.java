/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventTypeService;
import Serivces.EventsService;
import Serivces.Type;
import entities.Events;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class TypeEventFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private TableColumn<Type, Integer> idType;
@FXML private TableColumn<Type, String> typeName;
@FXML private TableView<Type> table;
ObservableList<Type>  TypeList = FXCollections.observableArrayList();
 @FXML
    private TextField tfType;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML    
 private void btnAfficherType(ActionEvent event) {
   EventTypeService sp = new EventTypeService();
    List<Type> type = sp.afficher();
    ObservableList<Type> observableEvents = FXCollections.observableArrayList(type);

    idType.setCellValueFactory(new PropertyValueFactory<>("idType"));
 typeName.setCellValueFactory(new PropertyValueFactory<>("nameType"));
   
    table.setItems(observableEvents);
}
 @FXML
private void supprimerType(ActionEvent event) {
    Type t = table.getSelectionModel().getSelectedItem();
    if (t == null) {
        // Aucun evenement sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez un evenement");
        alert.setContentText("Veuillez sélectionner un evenement dans la table.");
        alert.showAndWait();
        return;
    }
    // Demander la confirmation de la suppression
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Supprimer l'evenement ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer l'evenement sélectionnée ?");
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.get() == ButtonType.OK) {
        // Supprimer la réclamation
       EventTypeService sp = new EventTypeService();
        sp.suprrimer(t);
        // Mettre à jour la table des réclamations
        TypeList.remove(t);
    }
    
}
 
 @FXML
private void btnAjouterType(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("addTypeFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
private void btnUpdateType(ActionEvent event) throws IOException {
    Type selectedEvent = table.getSelectionModel().getSelectedItem();
    if (selectedEvent == null) {
        // Aucun evenement sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez un evenement");
        alert.setContentText("Veuillez sélectionner un evenement dans la table.");
        alert.showAndWait();
        return;
    }
    Parent root = FXMLLoader.load(getClass().getResource("UpdateType.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
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
    private void btnRetour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePageFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public TableView<Type> getTable() {
        return table;
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventsService;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
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
public class HomePageFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
   
    @FXML
    private TextField tfTitreEvent;
    @FXML
    private TextField tfDateDebut;
    @FXML
    private TextField tfDateFin;
    @FXML
    private TextField tfPlaceEvent;
    @FXML
    private ListView<Events> eventListView;
    //@FXML
   // private TextField tfImageEvnet;
      @FXML
    private TextField tfDescriptionEvent;
 @FXML private TableView<Events> table;
@FXML private TableColumn<Events, Integer> idEvent;
@FXML private TableColumn<Events, String> titreEvent;
@FXML private TableColumn<Events, DatePicker> dateDebut;
@FXML private TableColumn<Events, DatePicker> dateFin;
@FXML private TableColumn<Events, String> placeEvent;
@FXML private TableColumn<Events, String> descriptionEvent; 
@FXML private TableColumn<Events, String> imageEvent; 
ObservableList<Events>  EventList = FXCollections.observableArrayList();
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
  
    @FXML
    
private void btnAjouter(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AjouterEventFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
private void btnUpdate(ActionEvent event) throws IOException {
    
    Parent root = FXMLLoader.load(getClass().getResource("UpdateEventFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
private void btnAfficcheType(ActionEvent event) throws IOException {
    
    Parent root = FXMLLoader.load(getClass().getResource("TypeEventFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
 @FXML    
 private void btnAfficher(ActionEvent event) {
    EventsService sp = new EventsService();
    List<Events> events = sp.afficher();
    ObservableList<Events> observableEvents = FXCollections.observableArrayList(events);

    idEvent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
    titreEvent.setCellValueFactory(new PropertyValueFactory<>("titreEvent"));
    dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
    dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
    placeEvent.setCellValueFactory(new PropertyValueFactory<>("placeEvent"));
    descriptionEvent.setCellValueFactory(new PropertyValueFactory<>("DescriptionEvent"));
 imageEvent.setCellValueFactory(new PropertyValueFactory<>("image"));
    table.setItems(observableEvents);
}
@FXML
private void supprimerEvent(ActionEvent event) {
    Events e = table.getSelectionModel().getSelectedItem();
    if (e == null) {
        // Aucun evenement sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez un evenement");
        alert.setContentText("Veuillez sélectionner un evenement dans la table.");
        alert.showAndWait();
        return;
    }
    // Demander la confirmation de la suppression
    Alert confirmation = new Alert(AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Supprimer l'evenement ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer l'evenement sélectionnée ?");
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.get() == ButtonType.OK) {
        // Supprimer la réclamation
         EventsService service = new  EventsService();
        service.suprrimer(e);
        // Mettre à jour la table des réclamations
        EventList.remove(e);
    }
}

   @FXML
    public TableView<Events> getTable() {
        return table;
   }


}





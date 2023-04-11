/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventsService;
import entities.Events;
import java.awt.Event;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UpdateEventFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
@FXML
private ImageView image;
@FXML
private TextField tfTitreEvent;
@FXML
private DatePicker tfDateDebut;
@FXML
private DatePicker tfDateFin;
@FXML
private TextField tfPlaceEvent;
//@FXML
// private TextField tfImageEvnet;
@FXML
private TextArea tfDescriptionEvent;
@FXML
private Button AddImage;
@FXML
private TextField URLImage;
@FXML
private TableView<Events> table;

ObservableList<Events>  EventList = FXCollections.observableArrayList();
  
  
@FXML private TableColumn<Events, Integer> idEvent;
@FXML private TableColumn<Events, String> titreEvent;
@FXML private TableColumn<Events, DatePicker> dateDebut;
@FXML private TableColumn<Events, DatePicker> dateFin;
@FXML private TableColumn<Events, String> placeEvent;
@FXML private TableColumn<Events, String> descriptionEvent; 
@FXML private TableColumn<Events, String> imageEvent; 
@Override
public void initialize(URL url, ResourceBundle rb) {
    // TODO
    //table = new TableView<>();
}

@FXML
private void btnModifier2(ActionEvent event) {
     if (tfTitreEvent.getText().isEmpty() || tfDateDebut.getValue() == null || tfDateFin.getValue() == null || tfPlaceEvent.getText().isEmpty() || tfDescriptionEvent.getText().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Please fill all input fields");
            errorAlert.showAndWait();
            return;
        }
        // Check if the start date is before the end date
        if (tfDateDebut.getValue().isAfter(tfDateFin.getValue())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Start date must be before end date");
            errorAlert.showAndWait();
            return;
        }
 Events selectedEvent = table.getSelectionModel().getSelectedItem();
    if (selectedEvent == null) {
        // Aucun evenement sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez un evenement");
        alert.setContentText("Veuillez sélectionner un evenement dans la table.");
        alert.showAndWait();
        return;
    }

    // Modifier l'événement sélectionné avec les nouvelles valeurs
    selectedEvent.setTitreEvent(tfTitreEvent.getText());
    selectedEvent.setDateDebut(Date.valueOf(tfDateDebut.getValue()));
    selectedEvent.setDateFin(Date.valueOf(tfDateFin.getValue()));
    selectedEvent.setPlaceEvent(tfPlaceEvent.getText());
    selectedEvent.setDescriptionEvent(tfDescriptionEvent.getText());
    // selectedEvent.setImage(URLImage.getText());

    // Enregistrer les modifications dans la base de données
    EventsService service = new EventsService();
    service.modifier(selectedEvent);
    EventsService sp = new EventsService();
    List<Events> events = sp.afficher();
    ObservableList<Events> observableEvents = FXCollections.observableArrayList(events);

    idEvent.setCellValueFactory(new PropertyValueFactory<>("idEvent"));
    titreEvent.setCellValueFactory(new PropertyValueFactory<>("titreEvent"));
    dateDebut.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
    dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
    placeEvent.setCellValueFactory(new PropertyValueFactory<>("placeEvent"));
    descriptionEvent.setCellValueFactory(new PropertyValueFactory<>("descriptionEvent"));
    // Make sure that the name of the property matches the name of the variable in your Events class that holds the image data
    imageEvent.setCellValueFactory(new PropertyValueFactory<>("eventImage"));
    table.setItems(observableEvents);
    // Rafraîchir la table avec les nouvelles données et afficher un message de succès
    refreshTable();
    showSuccessAlert("Event modifié avec succès.");
    // Show a success message to the user
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Success");
    successAlert.setHeaderText(null);
    successAlert.setContentText("The event has been successfully added");
    successAlert.showAndWait();
}

private void refreshTable() {
    // Récupérer la liste des événements depuis la base de données
    EventsService service = new EventsService();
    List<Events> eventsList = service.afficher();

    // Afficher la liste des événements dans la table
    ObservableList<Events> data = FXCollections.observableArrayList(eventsList);
    table.setItems(data);
}

private void showSuccessAlert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}

    @FXML
    private void btnRetour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePageFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

   
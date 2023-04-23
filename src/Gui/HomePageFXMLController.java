/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventsService;
import Serivces.Type;
import entities.Events;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
    private TableView<Events> table;

    ObservableList<Events> EventList = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Events, Integer> idEvent;
    @FXML
    private TableColumn<Events, String> titreEvent;
    @FXML
    private TableColumn<Events, DatePicker> dateDebut;
    @FXML
    private TableColumn<Events, DatePicker> dateFin;
    @FXML
    private TableColumn<Events, String> placeEvent;
    @FXML
    private TableColumn<Events, String> descriptionEvent;
    @FXML
    private TableColumn<Events, String> imageEvent;
    @FXML
    private TableColumn<Events, String> type;

    private byte[] imageData; // moved imageData to class level

    @FXML
    private Button btnModifier;
    @FXML
private ListView<Events> listView;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the table columns
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
        imageEvent.setCellFactory(col -> new ImageTableCell<>());
   type.setCellValueFactory(new PropertyValueFactory<>("typeName"));
        // Load the data from the database and add it to the TableView
        EventsService eventsService = new EventsService();
        List<Events> eventsList = eventsService.afficher();
        table.getItems().addAll(eventsList);
    }

// Custom TableCell to display images in TableView
    private static class ImageTableCell<S> extends TableCell<S, String> {

        private final ImageView imageView;

        public ImageTableCell() {
            imageView = new ImageView();
            imageView.setFitHeight(50);
            imageView.setFitWidth(50);
            setGraphic(imageView);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }

        @Override
        protected void updateItem(String imagePath, boolean empty) {
            super.updateItem(imagePath, empty);

            if (empty || imagePath == null) {
                imageView.setImage(null);
            } else {
                try {
                    Image image = new Image(new FileInputStream(imagePath));
                    imageView.setImage(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML

    private void btnAjouter(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterEventFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        //  refreshTable();
    }

    @FXML
    private void btnUpdate(ActionEvent event) throws IOException {
        // Get the selected event from the table

        Parent root = FXMLLoader.load(getClass().getResource("UpdateEventFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        refreshTable();
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
    private void stats(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("statstypecounter.fxml"));
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
       
type.setCellValueFactory(new PropertyValueFactory<>("typeName"));
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
            EventsService service = new EventsService();
            service.suprrimer(e);
            // Mettre à jour la table des réclamations
            EventList.remove(e);
            showSuccessAlert("L'événement a été supprimé avec succès.");
            refreshTable();
        }
    }

    @FXML
    public TableView<Events> getTable() {
        return table;
    }

    @FXML
    private void getAddView(ActionEvent event) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("/Gui/AjouterEventFXML.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
            refreshTable();

        } catch (IOException ex) {
            Logger.getLogger(HomePageFXMLController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }
 @FXML
    private void btnHomeUser(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("UserAffichage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void refreshTable() {
        // Retrieve the list of events from the database
        EventsService service = new EventsService();
        List<Events> eventsList = service.afficher();

        // Display the list of events in the table
        ObservableList<Events> data = FXCollections.observableArrayList(eventsList);
        table.setItems(data);
    }

   
    public static Events staticEvent;

    @FXML
    private void btnModifierEvent(ActionEvent event) throws IOException, SQLException {

        if (table.getSelectionModel().getSelectedItem() != null) {
            Events events = table.getSelectionModel().getSelectedItem();
            staticEvent = events;

            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateEventFXML.fxml"));
            Parent root = loader.load();
            UpdateEventFXMLController updateEventFXMLController = loader.getController();
            updateEventFXMLController.setEvents(events);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            refreshTable();
            showSuccessAlert("L'événement a été modifié avec succès.");

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner un evenet");
            alert.show();
        }
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    

}

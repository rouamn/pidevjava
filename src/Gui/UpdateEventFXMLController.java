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
import java.awt.Event;
import java.awt.Insets;
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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DELL
 */
/**
 * Initializes the controller class.
 */
public class UpdateEventFXMLController implements Initializable {

    @FXML
    private TableView<Events> tableEvents;
    @FXML
    private TextField tfTitreEvent;
    @FXML
    private DatePicker tfDateDebut;
    @FXML
    private DatePicker tfDateFin;
    @FXML
    private TextField tfPlaceEvent;
    @FXML
    private TextArea tfDescriptionEvent;
    @FXML
    private TextField URLImage;
    @FXML
    private ImageView image;
    private String imagePath; // make URLImage a class-level variable
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private Button AddImage;

    private HomePageFXMLController homeController;

    public void setHomeController(HomePageFXMLController homeController) {
        this.homeController = homeController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize the table columns
        EventTypeService typeService = new EventTypeService();
        EventsService sp = new EventsService();
        List<String> types = sp.getEventTypes();
        typeComboBox.getItems().addAll(types);

    }

    private void refreshTable() {
        // Retrieve the list of events from the database
        EventsService service = new EventsService();
        List<Events> eventsList = service.afficher();

        // Display the list of events in the table
        ObservableList<Events> data = FXCollections.observableArrayList(eventsList);
        tableEvents.setItems(data);
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void setEvents(Events event) {
        staticEvent = event;

        this.tfTitreEvent.setText(staticEvent.getTitreEvent());
        this.tfDateDebut.setValue(staticEvent.getDateDebut().toLocalDate());
        this.tfDateFin.setValue(staticEvent.getDateFin().toLocalDate());
        this.tfPlaceEvent.setText(staticEvent.getPlaceEvent());
        this.tfDescriptionEvent.setText(staticEvent.getDescriptionEvent());
        this.typeComboBox.setValue(staticEvent.getTypeName());
        this.tfDescriptionEvent.setText(staticEvent.getDescriptionEvent());
if (staticEvent.getImage() != null) {
    imagePath = staticEvent.getImage();
}
        if (imagePath != null && !imagePath.isEmpty()) {
            File imageFile = new File(imagePath);
            Image image = new Image(imageFile.toURI().toString());
            this.image.setImage(image);
            this.URLImage.setText(imageFile.getName());
        } else if (staticEvent.getImage() != null) {
            File imageFile = new File(staticEvent.getImage());
            Image image = new Image(imageFile.toURI().toString());
            this.image.setImage(image);
            this.URLImage.setText(imageFile.getName());
        } else {
            this.image.setImage(null);
            this.URLImage.setText("");
        }

    }
    public static Events staticEvent;

    @FXML
    private void con(ActionEvent event) throws IOException, SQLException {

        // Check if all input fields are filled
        if (tfTitreEvent.getText().isEmpty() || tfDateDebut.getValue() == null
                || tfDateFin.getValue() == null || tfPlaceEvent.getText().isEmpty()
                || tfDescriptionEvent.getText().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Veuillez remplir tous les champs.");
            errorAlert.showAndWait();
            return;
        }

        // Check that the start date is before the end date
        if (tfDateDebut.getValue().isAfter(tfDateFin.getValue())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("La date de début doit être antérieure à la date de fin.");
            errorAlert.showAndWait();
            return;
        }
        EventsService eventService = new EventsService();
        Events e = new Events();
        // convert LocalDate to Date sql date
        TimeZone timeZone = TimeZone.getDefault();
        LocalDate selectedDateDebut = tfDateDebut.getValue();
        LocalDate selectedDateFin = tfDateFin.getValue();
        e.setTitreEvent(tfTitreEvent.getText());
        e.setDateDebut(java.sql.Date.valueOf(selectedDateDebut));
        e.setDateFin(java.sql.Date.valueOf(selectedDateFin));
        e.setPlaceEvent(tfPlaceEvent.getText());
        e.setDescriptionEvent(tfDescriptionEvent.getText());
        e.setTypeName(typeComboBox.getValue());
        e.setImage(imagePath); // Set the image path

        // Update the 'staticEvent' object with the new values
        staticEvent.setTitreEvent(e.getTitreEvent());
        staticEvent.setDateDebut(e.getDateDebut());
        staticEvent.setDateFin(e.getDateFin());
        staticEvent.setPlaceEvent(e.getPlaceEvent());
        staticEvent.setDescriptionEvent(e.getDescriptionEvent());
        staticEvent.setTypeName(e.getTypeName());
        staticEvent.setImage(e.getImage());

        // Debug prints to check the 'staticEvent' object
        System.out.println("staticEvent: " + staticEvent);

        // Update the record in the database
        eventService.modifier(staticEvent);

        // Debug prints to check the SQL query
        //System.out.println("SQL query: " + eventService.getModifierQuery(staticEvent));
        // Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();

        // Refresh the table view
        //notification 
                //notification
        notiff();
    }
 public void notiff() {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("un evenement a été modifier ");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }
    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            // Existing code...
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Erreur");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Aucun fichier sélectionné.");
            errorAlert.showAndWait();
        }
        if (file != null) {
            // Create a new file in the directory where you want to save the image
            File imageFile = new File("path/to/save/images/" + file.getName());

          try (FileInputStream Fsource = new FileInputStream(file);
        FileOutputStream Fdestination = new FileOutputStream(imageFile);
        BufferedInputStream bin = new BufferedInputStream(Fsource);
        BufferedOutputStream bou = new BufferedOutputStream(Fdestination)) {

    // Copy the image file to the database directory
    int b = 0;
    while ((b = bin.read()) != -1) {
        bou.write(b);
    }

    // Set the image path for the Events object
    imagePath = imageFile.getPath();

    // Set the image view to display the selected image
    Image img = new Image(file.toURI().toString());
    image.setImage(img);

    // Set the text field to display the file name
    String fileName = file.getName();
    URLImage.setText(fileName);

} catch (IOException e) {
    System.out.println("Error: " + e.getMessage());
}
    }
    }}
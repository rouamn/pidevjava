/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventTypeService;
import Serivces.EventsService;
import Serivces.Type;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Events;
import java.awt.Event;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;



/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AjouterEventFXMLController implements Initializable {

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

    @FXML
    private TextArea tfDescriptionEvent;
    @FXML
    private Button AddImage;
    @FXML
    private Button btnmodifier;
    @FXML
    private TextField URLImage;

    @FXML
    private TypeEventFXMLController TypeEvent;

    @FXML
    private UserAffichageController userAffichageController;

    @FXML
    private ComboBox<String> typeComboBox; // changed TypeEvent to ComboBox
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
    private byte[] imageData; // moved imageData to class level

    private String imagePath; // make URLImage a class-level variable

    //sms 
    // Find your Account Sid and Token at console.twilio.com
    public static final String ACCOUNT_SID = "ACc3f43739cb99f93f555da9910499a8b3";
    public static final String AUTH_TOKEN = "fc49d7d85e6dfaf12238fc4b542b35bd";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // populate the combo box with event types from the database

        EventsService sp = new EventsService();
        List<String> types = sp.getEventTypes();
        typeComboBox.getItems().addAll(types);
    }

    @FXML
    private void btnAjouterEvent(ActionEvent event) {
        // validate input fields
        if (tfTitreEvent.getText().isEmpty() || tfDateDebut.getValue() == null || tfDateFin.getValue() == null || tfPlaceEvent.getText().isEmpty() || tfDescriptionEvent.getText().isEmpty()) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Please fill all input fields");
            errorAlert.showAndWait();
            return;
        }
        // check date range
        if (tfDateDebut.getValue().isAfter(tfDateFin.getValue())) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText("Start date must be before end date");
            errorAlert.showAndWait();
            return;
        }

        // create an Events object with the selected event type and other event details
        java.sql.Date dateDebut = java.sql.Date.valueOf(tfDateDebut.getValue());
        java.sql.Date dateFin = java.sql.Date.valueOf(tfDateFin.getValue());
        String typeName = typeComboBox.getSelectionModel().getSelectedItem();
        Events e1 = new Events(tfTitreEvent.getText(), dateDebut, dateFin, tfPlaceEvent.getText(), tfDescriptionEvent.getText(), imagePath, typeName);

        // call the Ajouter method of the EventsService class to add the event to the database
        EventsService sp = new EventsService();
        sp.Ajouter(e1);

        // show success message to the user
        showSuccessAlert("The event has been successfully added.");

        //notification
        notiff();

        //sms 
        // sms();
        // Close the window
        ((Node) event.getSource()).getScene().getWindow().hide();
    }

    public void notiff() {
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.SLIDE;
        tray.setAnimationType(type);
        tray.setTitle("attention");
        tray.setMessage("un evenement a été ajouté");
        tray.setNotificationType(NotificationType.INFORMATION);
        tray.showAndDismiss(Duration.millis(2000));
    }

    public void sms() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(
                        new PhoneNumber("+21656142132"), // Recipient phone number
                        new PhoneNumber("+16207054078"), // Twilio phone number
                        "un nouveau evenement a ete ajoute"
                )
                .create();

        System.out.println(message.getSid());
    }

    @FXML
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "" + x + ".jpg";

        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file);
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);

            // Copy the image file to the database directory
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();

            // Set the image path for the Events object
            imagePath = DBPath;

            // Set the image view to display the selected image
            Image img = new Image(file.toURI().toString());
            image.setImage(img);

            // Set the text field to display the file name
            String fileName = file.getName();
            URLImage.setText(fileName);
        } else {
            System.out.println("Error: No file selected");
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

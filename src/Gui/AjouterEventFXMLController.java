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
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    //@FXML
    // private TextField tfImageEvnet;
    @FXML
    private TextArea tfDescriptionEvent;
    @FXML
    private Button AddImage;
    @FXML
    private TextField URLImage;
   @FXML
private TypeEventFXMLController TypeEvent;

@FXML
private ComboBox<Type> Type;

/**
 * Initializes the controller class.
 */
@Override
public void initialize(URL url, ResourceBundle rb) {
    // Populate the Type ComboBox with values from the database
    EventTypeService typeService = new EventTypeService();
    List<Type> types = typeService.afficher();
    Type.getItems().addAll(types);
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

    // prompt user to select a Type for the event
    Type selectedType = Type.getSelectionModel().getSelectedItem();
    if (selectedType == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Select a Type");
        alert.setContentText("Please select a Type for the event.");
        alert.showAndWait();
        return;
    }

    // create an Events object with the selected Type and other event details
    java.sql.Date dateDebut = java.sql.Date.valueOf(tfDateDebut.getValue());
    java.sql.Date dateFin = java.sql.Date.valueOf(tfDateFin.getValue());
    Events e1 = new Events(tfTitreEvent.getText(), dateDebut, dateFin, tfPlaceEvent.getText(), tfDescriptionEvent.getText(), selectedType.getNameType());

    // call the Ajouter method of the EventsService class to add the event to the database
    EventsService sp = new EventsService();
    sp.Ajouter(e1);

    // show success message to the user
    Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
    successAlert.setTitle("Success");
    successAlert.setHeaderText(null);
    successAlert.setContentText("The event has been successfully added.");
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
    private void AddImage(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG"),
                new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG"),
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png")
        );

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // Load the selected image file
                BufferedImage bufferedImage = ImageIO.read(file);

                // Convert the BufferedImage to a JavaFX WritableImage
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);

                // Set the image in the ImageView and resize it to 200x200
                this.image.setImage(image);
                this.image.setFitWidth(200);
                this.image.setFitHeight(200);
                this.image.setSmooth(true);
                this.image.setCache(true);

                // Read the image data into a byte array
                byte[] imageData;
                try (FileInputStream fin = new FileInputStream(file);
                        ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    byte[] buf = new byte[1024];
                    for (int readNum; (readNum = fin.read(buf)) != -1;) {
                        bos.write(buf, 0, readNum);
                    }
                    imageData = bos.toByteArray();
                }

                // Store the image data in a class variable or database, if necessary
                // person_image = imageData;
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

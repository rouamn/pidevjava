/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.EventsService;
import entities.Events;
import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.xml.bind.DatatypeConverter;
import org.controlsfx.control.Rating;
import tray.animations.AnimationType;
import tray.notification.TrayNotification;
import tray.notification.NotificationType;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.ContentType;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UserAffichageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TilePane eventTilePane;
    @FXML
    private WebView webView;
@FXML
private Pagination pagination;

private int itemsPerPage = 10;
private ObservableList<Events> allEventsData;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listeEvents();
    }
    public static Events staticEvent;

    // ...
   @FXML
private void listeEvents() {
    eventTilePane.getChildren().clear(); // Remove existing child nodes
    EventsService sp = new EventsService();
    List<Events> events = sp.afficher();

    for (Events e : events) {
        VBox eventBox = new VBox();
        ImageView imageView = new ImageView();
        String filename = e.getImage(); // Retrieve filename from database
        File file = new File(filename);
        if (file.exists()) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
        } else {
            System.err.println("Image file not found: " + filename);
        }

        Label titleLabel = new Label(e.getTitreEvent());
        Label dateLabel = new Label(e.getDateDebut() + " - " + e.getDateFin());
        Label placeLabel = new Label(e.getPlaceEvent());
        Label descriptionLabel = new Label(e.getDescriptionEvent());
        Label typeLabel = new Label(e.getTypeName());

        // Apply styles to the Labels
        titleLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");
        dateLabel.setStyle("-fx-font-style: italic;");
        placeLabel.setStyle("-fx-text-fill: darkgray;");
        descriptionLabel.setStyle("-fx-text-fill: gray;");
        typeLabel.setStyle("-fx-text-fill: gray;");

        imageView.setFitWidth(200);
        imageView.setFitHeight(200);

     
        // Create a "Learn More" button for this event
        Button learnMoreButton = new Button("Learn More");

        // Set the button's onAction property to a lambda expression that handles the button click event
        learnMoreButton.setOnAction(event -> {
            staticEvent = e; // set the staticEvent object to the selected event
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("DetailsEvent.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Détails de l'événement");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DetailsEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        // Apply styles to the learn more button
        learnMoreButton.setStyle("-fx-background-color: #25adbe; -fx-text-fill: white; -fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1; -fx-padding: 5;");

        // Apply styles to the eventBox
        eventBox.setStyle("-fx-background-color: white; -fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1; -fx-padding: 10;");
        eventBox.setSpacing(5);
        eventBox.getChildren().addAll(imageView, titleLabel, dateLabel, placeLabel, descriptionLabel, typeLabel,  learnMoreButton);
        eventTilePane.getChildren().add(eventBox);
        System.out.println("VBox added to TilePane");
    }
}
    @FXML
    private void btnHomeUser(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("HomePageFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}

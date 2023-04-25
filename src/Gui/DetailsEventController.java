/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import static Gui.UpdateEventFXMLController.staticEvent;
import Serivces.EventsService;
import entities.Events;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static javafx.scene.paint.Color.color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.controlsfx.control.Rating;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DetailsEventController implements Initializable {

    @FXML
    private Label titleLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label placeLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label typeLabel;
    @FXML
    private ImageView eventImage;
    @FXML
    private Rating eventRating;
     public static double lon;
        public static double lat;

    @FXML
    private WebView webview;
    private WebEngine webengine;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        eventsDetails();
          webengine = webview.getEngine();

        url = this.getClass().getResource("googleMaps.html");
        webengine.load(url.toString());
    }

  private void eventsDetails() {
    // Retrieve the selected event from the staticEvent object
    Events event = UserAffichageController.staticEvent;

    // Set the properties of the Labels to the properties of the selected event
    titleLabel.setText(event.getTitreEvent());
    dateLabel.setText(event.getDateDebut() + " - " + event.getDateFin());
    placeLabel.setText(event.getPlaceEvent());
    descriptionLabel.setText(event.getDescriptionEvent());
    typeLabel.setText(event.getTypeName());
    String filename = event.getImage();
    File file = new File(filename);
    if (file.exists()) {
        try {
            Image image = new Image(file.toURI().toString());
            eventImage.setImage(image);
        } catch (Exception e) {
            System.err.println("Error loading image: " + filename);
        }
    } else {
        System.err.println("Image file not found: " + filename);
    }

    // Create the rating stars control
    Rating rating = new Rating();

    // Set the rating stars control to the rating value from the event object
    rating.setRating(event.getRating());

    // Set the color of the selected stars
    String color = event.getColor();
    if (color != null && !color.isEmpty()) {
        rating.setStyle("-fx-star-fill-color: " + color + ";");
    }

    // Add an event handler to the rating control to handle clicks on the stars
    rating.setOnMouseClicked(event1 -> {
        // Get the current rating value and color
        int ratingValue = (int) rating.getRating();
        String selectedColor = rating.getStyle().substring(rating.getStyle().indexOf("#"));

        // Update the rating and color values in the database and in the staticEvent object
        updateRating(ratingValue, selectedColor);
    });

    // Create a VBox to hold the rating stars control
    VBox ratingBox = new VBox();
    ratingBox.setSpacing(10);
    ratingBox.getChildren().addAll(new Label("Rating:"), rating);

    // Apply styles to the rating VBox
    ratingBox.setStyle("-fx-background-color: #f2f2f2; -fx-padding: 10px;");

    // Add the rating VBox to the HBox
    HBox hbox = (HBox) eventImage.getParent();
    hbox.getChildren().add(ratingBox);

    // Create a new WebView for the map
    WebView mapWebView = new WebView();
    mapWebView.setPrefSize(400, 400);

    // Load the customized map with a marker at the event's location
    String mapUrl = getClass().getResource("googleMaps.html").toExternalForm();
   // String js = String.format("initializeMap(%f, %f);", event.getLatitude(), event.getLongitude());
    mapWebView.getEngine().getLoadWorker().stateProperty().addListener((ov, oldState, newState) -> {
        if (newState == Worker.State.SUCCEEDED) {
           // mapWebView.getEngine().executeScript(js);
        }
    });
    mapWebView.getEngine().load(mapUrl);

}
  private void updateRating(int ratingValue, String selectedColor) {
    try {
        // Get the selected event from the staticEvent object
        Events event = UserAffichageController.staticEvent;

        // Connect to the database
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionsalledesport", "root", "");

        // Prepare an SQL statement to update the rating field in the events table
        String sql = "UPDATE evenement SET rating = ? WHERE id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, ratingValue);
        pstmt.setInt(2, event.getIdEvent());

        // Execute the SQL statement
        pstmt.executeUpdate();

        // Close the database connection
        conn.close();

        // Update the rating value and color in the staticEvent object
        event.setRating(ratingValue);
        event.setColor(selectedColor);
    } catch (SQLException e) {
        e.printStackTrace();
    }
     

}
  
    @FXML
    private void tt(ActionEvent event) {
      
           
             lat = (Double) webview.getEngine().executeScript("lat");
             lon = (Double) webview.getEngine().executeScript("lon");


        System.out.println("Lat: " + lat);
                System.out.println("LOn " + lon);


    }

// JavaScript interface object
private class JavaApp {
  public void exit() {
    Platform.exit();
  }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Utils.MyDB;
import entities.Events;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatstypecounterController implements Initializable {

    @FXML
    private PieChart eventCountsPieChart;
    private Statement statement;
    private Connection connection;

    @FXML
    private AnchorPane anchorPane;

    public StatstypecounterController() {
        connection = MyDB.getInstance().getcon();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Retrieve event statistics and display them in the pie chart
        Map<String, Integer> eventCountsByType = count();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        eventCountsByType.forEach((type, count) -> {
            pieChartData.add(new PieChart.Data(type, count));
        });
        eventCountsPieChart.setData(pieChartData);
       // showRatingStatistics();
    }

    public Map<String, Integer> count() {
        Map<String, Integer> eventCountsByType = new HashMap<>();
        try {
            statement = connection.createStatement();

            String req = "SELECT t.type_name, COUNT(*) AS event_count FROM `gestionsalledesport`.`evenement` e JOIN `gestionsalledesport`.`type_evenement` t ON e.`type_id` = t.`id` GROUP BY t.type_name";
            ResultSet res = statement.executeQuery(req);
            while (res.next()) {
                String typeName = res.getString("type_name");
                int eventCount = res.getInt("event_count");
                eventCountsByType.put(typeName, eventCount);
            }

        } catch (SQLException ex) {
            System.out.println("err" + ex.getMessage());
        }
        return eventCountsByType;
    }

    @FXML
    private void btnbackhome(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("HomePageFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void btnbaccType(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("TypeEventFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    private void btnbackUserPage(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("UserAffichage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    private void showRatingStatistics() {
        // Get the selected event from the staticEvent object

      // Get the selected event from the staticEvent object
    Events event = UserAffichageController.staticEvent;
    
    

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gestionsalledesport", "root", "");

            // Prepare an SQL statement to retrieve all the ratings for the selected event
            String sql = "SELECT rating FROM evenement WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, event.getIdEvent());

            // Execute the SQL statement and retrieve the results
            ResultSet rs = pstmt.executeQuery();

            // Initialize an array to store the number of ratings for each star value
            int[] ratings = new int[5];

            // Iterate through the results and count the number of ratings for each star value
            while (rs.next()) {
                int rating = rs.getInt("rating");
                if (rating >= 1 && rating <= 5) {
                    ratings[rating - 1]++;
                }
            }

            // Display the statistics in a bar chart
            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setCategories(FXCollections.<String>observableArrayList(Arrays.asList("1 star", "2 stars", "3 stars", "4 stars", "5 stars")));
            xAxis.setLabel("Star Rating");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Number of Ratings");

            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Number of Ratings");

            series.getData().add(new XYChart.Data<>("1 star", ratings[0]));
            series.getData().add(new XYChart.Data<>("2 stars", ratings[1]));
            series.getData().add(new XYChart.Data<>("3 stars", ratings[2]));
            series.getData().add(new XYChart.Data<>("4 stars", ratings[3]));
            series.getData().add(new XYChart.Data<>("5 stars", ratings[4]));

            barChart.getData().add(series);

            // Add the bar chart to the VBox
            VBox ratingBox = new VBox();
            ratingBox.getChildren().addAll(new Label("Rating Statistics:"), barChart);

            // Add the VBox to the AnchorPane
            if (anchorPane != null) {
                AnchorPane.setTopAnchor(ratingBox, 10.0);
                AnchorPane.setLeftAnchor(ratingBox, 10.0);
                AnchorPane.setRightAnchor(ratingBox, 10.0);
                AnchorPane.setBottomAnchor(ratingBox, 10.0);
                anchorPane.getChildren().add(ratingBox);
            } else {
                System.out.println("AnchorPane is null");
            }

            // Close the database connection
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

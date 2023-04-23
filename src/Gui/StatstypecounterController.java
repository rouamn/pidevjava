/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Utils.MyDB;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class StatstypecounterController implements Initializable {

    @FXML private PieChart eventCountsPieChart;
    private Statement statement;
    private Connection connection;

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
}
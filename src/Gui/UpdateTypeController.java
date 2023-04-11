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
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
public class UpdateTypeController implements Initializable {

    /**
     * Initializes the controller class.
     */
 @FXML
    private TextField tfType;


ObservableList<Events>  EventList = FXCollections.observableArrayList();
  @FXML private TableView<Type> table;
  
@FXML private TableColumn<Events, Integer> idType;
@FXML private TableColumn<Events, String> name;
  @FXML private  TypeEventFXMLController  TypeEvent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

  public void setHomeController(TypeEventFXMLController TypeEvent) {
        this. TypeEvent = TypeEvent;
    }      
    
 @FXML
private void btnModifiert(ActionEvent event) {
    if (tfType.getText().isEmpty()) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle("Error");
        errorAlert.setHeaderText(null);
        errorAlert.setContentText("Please fill all input fields");
        errorAlert.showAndWait();
        return;
    }

    Type selectedEvent = TypeEvent.getTable().getSelectionModel().getSelectedItem();
    if (selectedEvent == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez un evenement");
        alert.setContentText("Veuillez sélectionner un evenement dans la table.");
        alert.showAndWait();
        return;
    }

    selectedEvent.setNameType(tfType.getText());
    EventTypeService service = new EventTypeService();
    service.modifier(selectedEvent);

    // Refresh the table with the new data and show a success message to the user
    //TypeEvent.refreshTable();
   // TypeEvent.showSuccessAlert("Type event modifié avec succès.");

    // Close the current window
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.close();
}



private void showSuccessAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Succès");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
    
}

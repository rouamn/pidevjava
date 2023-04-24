/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;



import java.util.stream.Collectors;
import static Gui.UpdateRecFXMLController.staticRec;
import Serivces.ReclamationService;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListReclamationFXMLController implements Initializable {
   
     @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField email;
    @FXML
    private TextField objet;
    @FXML
    private TextField contenue;
    
@FXML
    private TextField emailrecherche;
 @FXML
    private Button btnModifier; 
    
  @FXML private TableView<Reclamation> table;
@FXML private TableColumn<Reclamation, Integer> idRec;
@FXML private TableColumn<Reclamation, String> emailRec;
@FXML private TableColumn<Reclamation, String> objetRec;
@FXML private TableColumn<Reclamation, String> contenueRec;

String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation rec = null ;
    
    ObservableList<Reclamation>  ReclamationList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
         ReclamationService sp = new ReclamationService();
    List<Reclamation> reclamation = sp.afficher();
    ObservableList<Reclamation> observableEvents = FXCollections.observableArrayList(reclamation);

    idRec.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
    emailRec.setCellValueFactory(new PropertyValueFactory<>("email_reclamation"));
    objetRec.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
    contenueRec.setCellValueFactory(new PropertyValueFactory<>("contenue_reclamation"));
    
    table.setItems(observableEvents);
 
    }    
    @FXML
   private void btnAfficherReclamation(ActionEvent event) {
    ReclamationService sp = new ReclamationService();
    List<Reclamation> reclamation = sp.afficher();
    ObservableList<Reclamation> observableEvents = FXCollections.observableArrayList(reclamation);

    idRec.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
    emailRec.setCellValueFactory(new PropertyValueFactory<>("email_reclamation"));
    objetRec.setCellValueFactory(new PropertyValueFactory<>("objet_reclamation"));
    contenueRec.setCellValueFactory(new PropertyValueFactory<>("contenue_reclamation"));
   
    table.setItems(observableEvents);
}
    @FXML
   private void btnAddRec(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
   
    @FXML
   private void btnListCatRec(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListCatRecFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
   
   @FXML
private void supprimerReclamation(ActionEvent event) {
    Reclamation reclamation = table.getSelectionModel().getSelectedItem();
    if (reclamation == null) {
        // Aucune réclamation sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez une réclamation");
        alert.setContentText("Veuillez sélectionner une réclamation dans la table.");
        alert.showAndWait();
        return;
    }
    // Demander la confirmation de la suppression
    Alert confirmation = new Alert(AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Supprimer la réclamation ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer la réclamation sélectionnée ?");
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.get() == ButtonType.OK) {
        // Supprimer la réclamation
        ReclamationService service = new ReclamationService();
        service.suprrimer(reclamation);
        // Mettre à jour la table des réclamations
         ReclamationList.remove(reclamation);
          refreshTable(); 
    }
}
 
  

    private void refreshTable() {
        // Retrieve the list of events from the database
        ReclamationService service = new ReclamationService();
        List<Reclamation> ReclamList = service.afficher();

        // Display the list of events in the table
        ObservableList<Reclamation> data = FXCollections.observableArrayList(ReclamList);
         table.setItems(data);
    }
public static Reclamation staticRec;
@FXML
    private void btnModifierRec(ActionEvent event) throws IOException, SQLException {

        if (table.getSelectionModel().getSelectedItem() != null) {
            Reclamation rec = table.getSelectionModel().getSelectedItem();
            staticRec=rec;
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateRecFXML.fxml"));
            Parent root = loader.load();
            UpdateRecFXMLController updateRecFXMLController = loader.getController();
            updateRecFXMLController .setReclam(rec);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.showAndWait();
            //Afficher();
          refreshTable();  
         

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous devez selectionner une reclamation");
            alert.show();
        }
        
    }
    

@FXML
    void btnRechercher(ActionEvent event) {
        String email = emailrecherche.getText();

    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("L'e-mail n'est pas valide.");
        alert.showAndWait();
    } else{
        String searchQuery = emailrecherche.getText();
        ReclamationService sp = new ReclamationService();
        List<Reclamation> searchResults = sp.rechercherAvancee(searchQuery);
        table.getItems().setAll(searchResults);
    }}

   @FXML
    public void btnversStat(ActionEvent event) throws IOException {
        // code pour retourner à la page précédente
         Parent root = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
}
     



   
   
  






  
 
  


   



    

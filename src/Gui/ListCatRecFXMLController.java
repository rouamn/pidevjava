/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Serivces.CategorieReclamationService;
import Serivces.ReclamationService;
import entities.CategorieReclamation;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListCatRecFXMLController implements Initializable {
 @FXML
    private TextField libelle;
   
    
  @FXML private TableView<CategorieReclamation> tableCat;
@FXML private TableColumn<CategorieReclamation, Integer> idCatRec;
@FXML private TableColumn<CategorieReclamation, String> libelleCatRec;


String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Reclamation rec = null ;
    
     ObservableList<CategorieReclamation>  CatReclamationList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
   private void btnListReclam(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("ListReclamationFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
    @FXML
   private void btnAfficherCatReclamation(ActionEvent event) {
    CategorieReclamationService csp = new CategorieReclamationService();
     List<CategorieReclamation> creclamation = csp.afficher();
    ObservableList<CategorieReclamation> observableEvents = FXCollections.observableArrayList(creclamation);

    idCatRec.setCellValueFactory(new PropertyValueFactory<>("idCategorieReclamation"));
    libelleCatRec.setCellValueFactory(new PropertyValueFactory<>("libelle"));
  
    tableCat.setItems(observableEvents);
}
    @FXML
   private void btnAddcatRec(ActionEvent event) throws IOException {
    Parent root = FXMLLoader.load(getClass().getResource("AddCatRecFXML.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
}
   @FXML
private void supprimercatReclamation(ActionEvent event) {
    CategorieReclamation creclamation = tableCat.getSelectionModel().getSelectedItem();
    if (creclamation == null) {
        // Aucune réclamation sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Sélectionnez une categorie réclamation");
        alert.setContentText("Veuillez sélectionner une categorie réclamation dans la table.");
        alert.showAndWait();
        return;
    }
    // Demander la confirmation de la suppression
    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
    confirmation.setTitle("Confirmation");
    confirmation.setHeaderText("Supprimer la réclamation ?");
    confirmation.setContentText("Êtes-vous sûr de vouloir supprimer la réclamation sélectionnée ?");
    Optional<ButtonType> result = confirmation.showAndWait();
    if (result.get() == ButtonType.OK) {
        // Supprimer la réclamation
         CategorieReclamationService cservice = new CategorieReclamationService();
        cservice.suprrimer(creclamation);
        // Mettre à jour la table des réclamations
         CatReclamationList.remove(creclamation);
    }
}
}

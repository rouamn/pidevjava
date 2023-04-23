/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import entities.Events;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class DetailsEventController implements Initializable {

    /**
     * Initializes the controller class.
     */
      private Events event;
       @FXML
    private Label titleLabel;
    
    @FXML
    private Label dateLabel;
    
    @FXML
    private Label placeLabel;
    
    @FXML
    private Label descriptionLabel;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    
    
    public void initData(Events event) {
        this.event = event;
        titleLabel.setText(event.getTitreEvent());
        dateLabel.setText(event.getDateDebut() + " - " + event.getDateFin());
        placeLabel.setText(event.getPlaceEvent());
        descriptionLabel.setText(event.getDescriptionEvent());
    }
    
}
    


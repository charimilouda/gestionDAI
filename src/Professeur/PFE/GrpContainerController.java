/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.PFE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class GrpContainerController implements Initializable {

    @FXML
    private Label groupe;

    @FXML
    private Label sujet;

    public void setGroupe(String str) {
        groupe.setText(str);
    }

    public void setSujet(String str) {
        sujet.setText(str);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

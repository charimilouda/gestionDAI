/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Stages;

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
public class StageViewController implements Initializable {

    @FXML
    private Label debut;

    @FXML
    private Label fin;

    @FXML
    private Label lieu;

    @FXML
    private Label sujet;

    @FXML
    private Label etudiant;

    public void setEtudiant(String str) {
        etudiant.setText(str);
    }
    
    public void setDebut(String str) {
        debut.setText(str);
    }

    public void setFin(String str) {
        fin.setText(str);
    }

    public void setLieu(String str) {
        lieu.setText(str);
    }

    public void setSujet(String str) {
        sujet.setText(str);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Matieres;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class MatiereViewController implements Initializable {

    @FXML
    private Label matiere;
    
    @FXML
    private Label idmatiere;

    @FXML
    private ChoiceBox<String> prof;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setMatiere(String str) {
        matiere.setText(str);
    }

    public void setProf(ArrayList<String> arr) {
        prof.getItems().addAll(arr);
    }
    
    public void setIDMatiere(String str) {
        idmatiere.setText(str);
    }
    
    
}

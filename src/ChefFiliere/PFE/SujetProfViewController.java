/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class SujetProfViewController implements Initializable {

    @FXML
    private Label nomProf;

    @FXML
    private Text sujets;
    public void setNomProf(String nom)
    {
        nomProf.setText(nom);
    }
    public void setSujets(String contenu)
    {
        sujets.setText(contenu);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

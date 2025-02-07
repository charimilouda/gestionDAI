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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class SujetContainerController implements Initializable {
    @FXML
    private Text txt_sujet;

    @FXML
    private TextField sujet;
    
    public void setSujet(String str)
    {
        sujet.setText(str);
    }
    
    public void setLabelSujet(String str)
    {
        txt_sujet.setText(str);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    
}

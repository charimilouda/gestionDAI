/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class GroupeEtudViewController implements Initializable {

    
    @FXML
    private Text grp_etud;

    @FXML
    private Label id_grp;
    
    public void setIdGrp(String str)
    {
        id_grp.setText(str);
    }
    
    public void setGrp(String str)
    {
        grp_etud.setText(str);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

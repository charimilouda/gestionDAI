/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class NbrSujetContainerController implements Initializable {
   @FXML
    private Label label_nomProf;

    @FXML
    private TextField txtfield_nbrSujet;
 
    
    public void setNomProf(String nomProf)
    {
        label_nomProf.setText(nomProf);
    }
    
    public void setNbrSujet(int nbr)
    {
        txtfield_nbrSujet.setText(Integer.toString(nbr));
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

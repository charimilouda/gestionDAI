/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Deconnexion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class DeconnexionController implements Initializable {

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_logout;
    
    ActionEvent e;

    public void setE(ActionEvent e) {
        this.e = e;
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void logout(ActionEvent event) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/Authentification/Authentification.fxml"));
        Parent root=loader.load();
        Stage home = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        home.setScene(scene);
        home.show();
        Stage stage = (Stage) btn_logout.getScene().getWindow();
        stage.close();
        
    }
}

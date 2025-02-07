/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Messagerie;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class ContentController implements Initializable {

    private int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    String emailEmetteur="melou" , sujetMessage="pfe" , message ="salam",date="0000-00-00";

    public void setEmailEmetteur(String emailEmetteur) {
        this.emailEmetteur = emailEmetteur;
    }

    public void setSujetMessage(String sujetMessage) {
        this.sujetMessage = sujetMessage;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @FXML
    private Label abrev;

    @FXML
    private TextArea content;

    @FXML
    private Label dateTime;

    @FXML
    private Label object;

    @FXML
    private Label sender;
   
    public void getInformations(String emailEmetteur,String sujetMessage,String message,String date) throws SQLException
    {   
        sender.setText(emailEmetteur);
        object.setText(sujetMessage);
        content.setText(message);
        dateTime.setText(date);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getInformations(emailEmetteur,sujetMessage,message,date);
        } catch (SQLException ex) {
            Logger.getLogger(ContentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

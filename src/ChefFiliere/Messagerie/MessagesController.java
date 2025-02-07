/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Messagerie;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class MessagesController implements Initializable {
    String emailEmetteur="melou" , sujetMessage="pfe" , message ="salam",datee="0000-00-00";

    public String getEmailEmetteur() {
        return emailEmetteur;
    }

    public String getSujetMessage() {
        return sujetMessage;
    }

    public String getMessage() {
        return message;
    }
    public void setemailEmetteur(String emailEmetteur)
    {
        this.emailEmetteur=emailEmetteur;
    }
    public void setsujetMessage(String sujetMessage)
    {
        this.sujetMessage=sujetMessage;
    }
    public void setmessage(String message)
    {
        this.message=message;
    }
   public void setDate(String date) {
        this.datee = date;
    }
    @FXML
    private Label sender;
    @FXML
    private Label subject;
    @FXML
    private Label text;
        @FXML
    private Label datetime;
    
    BorderPane bp;

    public void setBp(BorderPane bp) {
        this.bp = bp;
    }
    
    
    public void getInformations(String emailEmetteur,String sujetMessage,String message,String date ) throws SQLException
    {   
        sender.setText(emailEmetteur);
        subject.setText(sujetMessage);
        text.setText(message);
        datetime.setText(date);
    }
    public void afficher() throws IOException
    {
        ContentController cc=new ContentController();
        cc.setEmailEmetteur(emailEmetteur);
        cc.setSujetMessage(sujetMessage);
        cc.setMessage(message);
        cc.setDate(datee);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(cc);
        loader.setLocation(getClass().getResource("./content.fxml"));
        AnchorPane ap = loader.load();
        bp.setCenter(ap);
       
        
        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getInformations(emailEmetteur,sujetMessage,message,datee);
        } catch (SQLException ex) {
            Logger.getLogger(MessagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

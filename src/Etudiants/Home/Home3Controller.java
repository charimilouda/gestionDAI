/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Home;


import Deconnexion.DeconnexionController;
import Etudiants.Messagerie.MessagerieController;
import Etudiants.Notes.NotesController;
import Etudiants.Calendrier.CalendrierController;
import Etudiants.Documents.DocumentsController;
import Etudiants.PFE.PFEController;
import Etudiants.Profil.ProfilController;
import Etudiants.Stages.StageController;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CHARI MILOUDA
 */
public class Home3Controller implements Initializable {

    int id_session_courante;
    public void setIdSessionCourante(int id_session_courante)
    {
            this.id_session_courante=id_session_courante;
    }
    @FXML private Button MenuHide; 
    @FXML private Button calendrier; 
    @FXML private BorderPane container; 
    @FXML private Button deconnexion; 
    @FXML private Button documents; 
    @FXML private Button notes; 
    @FXML private Button messagerie;
    @FXML private Button pfe;
    @FXML private Button profil;
    @FXML private Button stage;
    public void switchToDocuments(ActionEvent e) throws IOException
    {
        documents.getStyleClass().clear();
        documents.getStyleClass().add("btnsHome");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().add("btns");
        
        DocumentsController c=new DocumentsController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Documents/Documents.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
    public void switchToCalendrier(ActionEvent e) throws IOException
    {
        
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().clear();
        calendrier.getStyleClass().add("btnsHome");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().add("btns");
        
        
        CalendrierController c=new CalendrierController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Calendrier/Calendrier.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
     public void switchToMessagerie(ActionEvent e) throws IOException, SQLException
    {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().clear();
        messagerie.getStyleClass().add("btnsHome");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().add("btns");
        MessagerieController c=new MessagerieController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Messagerie/Messagerie.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
         
    }
    public void switchToStage(ActionEvent e) throws IOException
    
    {   documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().clear();
        stage.getStyleClass().add("btnsHome");
        StageController c=new StageController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Stages/Stage.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
    public void switchToNotes(ActionEvent e) throws IOException
    {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().clear();
        notes.getStyleClass().add("btnsHome");
        stage.getStyleClass().add("btns");
        NotesController c=new NotesController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Notes/Notes.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
    public void switchToPFE(ActionEvent e) throws IOException
    {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().clear();
        pfe.getStyleClass().add("btnsHome");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().add("btns");
        
        PFEController c=new PFEController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../PFE/PFE.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
    
    public void switchToProfil(ActionEvent e) throws IOException
    {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().clear();
        profil.getStyleClass().add("btnsHome");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        notes.getStyleClass().add("btns");
        stage.getStyleClass().add("btns");
        ProfilController c=new ProfilController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        System.out.print("id_session_courante"+id_session_courante);
        loader.setLocation(getClass().getResource("../Profil/Profil.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
        
    }
     public void deconnexion(ActionEvent eHome) throws IOException
    {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().clear();
        deconnexion.getStyleClass().add("btnsHome");
        
        DeconnexionController c=new DeconnexionController();
        c.setE(eHome);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("/Deconnexion/Deconnexion.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            documents.getStyleClass().add("btns");
            messagerie.getStyleClass().add("btns");
            profil.getStyleClass().add("btns");
            calendrier.getStyleClass().clear();
            calendrier.getStyleClass().add("btnsHome");
            pfe.getStyleClass().add("btns");
            deconnexion.getStyleClass().add("btns");
            stage.getStyleClass().add("btns");
            notes.getStyleClass().add("btns");  
            
            CalendrierController c=new CalendrierController();
            c.setIdUser(id_session_courante);

            FXMLLoader loader=new FXMLLoader();
            loader.setController(c);
            loader.setLocation(getClass().getResource("../Calendrier/Calendrier.fxml"));
            AnchorPane ap = loader.load();
            container.setCenter(ap);
        } catch (IOException ex) {
            Logger.getLogger(Home3Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }    
    
}

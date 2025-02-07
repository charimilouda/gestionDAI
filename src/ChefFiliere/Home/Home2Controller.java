/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Home;
import ChefFiliere.Calendrier.CalendrierController;
import ChefFiliere.Documents.DocumentsController;
import ChefFiliere.Messagerie.MessagerieController;
import ChefFiliere.Etudiants.EtudiantsController;
import ChefFiliere.Matieres.MatieresController;
import ChefFiliere.PFE.PFEController;
import ChefFiliere.Professeurs.ProfesseurController;
import ChefFiliere.Profil.ProfilController;
import ChefFiliere.Stages.StageController;
import Deconnexion.DeconnexionController;



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
 * @author CHARI Milouda
 */
public class Home2Controller implements Initializable {

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
    @FXML private Button etudiants; 
    @FXML private Button messagerie;
    @FXML private Button pfe;
    @FXML private Button profil;
    @FXML private Button professeur;
    @FXML private Button matieres;
    @FXML private Button stages;
    public void switchToDocuments(ActionEvent e) throws IOException
    {
        documents.getStyleClass().clear();
        documents.getStyleClass().add("btnsHome");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        professeur.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        matieres
                .getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
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
        etudiants.getStyleClass().add("btns");
        professeur.getStyleClass().add("btns");
        matieres.getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
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
        professeur.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        matieres.getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
        MessagerieController c=new MessagerieController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Messagerie/Messagerie.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
         
    }
    public void switchToEtudiants(ActionEvent e) throws IOException
    {
         
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        etudiants.getStyleClass().clear();
        professeur.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btnsHome");
        matieres.getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
        EtudiantsController c=new EtudiantsController();
        //c.setIdUser(id_session_courante);//je vais pas l'utiliser
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Etudiants/Etudiants.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
    }
    public void switchToProfesseur(ActionEvent e) throws IOException
    {
        
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        deconnexion.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        professeur.getStyleClass().clear();
        professeur.getStyleClass().add("btnsHome");
        matieres.getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
        ProfesseurController c=new ProfesseurController();
        //c.setIdUser(id_session_courante);//je vais pas l'utiliser
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Professeurs/Professeur.fxml"));
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
        professeur.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        matieres
                .getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
        PFEController c=new PFEController();
        //c.setIdUser(id_session_courante);//je vais pas l'utiliser
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
        etudiants.getStyleClass().add("btns");
        matieres.getStyleClass().add("btns");
        stages.getStyleClass().add("btns");
        ProfilController c=new ProfilController();
        c.setIdUser(id_session_courante);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        System.out.print("id_session_courante"+id_session_courante);//affiche 0
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
        stages.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        matieres.getStyleClass().add("btns");
        professeur.getStyleClass().add("btns");
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
     public void switchToStages(ActionEvent e) throws IOException
     {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        stages.getStyleClass().clear();
        deconnexion.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        matieres.getStyleClass().add("btns");
        professeur.getStyleClass().add("btns");
        stages.getStyleClass().add("btnsHome");
        
        StageController c=new StageController();
        //c.setIdUser(id_session_courante);//je vais pas l'utiliser
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Stages/Stage.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
     }
     public void switchToMatieres(ActionEvent e) throws IOException
     {
        documents.getStyleClass().add("btns");
        messagerie.getStyleClass().add("btns");
        profil.getStyleClass().add("btns");
        calendrier.getStyleClass().add("btns");
        pfe.getStyleClass().add("btns");
        etudiants.getStyleClass().add("btns");
        professeur.getStyleClass().add("btns");
        matieres
                .getStyleClass().clear();
        matieres
                .getStyleClass().add("btnsHome");
        stages.getStyleClass().add("btns");
        
        MatieresController c=new MatieresController();
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Matieres/Matieres.fxml"));
        AnchorPane ap = loader.load();
        container.setCenter(ap);
         
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // try {
            documents.getStyleClass().add("btns");
            messagerie.getStyleClass().add("btns");
            profil.getStyleClass().add("btns");
            calendrier.getStyleClass().add("btnsHome");
            pfe.getStyleClass().add("btns");
            deconnexion.getStyleClass().add("btns");
            professeur.getStyleClass().add("btns");
            etudiants.getStyleClass().add("btns");  
            matieres.getStyleClass().add("btns");
            calendrier.getStyleClass().clear();
            stages.getStyleClass().add("btns");  
            
            CalendrierController c=new CalendrierController();
            c.setIdUser(id_session_courante);
            FXMLLoader loader=new FXMLLoader();
            loader.setController(c);
            loader.setLocation(getClass().getResource("../Calendrier/Calendrier.fxml"));
            AnchorPane ap = loader.load();
            container.setCenter(ap);
            
            /*} catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } catch (IOException ex) {
            Logger.getLogger(Home2Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

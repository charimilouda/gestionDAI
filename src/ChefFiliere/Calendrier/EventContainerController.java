/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Calendrier;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class EventContainerController implements Initializable {
 @FXML
    private Label label_date_debut;

    @FXML
    private Label label_date_fin;

    @FXML
    private Label label_proprietaire;

    @FXML
    private Label label_titre_evenement;

    String date_debut;
    String date_fin;
    String proprietaire;
    String titre;
    String desc;
    String date;
    int id;
    int id_prof;
    int session_courante;
    
    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private ImageView img_delete;

    @FXML
    private ImageView img_update;

    public void setSession_courante(int session_courante) {
        this.session_courante = session_courante;
    }
    public void setIdProf(int id_prof) {
        this.id_prof = id_prof;
    }
    

    public void setId(int id) {
        this.id = id;
    }
    

    public void setDate(String date) {
        this.date = date;
    }
    public void setDescription(String desc)
    {
        this.desc=desc;
    }
    public void setDateDebut(String date_debut)
    {
        this.date_debut=date_debut;
    }
    public void setDateFin(String date_fin)
    {
        this.date_fin=date_fin;
    }
    public void setProprietaire(String proprietaire)
    {
        this.proprietaire=proprietaire;
    }
    public void setTitre(String titre)
    {
        this.titre=titre;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label_date_debut.setText(date_debut);
        label_date_fin.setText(date_fin);
        label_proprietaire.setText(proprietaire);
        label_titre_evenement.setText(titre);
        if(session_courante!=id_prof)
        {
            btn_delete.setVisible(false);
            btn_update.setVisible(false);
            img_update.setVisible(false);
            img_delete.setVisible(false);
        }
    }    
    public void afficherDetails(ActionEvent e) throws IOException
    {
        EventDetailViewController controller=new EventDetailViewController();
        controller.setDate(date);
        controller.setDebut(date_debut);
        controller.setFin(date_fin);
        controller.setDescription(desc);
        controller.setProprietaire(proprietaire);
        controller.setTitre(titre);
        
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("EventDetailView.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    public void modifier(ActionEvent e) throws IOException
    {
        ModifierEvenementController controller=new ModifierEvenementController();
        controller.setDate(date);
        controller.setDebut(date_debut);
        controller.setFin(date_fin);
        controller.setDescription(desc);
        controller.setTitre(titre);
        controller.setId(id);
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("ModifierEvenement.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    public void supprimer(ActionEvent e) throws IOException
    {
        SupprimerEvenementController controller=new SupprimerEvenementController();
        controller.setId(id);
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("SupprimerEvenement.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Calendrier;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class EventDetailViewController implements Initializable {
    String date;String debut;String fin; String description;String proprietaire;String titre;

    public void setDate(String date) {
        this.date = date;
    }

    public void setDebut(String debut) {
        this.debut = debut;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }


    @FXML
    private Label date_ev;

    @FXML
    private Label debut_ev;

    @FXML
    private TextFlow desc_ev;

    @FXML
    private Label fin_ev;

    @FXML
    private Label proprietaire_ev;

    @FXML
    private Label titre_ev;
    
    @FXML
    private Button btn_quitter;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        date_ev.setText(date);
        debut_ev.setText(debut);
        fin_ev.setText(fin);
        proprietaire_ev.setText(proprietaire);
        titre_ev.setText(titre);
        Text t1=new Text(description);
        t1.setId("description");
        desc_ev.getChildren().add(t1);
    }    
    public void quitter(ActionEvent e)
    {
        Stage stage = (Stage) btn_quitter.getScene().getWindow();
        stage.close();
    }
}

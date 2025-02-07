/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Calendrier;


import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private DatePicker date_ev;

    @FXML
    private ChoiceBox<String> debut_ev;

    @FXML
    private TextArea description_ev;

    @FXML
    private ChoiceBox<String> fin_ev;

    @FXML
    private TextField titre_ev;
    
    private String[] horaire={"08:00","09:00","10:00","11:00","12:00","13:00","14:00","15:00","16:00","17:00","18:00"};

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_enregister;
            
    @FXML
    private Label label_msg_err;
    String date;String debut;String fin; String description;String titre; int id;

    public void setId(int id) {
        this.id = id;
    }

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

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        debut_ev.getItems().addAll(horaire);
        fin_ev.getItems().addAll(horaire);
        debut_ev.setValue(debut);
        fin_ev.setValue(fin);
        description_ev.setText(description);
        titre_ev.setText(titre);
        date_ev.setValue(LocalDate.parse(date));
        
    }    
    public void modifierEvenement(ActionEvent e) throws SQLException
    {
        Evenement ev=new Evenement();
        ev.nom=titre_ev.getText();
        ev.debut=debut_ev.getValue();
        ev.fin=fin_ev.getValue();
        ev.description=description_ev.getText();
        ev.date=date_ev.getValue();
        ev.id_ev=id;
        if(ev.nom.isBlank()||ev.date==null||debut_ev.getSelectionModel().isEmpty()||fin_ev.getSelectionModel().isEmpty()) //controle saisie
	{
            label_msg_err.setText("Veuillez remplir les champs");
	}
	else
	{
             String url="jdbc:mysql://localhost/dai";
             Connection conn=DriverManager.getConnection(url, "root", "");
             PreparedStatement ps1=conn.prepareStatement("update evenement set nom_ev=?,date_ev=?,debut_ev=?,fin_ev=?,description_ev=? where id_ev=?");
             ps1.setString(1,ev.nom);
             ps1.setDate(2,Date.valueOf(ev.date));
             ps1.setString(3,ev.debut);
             ps1.setString(4, ev.fin);
             ps1.setString(5,ev.description );
             ps1.setInt(6,ev.id_ev);
             ps1.executeUpdate();
             ps1.close();
             conn.close();
             

             //optional: add a small popup window that hides itself after 10 seconds z3ma success
             Stage stage = (Stage) btn_enregister.getScene().getWindow();
             stage.close(); 
        }
    }
    public void annuler(ActionEvent e)
    {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
}

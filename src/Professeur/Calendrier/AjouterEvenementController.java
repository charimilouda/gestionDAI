/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.Calendrier;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author Doha Ess
 */
public class AjouterEvenementController implements Initializable
{

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
    
    private String[] horaire={"08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30"};

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_enregister;
            
    @FXML
    private Label label_msg_err;

    int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        debut_ev.getItems().addAll(horaire);
        fin_ev.getItems().addAll(horaire);
        System.out.println("id user at init"+id_user);
    }
    
    public void ajouterEvenement(ActionEvent e) throws SQLException
    {
        
        String name=null;
        ArrayList<Evenement> tableauEv=new ArrayList<Evenement>();
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");
        /*get nom*/
        PreparedStatement ps2=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
        ps2.setInt(1,id_user); 
	ResultSet res2=ps2.executeQuery();
	if(res2.next()){
            name=res2.getString("nom_prof")+" "+res2.getString("prenom_prof");
        }
        
        String nom=titre_ev.getText();
        String debut=debut_ev.getValue();
        String fin=fin_ev.getValue();
        String description=description_ev.getText();
        String date=date_ev.getValue().toString();
        String proprietaire=name;
        if(nom.isBlank()||date==null||debut_ev.getSelectionModel().isEmpty()||fin_ev.getSelectionModel().isEmpty()) //controle saisie
	{
            label_msg_err.setText("Veuillez remplir les champs");
	}
	else
	{
             int id_ev=0;
             Statement s1=conn.createStatement();
             ResultSet res1=s1.executeQuery("SELECT id_ev FROM evenement ORDER BY id_ev DESC LIMIT 1;");
             while(res1.next())
             {
                 id_ev=res1.getInt(1);
             }
             id_ev++;

             PreparedStatement ps1=conn.prepareStatement("insert into evenement values(?,?,?,?,?,?,?)");
             ps1.setInt(1, id_ev);
             ps1.setInt(2,id_user); 
             ps1.setString(3,nom);
             ps1.setDate(4,Date.valueOf(date));
             ps1.setString(5,debut);
             ps1.setString(6, fin);
             ps1.setString(7,description);
             ps1.executeUpdate();
             ps1.close();
             conn.close();
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

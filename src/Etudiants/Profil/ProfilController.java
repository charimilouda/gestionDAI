/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Profil;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author chari milouda
 */
public class ProfilController implements Initializable {
    private int id_user;
    
    @FXML
    private Label nom_prenom;
    @FXML
    private Label adresse;

    @FXML
    private Label adresse1;

    @FXML
    private Label civilite;

    @FXML
    private Label cne;

    @FXML
    private Label date_n;

    @FXML
    private Label email;

    @FXML
    private Label nmassar;

    @FXML
    private Label lieu_n;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;
    
    @FXML
    private Label napoge;


    @FXML
    private TextField telephone;
          
    String nom_etudiant="";
    String prenom_etudiant="";
    String civilite_etudiant="";
    String cne_etudiant="";
    String email_etudiant="";
    LocalDate date_n_etudiant=null;
    String lieu_n_etudiant="";
    String adresse_etudiant="";
    String telephone_etudiant="";
    String numero_massar="";
    String numero_apoge="";
    public void setIdUser(int id)
    {
        id_user=id;
    }
    
    public void pageProfil() throws SQLException, IOException
    {   
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("Select * from etudiant where id_etudiant=?");
                    System.out.print("pageprofil"+id_user);//ok

        ps1.setInt(1,id_user);
        ResultSet res1=ps1.executeQuery();
        while (res1.next())
        {               
            nom_etudiant=res1.getString("nom_etudiant");
            prenom_etudiant=res1.getString("prenom_etudiant");
            cne_etudiant=res1.getString("cin_etudiant");
            telephone_etudiant=res1.getString("tel_etudiant");
            civilite_etudiant=res1.getString("sexe_etudiant");
            lieu_n_etudiant=res1.getString("lieuNaissance_etudiant");
            date_n_etudiant=res1.getDate("dateNaissance_etudiant").toLocalDate();
            email_etudiant=res1.getString("email_etudiant");
            adresse_etudiant=res1.getString("adresse_etudiant");
            numero_massar=res1.getString("numeroMassar_etudiant");
            numero_apoge=res1.getString("numeroApogee_etudiant");
            System.out.println(nom_etudiant+" "+prenom_etudiant);//heta lehna kolchi khedam
            nom.setText(nom_etudiant);
            prenom.setText(prenom_etudiant);
            nom_prenom.setText(nom_etudiant+" "+prenom_etudiant);
            cne.setText(cne_etudiant);
            telephone.setText(telephone_etudiant);
            civilite.setText(civilite_etudiant);
            lieu_n.setText(lieu_n_etudiant);
            date_n.setText(date_n_etudiant.toString());
            email.setText(email_etudiant);
            adresse1.setText(adresse_etudiant);
            nmassar.setText(numero_massar);
            napoge.setText(numero_apoge);
        }
        
        conn.close();

        
    }
   

    @Override
    public void initialize(URL url, ResourceBundle rb) {
                try {
                    System.out.print("ok");
            pageProfil();
        } catch (SQLException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
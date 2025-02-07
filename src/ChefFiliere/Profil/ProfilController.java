/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Profil;


import Authentification.AuthentificationController;

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
    private Label err_tel;

    @FXML
    private Label fonction;

    @FXML
    private Label lieu_n;

    @FXML
    private Label nom;

    @FXML
    private Label prenom;

    @FXML
    private TextField telephone;
          
    String nom_prof="";
    String prenom_prof="";
    String civilite_prof="";
    String cne_prof="";
    String email_prof="";
    LocalDate date_n_prof=null;
    String lieu_n_prof="";
    String adresse_prof="";
    String fontion_prof="";
    String telephone_prof="";
    public void setIdUser(int id)
    {
        id_user=id;
    }
    
    public void pageProfil() throws SQLException, IOException
    {   
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("Select * from professeur where id_prof=?");
                    System.out.print("pageprofil"+id_user);//ok

        ps1.setInt(1,id_user);
        ResultSet res1=ps1.executeQuery();
        while (res1.next())
        {               
            nom_prof=res1.getString("nom_prof");
            prenom_prof=res1.getString("prenom_prof");
            cne_prof=res1.getString("cin_prof");
            telephone_prof=res1.getString("tel_prof");
            civilite_prof=res1.getString("sexe_prof");
            lieu_n_prof=res1.getString("lieuNaissance_prof");
            date_n_prof=res1.getDate("dateNaissance_prof").toLocalDate();
            email_prof=res1.getString("email_prof");
            adresse_prof=res1.getString("adresse_prof");
            fontion_prof=res1.getString("fonction_prof");
            System.out.println(nom_prof+" "+prenom_prof);//heta lehna kolchi khedam
            nom.setText(nom_prof);
            prenom.setText(prenom_prof);
            nom_prenom.setText(nom_prof+" "+prenom_prof);
            cne.setText(cne_prof);
            telephone.setText(telephone_prof);
            civilite.setText(civilite_prof);
            lieu_n.setText(lieu_n_prof);
            date_n.setText(date_n_prof.toString());
            email.setText(email_prof);
            adresse1.setText(adresse_prof);
            fonction.setText(fontion_prof);
        }
        
        conn.close();

        
    }
  
   public void modifierCordonnées(ActionEvent e) throws SQLException
   {
       String tel=telephone.getText();
       if(tel.length()== 10 )
       {    
            boolean isNumeric = true;
            for (int i = 0; i < tel.length(); i++) {
                if (!Character.isDigit(tel.charAt(i))) {
                    isNumeric = false;
                }
            }
            if(isNumeric==true)
            {   
                err_tel.setText("");
                String url="jdbc:mysql://localhost/dai";
                Connection conn=DriverManager.getConnection(url, "root", "");
                PreparedStatement ps1=conn.prepareStatement("update professeur set tel_prof=? where id_prof=?");
                ps1.setString(1, tel);
                ps1.setInt(2,id_user);//je dois modifier l'id
                int nbr=ps1.executeUpdate();
                System.out.println(nbr+" lignes modifiées");
                conn.close();
                
            }
            else{
                err_tel.setText("Invalid ! ");
            }
        }
       else{
            
                err_tel.setText("Invalid ! ");
            
       }
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
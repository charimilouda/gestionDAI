/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Professeurs;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class AjouterProfController implements Initializable {

    @FXML private TextField adresse; 
    @FXML private Button btn_ajouter; 
    @FXML  private Button btn_annuler;
    @FXML private TextField cin; 
    @FXML  private TextField email; 
    @FXML private TextField fonction; 
    @FXML private ChoiceBox<String> genre; 
    @FXML private TextField lieuNaissance; 
    @FXML private DatePicker naissance; 
    @FXML private TextField nom; 
    @FXML private TextField prenom; 
    @FXML
    private TextField telephone;
     private String[] genre__={"Homme","Femme"};
    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
    @FXML
    void verifierSaisie(ActionEvent event) throws IOException, SQLException{
        
            String nom_=nom.getText();
            String prenom_ =prenom.getText() ;
            LocalDate naissance_=naissance.getValue();
            String genre_=genre.getValue();
            String telephone_=telephone.getText();
            String lieu_=lieuNaissance.getText();
            String cin_=cin.getText();
            String adresse_=adresse.getText();
            String fonction_=fonction.getText();
            String email_=email.getText();
            if(nom_.isBlank()||prenom_.isBlank() ||naissance_==null||genre.getSelectionModel().isEmpty()||fonction_.isBlank()||telephone_.isBlank()||email_.isBlank())
            {
                Stage stage =new Stage();
                Parent root=FXMLLoader.load(getClass().getResource("alertChampsVides.fxml")); 
                Scene scene=new Scene(root);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
            }
            else{
                if(telephone_.length()== 10 ||telephone_.length()== 14)
                {
                    boolean isNumeric = true;
                    for (int i = 0; i < telephone_.length(); i++) {
                        if (!Character.isDigit(telephone_.charAt(i))) {
                            isNumeric = false;
                        }}
                        if(isNumeric==true)
                        {   
                            ajouter(nom_,prenom_,naissance_,genre_,telephone_,lieu_,cin_,adresse_,fonction_,email_);
                        }
                        else{
                            Stage stage =new Stage();
                            Parent root=FXMLLoader.load(getClass().getResource("Invalid.fxml")); 
                            Scene scene=new Scene(root);
                            stage.setResizable(false);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();
                        }
                      
                }
                else{
                    Stage stage =new Stage();
                    Parent root=FXMLLoader.load(getClass().getResource("Invalid.fxml")); 
                    Scene scene=new Scene(root);
                    stage.setResizable(false);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.show();
                }
            }   
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        genre.getItems().addAll(genre__);
    }    

    private void ajouter(String nom_, String prenom_, LocalDate naissance_, String genre_, String telephone_, String lieu_, String cin_, String adresse_, String fonction_, String email_) throws SQLException {
        
        
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");
        Statement s1=conn.createStatement();
        ResultSet res1=s1.executeQuery("SELECT id_prof FROM professeur ORDER BY id_prof DESC LIMIT 1; ");
        if(res1.next())
        {
            int id_prof=res1.getInt(1);
            id_prof++;
            PreparedStatement ps1=conn.prepareStatement("insert into professeur values (?,?,?,?,?,?,?,?,?,?,?)");
            ps1.setInt(1, id_prof);
            ps1.setString(2, email_);
            ps1.setString(3, nom_);
            ps1.setString(4, prenom_);
            ps1.setString(5, genre_);
            ps1.setString(6, telephone_);
            ps1.setString(7, adresse_);
            ps1.setDate(8, Date.valueOf(naissance_));
            ps1.setString(9, lieu_);
            ps1.setString(10, cin_);
            ps1.setString(11, fonction_);
            ps1.executeUpdate();
            /*ps1.close();*/
            conn.close();
            Stage stage = (Stage) btn_ajouter.getScene().getWindow();
             stage.close();

    }
        conn.close();
    }
    
}

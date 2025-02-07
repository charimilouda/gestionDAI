/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Etudiants;
import java.io.IOException;
import java.sql.ResultSet;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class ModifierEtudiantController implements Initializable {
  
    int v_id;
    private String[] genre__={"Homme","Femme"};

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }
    @FXML private TextField adresse; 
    @FXML private Button btn_annuler; 
    @FXML private Button btn_modifier;
    @FXML private TextField cin; 
    @FXML private TextField email;  
    @FXML private ChoiceBox<String> genre;
    @FXML private TextField lieuNaissance;  
    @FXML private TextField massar; 
    @FXML private DatePicker naissance;
    @FXML private TextField nom;
    @FXML private TextField numeroapg; 
    @FXML private TextField prenom; 
    @FXML private TextField telephone;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void verifierSaisie(ActionEvent event) throws IOException, SQLException {
        String nom_=nom.getText();
            String prenom_ =prenom.getText() ;
            LocalDate naissance_=naissance.getValue();
            String genre_=genre.getValue();
            String numApg_=numeroapg.getText();
            String telephone_=telephone.getText();
            String lieu_=lieuNaissance.getText();
            String cin_=cin.getText();
            String adresse_=adresse.getText();
            String massar_=massar.getText();
            String email_=email.getText();
            if(nom_.isBlank()||prenom_.isBlank() ||naissance_==null||numApg_.isBlank()||telephone_.isBlank()||lieu_.isBlank()||email_.isBlank())
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
                modifier(nom_,prenom_,naissance_,genre_,numApg_,telephone_,lieu_,cin_,adresse_,massar_,email_);
            }
        
    }
    public void modifier(String nom_,String prenom_,LocalDate naissance_,String genre_,String numApg_,String telephone_,String lieu_,String cin_,String adresse_,String massar_,String email_) throws SQLException
    {
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps1=conn.prepareStatement("update etudiant set email_etudiant=?,nom_etudiant=?,prenom_etudiant=?,sexe_etudiant=?,tel_etudiant=?,adresse_etudiant=?,dateNaissance_etudiant=?,lieuNaissance_etudiant=?,cin_etudiant=?,numeroApogee_etudiant=?,numeroMassar_etudiant=? where id_etudiant=?");
            ps1.setString(1, email_);
            ps1.setString(2, nom_);
            ps1.setString(3, prenom_);
            ps1.setString(4, genre_);
            ps1.setString(5, telephone_);
            ps1.setString(6, adresse_);
            ps1.setDate(7, Date.valueOf(naissance_));
            ps1.setString(8, lieu_);
            ps1.setString(9, cin_);
            ps1.setString(10, numApg_);
            ps1.setString(11, massar_);
            ps1.setInt(12, v_id);
            ps1.executeUpdate();
            ps1.close();
            conn.close();
            Stage stage = (Stage) btn_modifier.getScene().getWindow();
            stage.close(); 
    }
    public void getInfos() throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("select * FROM etudiant where id_etudiant=?");
        ps1.setInt(1, v_id);
        ResultSet res=ps1.executeQuery();
        while(res.next())
        {
            String v_nom=res.getString("nom_etudiant");
            String v_prenom=res.getString("prenom_etudiant");
            String v_email=res.getString("email_etudiant");
            String v_adresse=res.getString("adresse_etudiant");
            String v_cin=res.getString("cin_etudiant");
            String v_numApg=res.getString("numeroApogee_etudiant");
            String v_lieuNaissance=res.getString("lieuNaissance_etudiant");
            String v_massar=res.getString("numeroMassar_etudiant");
            String v_telephone=res.getString("tel_etudiant");
            String v_genre=res.getString("sexe_etudiant");
            LocalDate  v_naissance=res.getDate("dateNaissance_etudiant").toLocalDate();
            nom.setText(v_nom);
            prenom.setText(v_prenom);
            email.setText(v_email);
            genre.getItems().addAll(genre__);
            genre.setValue(v_genre);
            adresse.setText(v_adresse);
            numeroapg.setText(v_numApg);
            lieuNaissance.setText(v_lieuNaissance);
            cin.setText(v_cin);
            telephone.setText(v_telephone);
            massar.setText(v_massar);
            naissance.setValue(v_naissance);
        }
        conn.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getInfos();
        } catch (SQLException ex) {
            Logger.getLogger(ModifierEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

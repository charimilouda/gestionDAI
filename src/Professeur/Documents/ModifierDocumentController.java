/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.Documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class ModifierDocumentController implements Initializable {

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_enregistrer;

    @FXML
    private Label label_msg_err;

    @FXML
    private Label selection_doc;

    @FXML
    private TextField titre_doc;

    int id; 
    String name,titre;
    long taille;
    File file;
    FileInputStream input;
    Blob contenu;
    Blob contenuUpdated;
    boolean contenuisfreed=false;
    public void setName(String name) {
        this.name = name;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTaille(Long taille) {
        this.taille = taille;
    }
    public void setContenu(Blob contenu) {
        this.contenu = contenu;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void choisir(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        FileChooser fc=new FileChooser();
        file=fc.showOpenDialog(null);
	input=new FileInputStream(file);
        byte[] arr = new byte[(int)file.length()];
        input.read(arr);
        input.close();
        contenuUpdated=new javax.sql.rowset.serial.SerialBlob(arr);
        name=file.getName();
        taille=file.length();
        selection_doc.setText(file.getName());
        contenuisfreed=true;
    }

    @FXML
    void enregistrer(ActionEvent event) throws SQLException {
        LocalDateTime date=LocalDateTime.now();
        String url="jdbc:mysql://localhost/dai";
	java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        
	java.sql.PreparedStatement ps1=conn.prepareStatement("update document set nom_doc=?,date_depot=?,titre_doc=?,taille_doc=?,extension_doc=?,contenu_doc=? where id_doc=?");
        ps1.setString(1, name.substring(0, name.lastIndexOf('.')));
        ps1.setTimestamp(2, Timestamp.valueOf(date));
        ps1.setString(3,titre_doc.getText());
        ps1.setLong(4,taille);
        ps1.setString(5,name.substring(name.lastIndexOf(".")+1));
        if(contenuisfreed==true)
        {
            ps1.setBlob(6,contenuUpdated);
        }
        else
        {
            ps1.setBlob(6,contenu);
        }
	
        ps1.setInt(7, id);
	ps1.executeUpdate();
        ps1.close();
        conn.close();
        
        Stage stage = (Stage) btn_enregistrer.getScene().getWindow();
        stage.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        charger();
    }    
    
    public void charger()
    {
        titre_doc.setText(titre);
        selection_doc.setText(name);
    }
    
}

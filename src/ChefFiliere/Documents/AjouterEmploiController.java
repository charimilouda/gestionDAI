/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class AjouterEmploiController implements Initializable {

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
    File file;
    FileInputStream input;
        int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    public void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }

    public void choisir(ActionEvent event) throws FileNotFoundException {
        FileChooser fc=new FileChooser();
        file=fc.showOpenDialog(null);
	input=new FileInputStream(file);
        selection_doc.setText(file.getName());
    }

    public void enregistrer(ActionEvent event) throws SQLException {
        if(titre_doc.getText().isBlank()||file==null)
        {
            label_msg_err.setText("Veuillez remplir les informations du document!");
        }
        else
        {
            int id_doc=0;
            LocalDateTime date=LocalDateTime.now();
            String url="jdbc:mysql://localhost/dai";
            java.sql.Connection conn=DriverManager.getConnection(url, "root", "");

            Statement s1=conn.createStatement();
            ResultSet res1=s1.executeQuery("SELECT id_doc FROM document ORDER BY id_doc DESC LIMIT 1;");
                 while(res1.next())
                 {
                     id_doc=res1.getInt(1);
                 }
                 id_doc++;

            java.sql.PreparedStatement ps1=conn.prepareStatement("insert into document values(?,?,?,?,?,?,?,?,?)");
            ps1.setInt(1, id_doc);
            String name=file.getName();
            ps1.setString(2, name.substring(0, name.lastIndexOf('.')));
            ps1.setInt(3, id_user);
            ps1.setTimestamp(4, Timestamp.valueOf(date));
            ps1.setString(5,titre_doc.getText());
            ps1.setLong(6,file.length());
            ps1.setString(7,file.getName().substring(file.getName().lastIndexOf(".")+1));
            ps1.setBinaryStream(8,input);
            ps1.setInt(9, 4); 
            ps1.executeUpdate();
            ps1.close();
            conn.close();
            Stage stage = (Stage) btn_enregistrer.getScene().getWindow();
            stage.close();
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

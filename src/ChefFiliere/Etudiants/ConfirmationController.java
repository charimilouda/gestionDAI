/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Etudiants;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class ConfirmationController implements Initializable {
     int id;

    public void setId(int id) {
        this.id = id;
    }
        @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_confirmer;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage)btn_annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void  supprimer(ActionEvent event) throws SQLException {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("delete from etudiant where id_etudiant=?");
        ps1.setInt(1, id);
        int nbr=ps1.executeUpdate();
        System.out.println(nbr+" lignes supprimées avec succèes.");
        Stage stage = (Stage)btn_confirmer.getScene().getWindow();
        stage.close();
        conn.close();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

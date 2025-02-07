/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Documents;


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
 * @author Doha Ess
 */
public class SupprimerDocumentController implements Initializable {

    int id;

    public void setId(int id) {
        this.id = id;
    }
        @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_supprimer;

    @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }

    @FXML
    void supprimer(ActionEvent event) throws SQLException {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("delete from document where id_doc=?");
        ps1.setInt(1,id);
        ps1.executeUpdate();
        ps1.close();
        conn.close();
        Stage stage = (Stage) btn_supprimer.getScene().getWindow();
        stage.close(); 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

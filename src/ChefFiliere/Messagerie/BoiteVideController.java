/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Messagerie;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class BoiteVideController implements Initializable {
    private int id_user;
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    BorderPane bp;

    public void setBp(BorderPane bp) {
        this.bp = bp;
    }   
    public void nvMessage(ActionEvent e) throws IOException
    {
        NewController c=new NewController();
        c.setIdUser(id_user);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Messagerie/new.fxml"));
        AnchorPane ap = loader.load();
        bp.setCenter(ap);
            
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

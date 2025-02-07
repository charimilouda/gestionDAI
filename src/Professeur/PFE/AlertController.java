/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.PFE;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class AlertController implements Initializable {
    
    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @FXML
    private VBox containerV;

    public void setContainerV(VBox containerV) {
        this.containerV = containerV;
    }

    
    @FXML
    private Button save;

    public void setSave(Button save) {
        this.save = save;
    }


    
    
    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_confirmer;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void confirmer(ActionEvent e) throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        int i=0;
        String sujets="";
        ObservableList<Node> e3=containerV.getChildren();
        for(i=0;i<e3.size();i++)
        {
                Node n=e3.get(i);
                HBox h=(HBox) n;
                ObservableList<Node> elts_hbox=h.getChildren();
                Node n1=elts_hbox.get(1);
                TextField t1=(TextField)n1;
                sujets+=t1.getText()+"//";
        }
        
        java.sql.PreparedStatement ps2=conn.prepareStatement("update sujetpfe set intitule_sujet=? where id_prof=?");
        ps2.setString(1, sujets);
        ps2.setInt(2, idUser);
        ps2.executeUpdate();
        ps2.close();
        conn.close();
        save.setDisable(true);
        Stage stage = (Stage) btn_confirmer.getScene().getWindow();
        stage.close();
    }
    
    public void annuler(ActionEvent e)
    {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
}

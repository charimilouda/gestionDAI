/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;

import com.sun.jdi.connect.spi.Connection;
import java.net.URL;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class EnregistrerPFEController implements Initializable {
    @FXML
    private Button generer;

    @FXML
    private Button save;
    
    @FXML
    private Button confirm;
    
    @FXML
    private Button annuler;

    public void setGenerer(Button generer) {
        this.generer = generer;
    }

    public void setSave(Button save) {
        this.save = save;
    }

    public void setPFE(ArrayList<String> PFE) {
        this.PFE = PFE;
    }
    
    ArrayList<String> PFE;
    
    public void confirm(ActionEvent e) throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        for(String s:PFE)
        {
            String[] tab=s.split("//");
            int idpfe=Integer.parseInt(tab[0]);
            String sujet=tab[1];
            int idgrp=Integer.parseInt(tab[2]);
            int idprof=Integer.parseInt(tab[3]);
            
            System.out.println(idpfe+" "+sujet+" "+idgrp+" "+idprof);
            PreparedStatement ps1=conn.prepareStatement("insert into pfe values(?,?,?,?)");
             ps1.setInt(1, idpfe);
             ps1.setString(2, sujet);
             ps1.setInt(3, idgrp);
             ps1.setInt(4, idprof);
             ps1.executeUpdate();
             //close
        }
        conn.close();         
        save.setDisable(true);
        generer.setDisable(true);
        Stage stage = (Stage) confirm.getScene().getWindow();
        stage.close(); 
    }
        public void annuler(ActionEvent e)
    {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

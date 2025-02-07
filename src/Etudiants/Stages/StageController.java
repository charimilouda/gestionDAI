/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Stages;

import java.net.URL;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class StageController implements Initializable {

    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @FXML
    private DatePicker debut;

    @FXML
    private DatePicker fin;

    @FXML
    private TextField lieu;

    @FXML
    private TextField sujet;
    
    @FXML
    private Label label_msg_err;

    @FXML
    void save(ActionEvent event) throws SQLException {
        if(lieu.getText().isBlank()||sujet.getText().isBlank()||debut.getValue()==null||fin.getValue()==null)
        {
            label_msg_err.setText("Veuillez remplir tous les champs!");
        }
        else
        {
            int id_stage=0;
            String url="jdbc:mysql://localhost/dai";
            java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
            LocalDate d=debut.getValue();
            LocalDate f=fin.getValue();
            String sujetStage=sujet.getText();
            String lieuStage=lieu.getText();
            PreparedStatement ps1=conn.prepareStatement("select * from stage where id_etudiant=?");
            ps1.setInt(1,idUser);
            ResultSet res1=ps1.executeQuery();
            if(res1.next())
            {
                PreparedStatement ps2=conn.prepareStatement("update stage set debut_stage=?, fin_stage=?, sujet_stage=?,lieu_stage=? where id_etudiant=?");
                ps2.setDate(1, Date.valueOf(d));
                ps2.setDate(2, Date.valueOf(f));
                ps2.setString(3,sujetStage);
                ps2.setString(4, lieuStage);
                ps2.setInt(5,idUser);
                ps2.executeUpdate();
                ps2.close();
            }
            else
            {      
                Statement s1=conn.createStatement();
                ResultSet res2=s1.executeQuery("SELECT id_stage FROM stage ORDER BY id_stage DESC LIMIT 1;");
                while(res2.next())
                {
                    id_stage=res2.getInt("id_stage");
                }
                id_stage++;
                PreparedStatement ps2=conn.prepareStatement("insert into stage values(?,?,?,?,?,?)");
                ps2.setInt(1, id_stage);
                ps2.setInt(2,idUser); 
                ps2.setDate(3,Date.valueOf(d));
                ps2.setDate(4,Date.valueOf(f));
                ps2.setString(5,sujetStage);
                ps2.setString(6, lieuStage);
                ps2.executeUpdate();
                ps2.close();          
            }
            conn.close();
            label_msg_err.setText("");
        }
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerStage();
        } catch (SQLException ex) {
            Logger.getLogger(StageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void chargerStage() throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("select * from stage where id_etudiant=?");
        ps1.setInt(1,idUser);
        ResultSet res1=ps1.executeQuery();
        if(res1.next())
        {
            sujet.setText(res1.getString("sujet_stage"));
            lieu.setText(res1.getString("lieu_stage"));
            debut.setValue(res1.getDate("debut_stage").toLocalDate());
            fin.setValue(res1.getDate("fin_stage").toLocalDate());
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.PFE;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class PFEController implements Initializable {

    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    @FXML
    VBox containerV;
    public void configurerGroupe(ActionEvent e) throws IOException
    {
        GroupeContainerController controller=new GroupeContainerController();
        controller.setIdUser(idUser);
        
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("GroupeContainer.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    public void chargerPFE() throws IOException, SQLException
    {
        ArrayList<HBox> list=new ArrayList<>();
        
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        java.sql.PreparedStatement ps1=conn.prepareStatement("select id_groupe_etudiant from groupe_etudiant where id_etudiant=?");
        ps1.setInt(1,idUser);
        ResultSet res1=ps1.executeQuery();
        while(res1.next())
        {
            int id_grp=res1.getInt("id_groupe_etudiant");
            java.sql.PreparedStatement ps2=conn.prepareStatement("select * from pfe where id_groupe_etudiant=?");
            ps2.setInt(1,id_grp);
            ResultSet res2=ps2.executeQuery();
            while(res2.next())
            {
                String nomprof="";
                int id_prof=res2.getInt("id_encadrant");
                String sujet=res2.getString("sujet_pfe");
                java.sql.PreparedStatement ps3=conn.prepareStatement("select nom_prof,prenom_prof from professeur,pfe where id_encadrant=id_prof and id_groupe_etudiant=?;");
                ps3.setInt(1,id_grp);
                ResultSet res3=ps3.executeQuery();
                while(res3.next())
                {
                    nomprof=res3.getString("nom_prof")+" "+res3.getString("prenom_prof");
                }
                HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("PFEContainer.fxml"));
                containerH = loader.load();
                PFEContainerController controller=loader.getController();
                controller.setEncadrant(nomprof);
                controller.setSujet(sujet);
                list.add(containerH);
            }
            
        }
        containerV.getChildren().addAll(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerPFE();
        } catch (IOException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    
}

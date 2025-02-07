/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Stages;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class StageController implements Initializable {

    @FXML
    private VBox containerV;
    
    public void chargerStage() throws SQLException, IOException
    {
        ArrayList<HBox> list=new ArrayList<>();
        
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        java.sql.Statement s1=conn.createStatement();
        ResultSet res1=s1.executeQuery("select * from stage");
        while(res1.next())
        {
            int id_etud=res1.getInt("id_etudiant");
            String nomEtud="";
            java.sql.PreparedStatement ps2=conn.prepareStatement("select nom_etudiant,prenom_etudiant from etudiant where id_etudiant=?;");
            ps2.setInt(1,id_etud);
            ResultSet res2=ps2.executeQuery();
            while(res2.next())
            {
                nomEtud=res2.getString("nom_etudiant")+" "+res2.getString("prenom_etudiant")+"  ";
            }

            String sujet=res1.getString("sujet_stage");
            String lieu=res1.getString("lieu_stage");
            String datedebut=res1.getDate("debut_stage").toString();
            String datefin=res1.getDate("fin_stage").toString();
            HBox containerH=new HBox();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("StageView.fxml"));
            containerH = loader.load();
            StageViewController c=loader.getController();
            c.setDebut(datedebut);
            c.setFin(datefin);
            c.setLieu(lieu);
            c.setSujet(sujet);
            c.setEtudiant(nomEtud);
            list.add(containerH);
        }
        containerV.getChildren().addAll(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerStage();
        } catch (SQLException ex) {
            Logger.getLogger(StageController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

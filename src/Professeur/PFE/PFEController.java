/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.PFE;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    private VBox containerSujet;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerPFE();
        } catch (SQLException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
    public void openSujets(ActionEvent e) throws IOException
    {
        SujetController controller=new SujetController();
        controller.setIdUser(idUser);
        
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("Sujet.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    public void chargerPFE() throws SQLException, IOException
    {
        ArrayList<HBox> list=new ArrayList<>();
        
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        java.sql.PreparedStatement ps1=conn.prepareStatement("select * from pfe where id_encadrant=?");
        ps1.setInt(1,idUser);
        ResultSet res1=ps1.executeQuery();
        while(res1.next())
        {
            
            String grpEtud="";
            int id_grp=res1.getInt("id_groupe_etudiant");
            java.sql.PreparedStatement ps2=conn.prepareStatement("select nom_etudiant,prenom_etudiant from etudiant,groupe_etudiant where etudiant.id_etudiant=groupe_etudiant.id_etudiant and groupe_etudiant.id_groupe_etudiant=?;");
            ps2.setInt(1,id_grp);
            ResultSet res2=ps2.executeQuery();
            while(res2.next())
            {
                grpEtud+=res2.getString("nom_etudiant")+" "+res2.getString("prenom_etudiant")+"  ";
            }
            String sujet=res1.getString("sujet_pfe");
            HBox containerH=new HBox();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("grpContainer.fxml"));
            containerH = loader.load();
            GrpContainerController controller=loader.getController();
            controller.setSujet(sujet);
            controller.setGroupe(grpEtud);
            list.add(containerH);
        }
        containerSujet.getChildren().addAll(list);
    }
}
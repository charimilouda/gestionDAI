/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Documents;

import Professeur.Documents.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class DocumentsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox containerV;
    
    int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    
    String nom_user="XXX";
    public void chargerDoc() throws SQLException, IOException
    {
        String nomChef=null;
        
        ArrayList<HBox> list=new ArrayList<HBox>();
        
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        Statement s1=conn.createStatement();
        ResultSet res4=s1.executeQuery("select * from document where destination=2 or destination=3 order by date_depot desc");
        while(res4.next())
        {
                PreparedStatement ps2=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
                ps2.setInt(1, res4.getInt("id_proprietaire"));
                ResultSet res2=ps2.executeQuery();
                if(res2.next()){nom_user=res2.getString("nom_prof")+" "+res2.getString("prenom_prof");}
                
                HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DocContainerRespo.fxml"));
                containerH = loader.load();
                DocContainerRespoController controller=loader.getController();
                controller.setNom(res4.getString("nom_doc"));
                //formatter la date
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime dateTime=res4.getTimestamp("date_depot").toLocalDateTime();
                controller.setDateDepot(dateTime.format(formatter)); 
                
                
                controller.setTitre(res4.getString("titre_doc"));
                controller.setTaille(res4.getString("taille_doc"));
                controller.setExtension(res4.getString("extension_doc"));
                controller.setProprietaire(nom_user);
                controller.setContenu(res4.getBlob("contenu_doc"));
                list.add(containerH);
        }
        
        
        
        Statement s3=conn.createStatement();
        ResultSet res1=s3.executeQuery("Select * from document where destination=0");

        
        
        while(res1.next())
            {
                int id_prof=res1.getInt("id_proprietaire");   
                PreparedStatement ps2=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
                ps2.setInt(1, id_prof);
                ResultSet res2=ps2.executeQuery();
                if(res2.next()){nom_user=res2.getString("nom_prof")+" "+res2.getString("prenom_prof");}
                HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DocContainer.fxml"));
                containerH = loader.load();
                DocContainerController controller=loader.getController();
                controller.setIddoc(res1.getInt("id_doc"));
                controller.setNom(res1.getString("nom_doc"));
                
                //formatter la date
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime dateTime=res1.getTimestamp("date_depot").toLocalDateTime();
                controller.setDateDepot(dateTime.format(formatter)); 
                
                
                controller.setTitre(res1.getString("titre_doc"));
                controller.setTaille(res1.getString("taille_doc"));
                controller.setExtension(res1.getString("extension_doc"));
                controller.setProprietaire(nom_user);
                controller.setContenu(res1.getBlob("contenu_doc"));
                list.add(containerH);
            }
        containerV.getChildren().addAll(list);

        conn.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerDoc();
        } catch (SQLException ex) {
            Logger.getLogger(DocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
        public void emploi(ActionEvent e ) throws IOException
    {
        EmploiController c=new EmploiController();
        FXMLLoader loader=new FXMLLoader();
        c.setIdUser(id_user);
        loader.setController(c);
        loader.setLocation(getClass().getResource("Emploi.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
        
    public void refresh(ActionEvent e)
        {
             try {
            containerV.getChildren().clear();
            chargerDoc();
        } catch (SQLException ex) {
            Logger.getLogger(DocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DocumentsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
}

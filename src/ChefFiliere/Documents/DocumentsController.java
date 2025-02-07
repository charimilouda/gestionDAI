/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Documents;


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
        
        PreparedStatement ps1=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
        ps1.setInt(1, id_user);
        ResultSet res3=ps1.executeQuery();
        if(res3.next()){nomChef=res3.getString("nom_prof")+" "+res3.getString("prenom_prof");}
        
        
        Statement s1=conn.createStatement();
        ResultSet res4=s1.executeQuery("select * from document where destination=1 or destination=2 or destination=3 order by date_depot desc");
        while(res4.next())
        {
             HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("DocContainer.fxml"));
                containerH = loader.load();
                DocContainerController controller=loader.getController();
                controller.setIddoc(res4.getInt("id_doc"));
                controller.setNom(res4.getString("nom_doc"));
                controller.setDestination(res4.getInt("destination"));
                
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime dateTime=res4.getTimestamp("date_depot").toLocalDateTime();
                controller.setDateDepot(dateTime.format(formatter)); 
                
                
                controller.setTitre(res4.getString("titre_doc"));
                controller.setTaille(res4.getString("taille_doc"));
                controller.setExtension(res4.getString("extension_doc"));
                controller.setProprietaire(nomChef);
                controller.setContenu(res4.getBlob("contenu_doc"));
                list.add(containerH);
        }
        containerV.getChildren().addAll(list);
        s1.close();
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
    public void ajouterDoc(ActionEvent e) throws IOException
    {
        AjouterDocumentController c=new AjouterDocumentController();
        c.setIdUser(id_user);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("AjouterDocument.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
        
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Messagerie;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author chari milouda
 */
public class MessagerieController implements Initializable{
        private int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }

    @FXML
    private BorderPane bp;
     @FXML
    private BorderPane bp2;
    @FXML
    private Button nvMsg;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Label nbrMsj;

    public void messagesExists() throws IOException, SQLException
    {   String email; 
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps=conn.prepareStatement("Select * from etudiant where id_etudiant=?");
        ps.setInt(1,id_user);
        ResultSet res=ps.executeQuery();
        while(res.next())
        {
            email =res.getString("email_etudiant");
            PreparedStatement ps1=conn.prepareStatement("Select count(*) from message where email_recepteur=?");
            ps1.setString(1,email);
            ResultSet res1=ps1.executeQuery();
            if(res1.next()) 
            {
                int nbr=res1.getInt(1);
                
                if(nbr ==0)
                {   
                    nbrMsj.setText(0+" message(s)");
                    BoiteVideController c=new BoiteVideController();
                    c.setIdUser(id_user);
                    c.setBp(bp2);
                    FXMLLoader loader=new FXMLLoader();
                    loader.setController(c);
                    loader.setLocation(getClass().getResource("../Messagerie/boiteVide.fxml"));
                    AnchorPane ap = loader.load();
                    borderpane.setCenter(ap);
                    
                }
                else{
                    
                  
                    PreparedStatement ps2=conn.prepareStatement("Select count(*) from message where email_recepteur =?");
                    ps2.setString(1,email);
                    ResultSet res2=ps2.executeQuery();
                    if(res2.next()) 
                    {
                        int nbr2=res2.getInt(1);
                        nbrMsj.setText(nbr2+" message(s)");
                    }
                    Container_messagesController c=new Container_messagesController();
                    c.setIdUser(id_user);
                    c.setBp(bp2);
                    FXMLLoader loader=new FXMLLoader();
                    loader.setController(c);
                    loader.setLocation(getClass().getResource("../Messagerie/container_messages.fxml"));
                    AnchorPane ap = loader.load();
                    borderpane.setCenter(ap);
                    
                }
            }
        }
        conn.close();
    }
    public void refresh(ActionEvent e) throws IOException, SQLException
    {
        
        messagesExists();
    }
    public void nvMessage(ActionEvent e) throws IOException
    {
        NewController c=new NewController();
        c.setIdUser(id_user);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("../Messagerie/new.fxml"));
        AnchorPane ap = loader.load();
        bp2.setCenter(ap);
    }
    
    public void getContents(AnchorPane ap)//elle va  etre appelée dans messagescontroller 
    {
        bp2.setCenter(ap);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            messagesExists();
        } catch (IOException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MessagerieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}

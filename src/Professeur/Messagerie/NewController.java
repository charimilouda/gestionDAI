/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.Messagerie;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author CHRAI Milouda
 * 
 */
public class NewController  implements Initializable
{
    private int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    @FXML
    private Button envoyer;
    @FXML
    private Label sender;
    @FXML
    private TextArea content;
    @FXML
    private TextField object;
    @FXML
    private TextField receiver;
    @FXML
    private Label etatEnvoi;
    
       public void Envoyer(ActionEvent e) throws SQLException, IOException
       {   
           
            
            String emetteur =sender.getText();
           String email=receiver.getText();
           String obj=object.getText();
           String contenu=content.getText();
           LocalDateTime date=LocalDateTime.now();
           if(email.isBlank()||obj.isBlank()||contenu.isBlank())
           {
                Stage stage =new Stage();
                Parent root=FXMLLoader.load(getClass().getResource("alertChampsVides.fxml")); 
                Scene scene=new Scene(root);
                stage.setResizable(false);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(scene);
                stage.show();
           }
           else{
               String [] emails=email.split(" ");
               for(String item : emails)
               {
                    String url="jdbc:mysql://localhost/dai";
                    Connection conn=DriverManager.getConnection(url, "root", "");
                    PreparedStatement pstmt=conn.prepareStatement("SELECT * FROM Authentification Where email=?");
                    pstmt.setString(1,item);
                    ResultSet rs=pstmt.executeQuery();
                    if(rs.next())
                    {   // test sur l'email : il doit etre esxite dans la table authentification
                        PreparedStatement ps1=conn.prepareStatement("INSERT INTO message VALUES (?,?,?,?,?,?) ");
                        PreparedStatement ps2=conn.prepareStatement("select count(*) from message ");
                        ResultSet re=ps2.executeQuery();
                        if(re.next()) 
                        {
                            int nbr=0;
                            
                            Statement s1=conn.createStatement();
                            ResultSet res2=s1.executeQuery("select id_message from message order by id_message desc limit 1");
                            if(res2.next())
                            {
                                nbr=res2.getInt(1);
                            }
                            nbr++;
                            ps1.setInt(1, nbr);
                            ps1.setString(2, emetteur);
                            ps1.setString(3, item);
                            ps1.setString(4, obj);
                            ps1.setString(5, contenu);
                            ps1.setTimestamp(6, java.sql.Timestamp.valueOf(date));
                            int i=ps1.executeUpdate();
                            System.out.println(i+"lignes insérées");
                            Stage stage =new Stage();
                            Parent root=FXMLLoader.load(getClass().getResource("BoiteAlert.fxml")); 
                            Scene scene=new Scene(root);
                            stage.setResizable(false);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                   else{
                            Stage stage =new Stage();
                            Parent root=FXMLLoader.load(getClass().getResource("Email introuvable.fxml")); 
                            Scene scene=new Scene(root);
                            stage.setResizable(false);
                            stage.initStyle(StageStyle.UNDECORATED);
                            stage.setScene(scene);
                            stage.show();
                        break;
                    }
               }
           
        }
    }

    @Override
    public void initialize(URL url1, ResourceBundle rb) {
        try {
            String email_;//recuperer l'email d'apres l'id de la session courante
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps=conn.prepareStatement("Select * from professeur where id_prof=?");
            ps.setInt(1,id_user);
            ResultSet res=ps.executeQuery();
            while(res.next())
            {
                email_ =res.getString("email_prof");
                sender.setText(email_);
            }   conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewController.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
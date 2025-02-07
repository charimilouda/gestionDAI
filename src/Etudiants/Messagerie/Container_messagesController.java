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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class Container_messagesController implements Initializable {
    private int id_user;
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    
    @FXML
    private Label nbrMessages;
    @FXML
    private TextField txtField;
    @FXML
    private VBox container;
    BorderPane bp;

    public void setBp(BorderPane bp) {
        this.bp = bp;
    }
    public void refresh(ActionEvent ae) throws IOException, SQLException
    {
        container.getChildren().clear();
        genererMessages();
    }
    public void genererMessages() throws IOException, SQLException
    {
        ArrayList  <AnchorPane> list;
        list= new ArrayList<>();
        String email;
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps=conn.prepareStatement("Select * from etudiant where id_etudiant=?");
        ps.setInt(1,id_user);
        ResultSet res=ps.executeQuery();
        while(res.next())
        {
            email =res.getString("email_etudiant");
            
            PreparedStatement ps2=conn.prepareStatement("Select * from message where email_recepteur =? order by dateenvoi_message desc");
            ps2.setString(1,email);
            ResultSet res2=ps2.executeQuery();
            while(res2.next()) 
            {
                int id=res2.getInt("id_message");
                String[] tab=recupererInfos(id);
                MessagesController mc=new MessagesController();
                mc.setemailEmetteur(tab[0]);
                mc.setsujetMessage(tab[1]);
                mc.setmessage(tab[2]);
                mc.setDate(tab[3]);
                mc.setBp(bp);
                FXMLLoader loader=new FXMLLoader();
                loader.setController(mc);
                loader.setLocation(getClass().getResource("./messages.fxml"));
                AnchorPane ap =loader.load();
                list.add(ap);
            }
            
        }
       for (AnchorPane item : list)
       {
           container.getChildren().addAll(item);
       }
    }
    
    public String [] recupererInfos(int id) throws SQLException
    {
        String []info ={
            
            " "," "," ",""
        };
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps=conn.prepareStatement("SELECT * FROM message WHERE id_message=? ");
                ps.setInt(1, id);
                ResultSet res1=ps.executeQuery();
                if(res1.next())
                {
                    info[0] = res1.getString("email_emetteur");
                    info[1]= res1.getString("sujet_message");
                    String msg=res1.getString("message");
                    info[2]= msg;
                    String date=res1.getTimestamp("dateenvoi_message").toString();
                    info[3]=date.substring(0, 16);
                } conn.close();
        return info;
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            genererMessages();
        } catch (IOException ex) {
            Logger.getLogger(Container_messagesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Container_messagesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

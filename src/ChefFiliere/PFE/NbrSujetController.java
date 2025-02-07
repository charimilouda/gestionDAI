/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class NbrSujetController implements Initializable {

    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_enregister;

    @FXML
    private VBox containerV;


    public void chargerProf() throws SQLException, IOException
    {
        String nomProf=null;
        int id_prof=0;
        ArrayList<HBox> list=new ArrayList<HBox>();
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
            Statement s2=conn.createStatement();
            ResultSet res3=s2.executeQuery("Select id_prof,nom_prof,prenom_prof from professeur");
            while(res3.next())
            {
                id_prof=res3.getInt("id_prof");
                nomProf=res3.getString("nom_prof")+" "+res3.getString("prenom_prof");
                
                HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("NbrSujetContainer.fxml"));
                containerH = loader.load();
                NbrSujetContainerController c=loader.getController();
                c.setNomProf(nomProf);
                java.sql.PreparedStatement ps1=conn.prepareStatement("Select * from sujetpfe where id_prof=?");
                ps1.setInt(1,id_prof);
                ResultSet res4=ps1.executeQuery();
                if(res4.next())
                {
                    
                    c.setNbrSujet(res4.getInt("nbr_sujet"));
                }
                
                list.add(containerH);
                ps1.close();
            }
        containerV.getChildren().addAll(list);
        s2.close();
        conn.close();
    }
    
    public void enregistrer() throws SQLException, IOException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        Statement s2=conn.createStatement();
        ResultSet res3=s2.executeQuery("Select id_prof from professeur");
        int i=0;
        ObservableList<Node> e3=containerV.getChildren();
        while(res3.next())
        {
            Node n=e3.get(i);
	    HBox h1=(HBox) n;
            ObservableList<Node> e4=h1.getChildren();
            Node n1=e4.get(1);
            TextField t1=(TextField) n1;
            int nbr=Integer.parseInt(t1.getText());
            java.sql.PreparedStatement ps1=conn.prepareStatement("select * from sujetpfe where id_prof=?");
            ps1.setInt(1,res3.getInt("id_prof"));
            ResultSet res1=ps1.executeQuery();
            if(res1.next())
            {
                java.sql.PreparedStatement ps2=conn.prepareStatement("update sujetpfe set nbr_sujet=? where id_prof=?");
                ps2.setInt(1, nbr);
                ps2.setInt(2, res3.getInt("id_prof"));
                ps2.executeUpdate();
                ps2.close();
            }
            else
            {
                int idSujet=0;
                Statement s1=conn.createStatement();
                ResultSet res7=s1.executeQuery("SELECT id_sujet FROM sujetpfe ORDER BY id_sujet DESC LIMIT 1;");
                while(res7.next())
                {
                    idSujet=res7.getInt(1);
                }
                idSujet++;
             
                java.sql.PreparedStatement ps3=conn.prepareStatement("insert into sujetpfe values(?,?,?,?)");
                ps3.setInt(1, idSujet); //id
                ps3.setInt(2, res3.getInt("id_prof"));
                ps3.setInt(3, nbr);
                ps3.setString(4, "");
                ps3.executeUpdate();
                ps3.close();
            }
            i++;
        }
        Stage stage = (Stage) btn_enregister.getScene().getWindow();
        stage.close();
    }
    
    
    public void annuler()
    {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerProf();
        } catch (SQLException ex) {
            Logger.getLogger(NbrSujetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NbrSujetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
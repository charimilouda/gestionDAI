/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Matieres;


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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class MatieresController implements Initializable {

    @FXML
    private VBox containerV;

        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerMatieres();
        } catch (SQLException ex) {
            Logger.getLogger(MatieresController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MatieresController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    

    
    public void chargerMatieres() throws SQLException, IOException
    {
        
        ArrayList<String> ProfList=new ArrayList<>();
        ArrayList<Integer> ProfIds=new ArrayList<>();
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        java.sql.Statement s1=conn.createStatement();
        ResultSet res1=s1.executeQuery("select * from professeur");
        while(res1.next())
        {
            String prof=res1.getInt("id_prof")+"-"+res1.getString("nom_prof")+" "+res1.getString("prenom_prof");
            ProfList.add(prof);

        }
        
        
        ArrayList<HBox> list=new ArrayList<>();
        java.sql.Statement s2=conn.createStatement();
        ResultSet res2=s2.executeQuery("select * from matiere");
        while(res2.next())
        {
            String matiere=res2.getString("intitule_matiere");
            
            HBox containerH=new HBox();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MatiereView.fxml"));
            containerH = loader.load();
            MatiereViewController c=loader.getController();
            c.setMatiere(matiere);
            c.setProf(ProfList);
            c.setIDMatiere(String.valueOf(res2.getInt("id_matiere")));
            list.add(containerH);
        }
        containerV.getChildren().addAll(list);
        
        int i=0;
        String sujets="";
        ObservableList<Node> e3=containerV.getChildren();
        
        
        for(i=0;i<e3.size();i++)
        {
                Node n=e3.get(i);
                HBox h=(HBox) n;
                ObservableList<Node> elts_hbox=h.getChildren();
                Node n1=elts_hbox.get(0);
                Node n2=elts_hbox.get(2);
                Label l1=(Label)n1;
                ChoiceBox<String> c1=(ChoiceBox<String>) n2;
                
                int idMatiere=Integer.parseInt(l1.getText());
                
                java.sql.Statement s3=conn.createStatement();
                ResultSet res3=s3.executeQuery("select * from matiere where id_prof is not null;");
                while(res3.next())
                {
                    if(res3.getInt("id_matiere")==idMatiere)
                    {
                       
                        java.sql.PreparedStatement ps4=conn.prepareStatement("select * from professeur where id_prof=?");
                        ps4.setInt(1,res3.getInt("id_prof"));
                        ResultSet res4=ps4.executeQuery();
                        if(res4.next())
                        {
                            String prof=res4.getInt("id_prof")+"-"+res4.getString("nom_prof")+" "+res4.getString("prenom_prof");
                            c1.setValue(prof);
                        }
                        
                    }
                }
        }
    
    
    }
    
    
    public void save(ActionEvent e) throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        int i=0;
        String sujets="";
        ObservableList<Node> e3=containerV.getChildren();
        for(i=0;i<e3.size();i++)
        {
                Node n=e3.get(i);
                HBox h=(HBox) n;
                ObservableList<Node> elts_hbox=h.getChildren();
                Node n1=elts_hbox.get(0);
                Node n2=elts_hbox.get(2);
                Label l1=(Label)n1;
                ChoiceBox<String> c1=(ChoiceBox<String>) n2;
                
                if(c1.getValue()!=null)
                {
                    int idMatiere=Integer.parseInt(l1.getText());
                    int idProf=Integer.parseInt(c1.getValue().split("-")[0]);

                    java.sql.PreparedStatement ps2=conn.prepareStatement("update matiere set id_prof=? where id_matiere=?");
                    ps2.setInt(1, idProf);
                    ps2.setInt(2, idMatiere);
                    ps2.executeUpdate();
                }
                
        }
        

    }
}
    
    


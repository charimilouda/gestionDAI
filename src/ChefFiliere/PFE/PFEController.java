/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.PFE;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
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
    @FXML
    private VBox containerSujet;
        @FXML
    private VBox containerGrp;
       @FXML
    private VBox containerPFE; 
       
       ArrayList<String> PFE=new ArrayList<>();
       
           @FXML
    private Button generer;

    @FXML
    private Button save;
        
    public void afficherSujet(ActionEvent event) throws IOException {
        NbrSujetController c=new NbrSujetController();
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("NbrSujet.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    public void chargerSujetProf() throws SQLException, IOException
    {
        String nomProf=null;
        int id_prof=0;
        ArrayList<HBox> list=new ArrayList<HBox>();
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
            Statement s2=conn.createStatement();
            ResultSet res3=s2.executeQuery("select * from sujetpfe where intitule_sujet NOT like \"NA\";");
            while(res3.next())
            {
                id_prof=res3.getInt("id_prof");
                
                java.sql.PreparedStatement ps3=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
                ps3.setInt(1, id_prof);
                ResultSet res4=ps3.executeQuery();
                if(res4.next()){nomProf=res4.getString("nom_prof")+" "+res4.getString("prenom_prof");}
                
                HBox containerH=new HBox();
                FXMLLoader loader=new FXMLLoader(getClass().getResource("SujetProfView.fxml"));
                containerH = loader.load();
                SujetProfViewController c=loader.getController();
                c.setNomProf(nomProf);
                c.setSujets(res3.getString("intitule_sujet"));
                list.add(containerH);
            }
        containerSujet.getChildren().addAll(list);
        s2.close();
        conn.close();
    }
    
    public void chargerGrpEtud() throws SQLException, IOException
    {
        String nomGrp="";
        int id_grp=0;
        int count=0;
        ArrayList<HBox> list2=new ArrayList<HBox>();
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        Statement s2=conn.createStatement();
        ResultSet res3=s2.executeQuery("SELECT distinct id_groupe_etudiant from groupe_etudiant;");
        while(res3.next())
        {
            int i=res3.getInt("id_groupe_etudiant");
            PreparedStatement ps1=conn.prepareStatement("select id_etudiant from groupe_etudiant where id_groupe_etudiant=?");
            ps1.setInt(1,i);
            ResultSet res1=ps1.executeQuery();
            while(res1.next())
            {
                int id_etud=res1.getInt("id_etudiant");
                PreparedStatement ps2=conn.prepareStatement("select nom_etudiant,prenom_etudiant from etudiant where id_etudiant=?");
                ps2.setInt(1,id_etud);
                ResultSet res2=ps2.executeQuery();
                if(res2.next())
                {
                    nomGrp+=res2.getString("nom_etudiant")+" "+res2.getString("prenom_etudiant")+"  ";
                      
                }  
            }
            HBox containerH=new HBox();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("GroupeEtudView.fxml"));
            containerH = loader.load();
            GroupeEtudViewController c=loader.getController();
            c.setGrp(nomGrp);
            c.setIdGrp(""+i);
            list2.add(containerH);
            nomGrp="";
        }
        containerGrp.getChildren().addAll(list2);
        
    
    
    }

    public void genererPFE() throws SQLException, IOException
    {
        save.setDisable(false);
        PFE.clear();
        containerPFE.getChildren().clear();
        ArrayList<HBox> list=new ArrayList<>();
        System.out.println("_-----------------------------------_");
        ArrayList<Integer> grps=new ArrayList<>();
        ArrayList<Integer> profs=new ArrayList<>();
        Random generator=new Random();
        String url="jdbc:mysql://localhost/dai";
        java.sql.Connection conn=DriverManager.getConnection(url, "root", "");
        Statement s1=conn.createStatement();
        ResultSet res1=s1.executeQuery("select distinct id_groupe_etudiant from groupe_etudiant");
        while(res1.next())
        {
            grps.add(res1.getInt("id_groupe_etudiant"));
        }
        
        Statement s2=conn.createStatement();
        ResultSet res2=s2.executeQuery("select * from sujetpfe where intitule_sujet NOT like \"\";");
        ArrayList<String> listsujets=new ArrayList<>();
        while(res2.next())
        {                
            int id_prof=res2.getInt("id_prof");
            
            String[] sujets=res2.getString("intitule_sujet").split("//");
            for(int j=0;j<sujets.length;j++)
            {
                sujets[j]=id_prof+"%"+sujets[j];
            }
            Collections.addAll(listsujets, sujets);
        }
        
        Collections.shuffle(grps);
        Collections.shuffle(listsujets);
        for(int i=0;i<grps.size();i++)
        {
            String nomProf="";
            String str=listsujets.get(i);
            String[] strs=str.split("%");
            int id_prof=Integer.parseInt(strs[0]);
            PreparedStatement ps3=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
            ps3.setInt(1, id_prof);
            ResultSet res4=ps3.executeQuery();
            if(res4.next()){nomProf=res4.getString("nom_prof")+" "+res4.getString("prenom_prof");}
            String sujet=strs[1];
            
            int id_grp=grps.get(i);
            HBox containerH=new HBox();
            FXMLLoader loader=new FXMLLoader(getClass().getResource("PFEContainer.fxml"));
            containerH = loader.load();
            PFEContainerController c=loader.getController();
                c.setEncadrant(nomProf);
                c.setId_grp(""+id_grp);
                c.setSujet(sujet);
            list.add(containerH);
            PFE.add(i+"//"+sujet+"//"+id_grp+"//"+id_prof);
        }
        containerPFE.getChildren().addAll(list);
    }

    
    public void enregistrerPFE(ActionEvent e) throws IOException
    {
        EnregistrerPFEController controller=new EnregistrerPFEController();
        controller.setGenerer(generer);
        controller.setSave(save);
        controller.setPFE(PFE);
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("EnregistrerPFE.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerSujetProf();
            chargerGrpEtud();
        } catch (SQLException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PFEController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

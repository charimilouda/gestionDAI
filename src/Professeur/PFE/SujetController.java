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
import javafx.scene.control.Button;
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
public class SujetController implements Initializable {

    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @FXML
    private Button annuler;

    @FXML
    private VBox containerV;

    @FXML
    private Button save;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            chargerSujets();
        } catch (SQLException ex) {
            Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SujetController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void chargerSujets() throws SQLException, IOException
    {
        String nomProf=null;
        int id_prof=0;
        ArrayList<HBox> list=new ArrayList<>();
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
            java.sql.PreparedStatement ps1=conn.prepareStatement("Select * from sujetpfe where id_prof=?");
            ps1.setInt(1, idUser);
            ResultSet res3=ps1.executeQuery();
            if(res3.next())
            {
                int nbrSujet=res3.getInt("nbr_sujet");
                String sujets[]=res3.getString("intitule_sujet").split("//");
                for(int i=0;i<nbrSujet;i++)
                {
                    HBox t=new HBox();
                    FXMLLoader loader=new FXMLLoader(getClass().getResource("SujetContainer.fxml"));
                    t = loader.load();
                    SujetContainerController c=loader.getController();
                    c.setLabelSujet("Sujet N"+(i+1));
                    //c.setSujet(sujets[i]);
                    list.add(t);
                }
                if(!"".equals(res3.getString("intitule_sujet")))
                {
                    save.setDisable(true);
                }
            }
        containerV.getChildren().addAll(list);
        conn.close();
    
    }
    
    @FXML
    void save(ActionEvent event) throws SQLException, IOException {
        AlertController controller=new AlertController();
        controller.setIdUser(idUser);
        controller.setContainerV(containerV);
        controller.setSave(save);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(getClass().getResource("Alert.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
        @FXML
    void annuler(ActionEvent event) {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }


    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.PFE;


import java.io.IOException;
import java.sql.*;
import java.net.URL;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class GroupeContainerController implements Initializable {
      int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
       
    @FXML
    private Button annuler;

    @FXML
    private ChoiceBox<String> choice_box1;

    @FXML
    private ChoiceBox<String> choice_box2;

    @FXML
    private ChoiceBox<String> choice_box3;

    @FXML
    private Label label_msg_err;

    @FXML
    private Button save;
    
    
    @FXML
    private VBox containerV;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
              generateStudentNames();
              verifier();
          } catch (SQLException ex) {
              Logger.getLogger(GroupeContainerController.class.getName()).log(Level.SEVERE, null, ex);
          }
    }    
        public void generateStudentNames() throws SQLException
    {
        String nomEtudCourant=null;
        ArrayList<String> names=new ArrayList<>();
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");
        
        PreparedStatement ps2=conn.prepareStatement("Select nom_etudiant,prenom_etudiant from etudiant where id_etudiant=?");
        ps2.setInt(1,idUser); 
	ResultSet res2=ps2.executeQuery();
	if(res2.next()){
            nomEtudCourant=idUser+"-"+res2.getString("nom_etudiant")+" "+res2.getString("prenom_etudiant");
        }
        choice_box1.getItems().add(nomEtudCourant);
        choice_box1.setValue(nomEtudCourant);
        
        PreparedStatement ps3=conn.prepareStatement("Select id_etudiant,nom_etudiant,prenom_etudiant from etudiant where id_etudiant!=?");
        ps3.setInt(1, idUser);
        ResultSet res3=ps3.executeQuery();
        while(res3.next())
        {
            int id_etud=res3.getInt("id_etudiant");
            PreparedStatement ps4=conn.prepareStatement("select id_etudiant from groupe_etudiant where id_etudiant=?");
            ps4.setInt(1, id_etud);
            ResultSet res4=ps4.executeQuery();
            if(!res4.next())
            {
                PreparedStatement ps5=conn.prepareStatement("Select nom_etudiant,prenom_etudiant from etudiant where id_etudiant=?");
                ps5.setInt(1, id_etud);
                ResultSet res5=ps5.executeQuery();
                String str=null;
                if(res5.next()){str=id_etud+"-"+res5.getString("nom_etudiant")+" "+res5.getString("prenom_etudiant");}
                names.add(str);
            }
            
        }
        choice_box2.getItems().addAll(names);
        
        choice_box2.setOnAction(event -> {
            choice_box3.getItems().clear();
            if(choice_box2.getValue()!=null) {
                //fetch id de box2 selectionne
               ArrayList<String> names2=new ArrayList<>();
               names2.addAll(names);
               names2.remove(choice_box2.getValue());
               choice_box3.getItems().addAll(names2);
            }
        });
    }
    public void save(ActionEvent e) throws SQLException, IOException
    {

        if(choice_box2.getValue()==null)
        {
            label_msg_err.setText("Le groupe doit avoir au minimum deux etudiants!");
        }
        else
        {
            AlertController controller=new AlertController();
            controller.setIdUser(idUser);
            controller.setChoice_box1(choice_box1);
            controller.setChoice_box2(choice_box2);
            controller.setChoice_box3(choice_box3);
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
        
    }
    
    public void annuler(ActionEvent e)
    {
        Stage stage = (Stage) annuler.getScene().getWindow();
        stage.close();
    }
    
    public void verifier() throws SQLException
    {
        ObservableList<Node> e3=containerV.getChildren();
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");
       
        PreparedStatement ps1=conn.prepareStatement("select * from groupe_etudiant where id_etudiant=?");
        ps1.setInt(1, idUser);
        ResultSet res1=ps1.executeQuery();
        if(res1.next())
        {
            int idGrp=res1.getInt("id_groupe_etudiant");
            PreparedStatement ps2=conn.prepareStatement("select id_etudiant from groupe_etudiant where id_groupe_etudiant=? and id_etudiant!=?");
            ps2.setInt(1, idGrp);
            ps2.setInt(2,idUser);
            ResultSet res2=ps2.executeQuery();
            int i=1;
            while(res2.next())
            {
                String name=null;
                int id_etud=res2.getInt("id_etudiant");
                PreparedStatement ps3=conn.prepareStatement("select nom_etudiant,prenom_etudiant from etudiant where id_etudiant=?");
                ps3.setInt(1,id_etud);
                ResultSet res3=ps3.executeQuery();
                if(res3.next())
                {
                    name=id_etud+"-"+res3.getString("nom_etudiant")+" "+res3.getString("prenom_etudiant");
                }
                Node n=e3.get(i);
		ChoiceBox<String> c=(ChoiceBox<String>) n;
                c.setValue(name);
                i++;
            }
            save.setDisable(true);
        }
    }
}

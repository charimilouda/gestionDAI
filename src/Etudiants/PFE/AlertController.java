/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.PFE;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class AlertController implements Initializable {
    
    int idUser;

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    @FXML
    private ChoiceBox<String> choice_box1;

    @FXML
    private ChoiceBox<String> choice_box2;

    @FXML
    private ChoiceBox<String> choice_box3;
    
    @FXML
    private Button save;

    public void setSave(Button save) {
        this.save = save;
    }

    public void setChoice_box1(ChoiceBox<String> choice_box1) {
        this.choice_box1 = choice_box1;
    }

    public void setChoice_box2(ChoiceBox<String> choice_box2) {
        this.choice_box2 = choice_box2;
    }

    public void setChoice_box3(ChoiceBox<String> choice_box3) {
        this.choice_box3 = choice_box3;
    }
    
    
    @FXML
    private Button btn_annuler;

    @FXML
    private Button btn_confirmer;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void confirmer(ActionEvent e) throws SQLException
    {
        int id1=idUser;
        String[] str=choice_box2.getValue().split("-");
        int id2=Integer.parseInt(str[0]);

            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            Statement s1=conn.createStatement();
            ResultSet res1=s1.executeQuery("SELECT id_groupe_etudiant FROM groupe_etudiant ORDER BY id_groupe_etudiant DESC LIMIT 1;");
            int id_groupe=0;    
            while(res1.next())
                 {
                     id_groupe=res1.getInt(1);
                 }
            id_groupe++;

            PreparedStatement ps1=conn.prepareStatement("insert into groupe_etudiant values(?,?)");
            ps1.setInt(1, id1);
            ps1.setInt(2,id_groupe); //a changeer
            ps1.executeUpdate();
            ps1.close();

            PreparedStatement ps2=conn.prepareStatement("insert into groupe_etudiant values(?,?)");
            ps2.setInt(1, id2);
            ps2.setInt(2,id_groupe); //a changeer
            ps2.executeUpdate();
            ps2.close(); 

            if(choice_box3.getValue()!=null)
                {
                   String[] str2=choice_box3.getValue().split("-");
                   int id3=Integer.parseInt(str2[0]);

                   PreparedStatement ps3=conn.prepareStatement("insert into groupe_etudiant values(?,?)");
                    ps3.setInt(1, id3);
                    ps3.setInt(2,id_groupe); //a changeer
                    ps3.executeUpdate();
                    ps3.close();
                }
            conn.close();
            save.setDisable(true);
            Stage stage = (Stage) btn_confirmer.getScene().getWindow();
            stage.close();
    }
    
    public void annuler(ActionEvent e)
    {
        Stage stage = (Stage) btn_annuler.getScene().getWindow();
        stage.close();
    }
}

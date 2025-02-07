/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Notes;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class NotesController implements Initializable {
    private int IdUser;

    public int getIdUser() {
        return IdUser;
    }

    public void setIdUser(int IdUser) {
        this.IdUser = IdUser;
    }
    
    @FXML private TableColumn<NoteMatiere, String> assiduite;
    @FXML private TableColumn<NoteMatiere, String> avg;
    @FXML private TableColumn<NoteMatiere, String> ds1;
    @FXML private TableColumn<NoteMatiere, String> ds2;
    @FXML private TableColumn<NoteMatiere, String> ds3;
    @FXML  private TableColumn<NoteMatiere,String> matiere; @FXML
    private TableView<NoteMatiere> table;
        ObservableList<NoteMatiere> listMatiere=FXCollections.observableArrayList();
        public void showGrades() throws SQLException
        {
            table.setFixedCellSize(40.0);
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps1=conn.prepareStatement("select * from matiere");
            ResultSet res1=ps1.executeQuery();
            while(res1.next())
            { 
                String matiere__=res1.getString("intitule_matiere");//on a recuuperé le nom de la matiere
                int id_mat=res1.getInt("id_matiere");
                PreparedStatement ps=conn.prepareStatement("select * from note_matiere where id_etudiant=? and id_matiere=?");
                ps.setInt(1, IdUser);
                ps.setInt(2, id_mat);
                ResultSet res=ps.executeQuery();
                while(res.next())
                {
                    String ds1_=res.getString("ds1");
                    String ds2_=res.getString("ds2");
                    String ds3_=res.getString("ds3");
                    String assiduite_=res.getString("assiduite");
                    String moyenne_=res.getString("valeur_note_matiere");
                    listMatiere.add(new NoteMatiere(matiere__,ds1_,ds2_,ds3_,assiduite_,moyenne_));
                }
            }
            matiere.setCellValueFactory(new PropertyValueFactory< NoteMatiere,String>("matiere_"));
            ds1.setCellValueFactory(new PropertyValueFactory<NoteMatiere,String>("ds1"));
            ds2.setCellValueFactory(new PropertyValueFactory<NoteMatiere,String>("ds2"));
            ds3.setCellValueFactory(new PropertyValueFactory<NoteMatiere,String>("ds3"));
            assiduite.setCellValueFactory(new PropertyValueFactory<NoteMatiere,String>("assiduite"));
            avg.setCellValueFactory(new PropertyValueFactory<NoteMatiere,String>("moyenne"));
            table.setItems(listMatiere);
            conn.close();
        }
         public void refresh (ActionEvent ae) throws SQLException, IOException
        {
            table.getItems().clear();
            showGrades();    
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showGrades();
        } catch (SQLException ex) {
            Logger.getLogger(NotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

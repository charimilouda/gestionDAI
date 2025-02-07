/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Professeurs;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class ProfesseurController implements Initializable {
    @FXML  private TableColumn action;
    @FXML private Button btn_ajouter;
    @FXML private TableColumn<Professeur, String> email;
    @FXML private TableColumn<Professeur, String> genre;
    @FXML private TableColumn<Professeur, Integer> id;
    @FXML private TableColumn<Professeur, String> nom;
    @FXML private TableColumn<Professeur, String> prenom;
    @FXML private TableColumn<Professeur, String> telephone;
    @FXML private TableView<Professeur> table;
    ObservableList<Professeur> listProf=FXCollections.observableArrayList();
    public void showProf() throws SQLException
    {
        
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps=conn.prepareStatement("select * from professeur");
        ResultSet res=ps.executeQuery();
        while(res.next())
        {
            int id_=res.getInt("id_prof");
            System.out.println(id_);
            String nom_=res.getString("nom_prof");
            String prenom_=res.getString("prenom_prof");
            String genre_=res.getString("sexe_prof");
            String email_=res.getString("email_prof");
            String tel_=res.getString("tel_prof");
            System.out.println(id_+" " +nom_+" " +prenom_+" " + genre_);
            listProf.add(new Professeur(id_,nom_,prenom_,tel_,email_,genre_));
        }
        id.setCellValueFactory(new PropertyValueFactory< Professeur,Integer>("id_"));
        nom.setCellValueFactory(new PropertyValueFactory<Professeur,String>("nom_"));
        prenom.setCellValueFactory(new PropertyValueFactory<Professeur,String>("prenom_"));
        telephone.setCellValueFactory(new PropertyValueFactory<Professeur,String>("tel_"));
        email.setCellValueFactory(new PropertyValueFactory<Professeur,String>("email_"));
        genre.setCellValueFactory(new PropertyValueFactory<Professeur,String>("genre_"));
        Callback<TableColumn<Professeur,String>,TableCell<Professeur,String>>cellFactory
            =(param)->{
                    final TableCell<Professeur,String> cell=new TableCell<Professeur,String>(){
                        @Override
                        public void updateItem(String item, boolean empty)
                        {
                            super.updateItem(item, empty);
                            if(empty)
                            {
                                setGraphic(null);
                                setText(null);
                            }
                             else
                            {
                                final Button editBtn=new Button("Modifier");
                                final Button deleteBtn=new Button("Supprimer");
                                HBox pane =new HBox(editBtn,deleteBtn);
                                pane.setSpacing(10);
                                editBtn.setStyle("-fx-background-color: #ff7d00;-fx-text-fill: white ; -fx-font-size: 12px; ");
                                editBtn.setOnAction(event->{
                                    try {
                                        Professeur et=getTableView().getItems().get(getIndex());
                                        ModifierProfesseurController controller=new ModifierProfesseurController();
                                        controller.setV_id(et.getId_());
                                        FXMLLoader loader=new FXMLLoader();
                                        loader.setController(controller);
                                        loader.setLocation(getClass().getResource("ModifierProfesseur.fxml"));
                                        Parent root1 = (Parent) loader.load();
                                        Stage stage = new Stage();
                                        stage.setResizable(false);
                                        stage.initStyle(StageStyle.UNDECORATED);
                                        stage.setScene(new Scene(root1));
                                        stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(ProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                                deleteBtn.setStyle("-fx-background-color:  #ff7d00;-fx-text-fill: white ; -fx-font-size: 12px; ");
                                deleteBtn.setOnAction(event->{
                                    try {
                                        Professeur et=getTableView().getItems().get(getIndex());
                                        ConfirmationController controller=new ConfirmationController();
                                        controller.setId(et.getId_());
                                        FXMLLoader loader=new FXMLLoader();
                                        loader.setController(controller);
                                        loader.setLocation(getClass().getResource("Confirmation.fxml"));
                                        Parent root1 = (Parent) loader.load();
                                        Stage stage = new Stage();
                                        stage.setResizable(false);
                                        stage.initStyle(StageStyle.UNDECORATED);
                                        stage.setScene(new Scene(root1));
                                        stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(ProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                });
                                setGraphic(pane); 
                                
                            setText(null);}
                        }
                    };
                    return cell;
                };
        action.setCellFactory(cellFactory);
        table.setItems(listProf);
        conn.close();
    }
    @FXML
    void ajouterProf(ActionEvent event) throws IOException {
         Stage stage =new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("AjouterProf.fxml")); 
        Scene scene=new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
        
    }
    public void refresh (ActionEvent ae) throws SQLException, IOException
    {
        table.getItems().clear();
        showProf();    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            showProf();
        } catch (SQLException ex) {
            Logger.getLogger(ProfesseurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}

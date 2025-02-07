/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Etudiants;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author CHARI Milouda
 */
public class EtudiantsController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
           showEtudiants();
       } catch (SQLException ex) {
           Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
            Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    @FXML private TableColumn action;
    @FXML private TableColumn<Etudiants, String> telephone;
    @FXML private TableColumn<Etudiants, String> email;
    @FXML private TableColumn<Etudiants, Integer> id;
    @FXML private TableColumn<Etudiants, String> naissance;
    @FXML private TableColumn<Etudiants, String> nom;
    @FXML private TableColumn<Etudiants, String> numApog;
    @FXML private TableColumn<Etudiants, String> prenom;
    @FXML private TableView<Etudiants> table;
    ObservableList<Etudiants> listEtud=FXCollections.observableArrayList();
   public void showEtudiants() throws SQLException,IOException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps=conn.prepareStatement("select * from etudiant");
        ResultSet res=ps.executeQuery();
        while(res.next())
        {
            int id_=res.getInt("id_etudiant");
            String nom_=res.getString("nom_etudiant");
            String prenom_=res.getString("prenom_etudiant");
            String naissance_=res.getDate("dateNaissance_etudiant").toString();
            String numApg_=res.getString("numeroApogee_etudiant");
            String email_=res.getString("email_etudiant");
            String tele=res.getString("tel_etudiant");
            listEtud.add(new Etudiants(id_,nom_,prenom_,naissance_,numApg_,tele,email_));
        }
        id.setCellValueFactory(new PropertyValueFactory<Etudiants,Integer>("id_"));
        numApog.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("numApg_"));
        nom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("nom_"));
        prenom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("prenom_"));
        telephone.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("tel_"));
        naissance.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("naissance_"));
        telephone.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("tel_"));
        email.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("email_"));
        Callback<TableColumn<Etudiants,String>,TableCell<Etudiants,String>>cellFactory
            =(param)->{
                    final TableCell<Etudiants,String> cell=new TableCell<Etudiants,String>(){
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
                                editBtn.setStyle("-fx-background-color:  #ff7d00;-fx-text-fill: white ; -fx-font-size: 12px; ");
                                editBtn.setOnAction(event->{
                                    try {
                                        Etudiants et=getTableView().getItems().get(getIndex());
                                        ModifierEtudiantController controller=new ModifierEtudiantController();
                                        controller.setV_id(et.getId_());
                                        FXMLLoader loader=new FXMLLoader();
                                        loader.setController(controller);
                                        loader.setLocation(getClass().getResource("ModifierEtudiant.fxml"));
                                        Parent root1 = (Parent) loader.load();
                                        Stage stage = new Stage();
                                        stage.setResizable(false);
                                        stage.initStyle(StageStyle.UNDECORATED);
                                        stage.setScene(new Scene(root1));  
                                        stage.show();
                                    } catch (IOException ex) {
                                        Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                                deleteBtn.setStyle("-fx-background-color:  #ff7d00;-fx-text-fill: white ; -fx-font-size: 12px; ");
                                deleteBtn.setOnAction(event->{
                                    try {
                                        Etudiants et=getTableView().getItems().get(getIndex());
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
                                        Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                        
                                });
                                setGraphic(pane); 
                                
                            setText(null);}
                        }
                    };
                    return cell;
                };
        action.setCellFactory(cellFactory);
        table.setItems(listEtud);
        conn.close();

    }
    
   @FXML
    void ajouterETudiant(ActionEvent event) throws IOException {
        Stage stage =new Stage();
        Parent root=FXMLLoader.load(getClass().getResource("AjouterEtudiant.fxml")); 
        Scene scene=new Scene(root);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
    public void refresh (ActionEvent ae) throws SQLException, IOException
    {
        table.getItems().clear();
        showEtudiants();    
    }
       
}

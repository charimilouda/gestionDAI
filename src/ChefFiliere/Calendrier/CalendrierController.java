/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Calendrier;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class CalendrierController implements Initializable {
    
    int id_user;

    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    
    ZonedDateTime dateFocus;
    ZonedDateTime today;


    @FXML
    private Label annee;

    @FXML
    private Label mois;

    @FXML
    private FlowPane calendrier;
    
    @FXML
    private Label jour_selectione;
    @FXML
    private VBox container_V;
    
    ArrayList<Evenement> ev_container;

    public void generer()
    {
        ArrayList<AnchorPane> list=new ArrayList<AnchorPane>(); 
        for(Evenement e:ev_container)
        {
            EventContainerController controller=new EventContainerController();
            controller.setTitre(e.nom);
            controller.setProprietaire(e.proprietaire);
            controller.setIdProf(e.id_prof);
            controller.setDateDebut(e.debut);
            controller.setDateFin(e.fin);
            controller.setDescription(e.description);
            controller.setDate(e.date.toString());
            controller.setId(e.id_ev);
            controller.setSession_courante(id_user);
            FXMLLoader loader=new FXMLLoader();
            loader.setController(controller);
            loader.setLocation(getClass().getResource("EventContainer.fxml"));
            AnchorPane container=new AnchorPane();
            try {
                container=loader.load();
            } catch (IOException ex) {
                Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
            }
            list.add(container);

        }
        container_V.getChildren().addAll(list);
                            
    }
    public void chargerEvents() throws SQLException, IOException
    {
        ArrayList<AnchorPane> list=new ArrayList<AnchorPane>();
        
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps1=conn.prepareStatement("Select * from evenement where date_ev=? order by debut_ev"); //verifiyi 7ta l3am extract year form date_ev=
        ps1.setDate(1, Date.valueOf(dateFocus.toLocalDate()));
        
	ResultSet res1=ps1.executeQuery();
	while(res1.next())
        {
            String name=null;
            
            PreparedStatement ps2=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
            ps2.setInt(1,res1.getInt("id_prof")); 
            ResultSet res2=ps2.executeQuery();
            if(res2.next()){
                name=res2.getString("nom_prof")+" "+res2.getString("prenom_prof");
            }
            EventContainerController controller=new EventContainerController();
            controller.setTitre(res1.getString("nom_ev"));
            controller.setDateDebut(res1.getString("debut_ev"));
            controller.setDateFin(res1.getString("fin_ev"));
            controller.setDescription(res1.getString("description_ev"));
            controller.setDate(res1.getDate("date_ev").toString());
            controller.setId(res1.getInt("id_ev"));
            controller.setIdProf(res1.getInt("id_prof"));
            controller.setProprietaire(name);
            controller.setSession_courante(id_user);
            FXMLLoader loader=new FXMLLoader();
            loader.setController(controller);
            loader.setLocation(getClass().getResource("EventContainer.fxml"));
            AnchorPane container=new AnchorPane();
            container=loader.load();
            list.add(container);
        }
        container_V.getChildren().addAll(list);
        ps1.close();
        conn.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        try {
            drawCalendar();
            jour_selectione.setText(today.getDayOfMonth()+"/"+today.getMonthValue()+"/"+today.getYear());
            chargerEvents();
        } catch (SQLException ex) {
            Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    @FXML
    void avantMois(ActionEvent event) throws SQLException, IOException {
        dateFocus = dateFocus.minusMonths(1);
        calendrier.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void apresMois(ActionEvent event) throws SQLException, IOException {
        dateFocus = dateFocus.plusMonths(1);
        calendrier.getChildren().clear();
        drawCalendar();
    }
    private void drawCalendar() throws SQLException, IOException{
        annee.setText(String.valueOf(dateFocus.getYear()));
        mois.setText(String.valueOf(dateFocus.getMonth()));
        
        double calendarWidth = calendrier.getPrefWidth();
        double calendarHeight = calendrier.getPrefHeight();
        double strokeWidth = 1;
        double spacingH = calendrier.getHgap();
        double spacingV = calendrier.getVgap();
        
        int monthMaxDate = dateFocus.getMonth().maxLength();
        
        if(dateFocus.getYear() % 4 != 0 && monthMaxDate == 29){
            monthMaxDate = 28;
        }
        
        int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1,0,0,0,0,dateFocus.getZone()).getDayOfWeek().getValue();
        ArrayList<Evenement> evMois = verifierEvenement(dateFocus);
        
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                StackPane stackPane = new StackPane();

                Rectangle rectangle = new Rectangle();
                rectangle.setId("emptyBox");
                
                double rectangleWidth =(calendarWidth/7) - strokeWidth - spacingH;
                rectangle.setWidth(rectangleWidth);
                double rectangleHeight = (calendarHeight/6) - strokeWidth - spacingV;
                rectangle.setHeight(rectangleHeight);
                
                stackPane.getChildren().add(rectangle);

                int calculatedDate = (j+2)+(7*i);
                
                Text date=null;
                if(calculatedDate > dateOffset){
                    int currentDate = calculatedDate - dateOffset;
                    
                    if(currentDate <= monthMaxDate){
                        date = new Text(String.valueOf(currentDate));
                        double textTranslationY = - (rectangleHeight / 2) * 0.75;
                        date.setTranslateY(textTranslationY);
                        date.setFont(Font.font("Verdana"));
                        stackPane.getChildren().add(date);
                        rectangle.setId("fullBox");

                        ArrayList<Evenement> evJour=new ArrayList<Evenement>();
                        for(Evenement e:evMois)
                        {
                            if(e.date.getDayOfMonth()==currentDate)
                            {
                                evJour.add(e);
                            }
                        }
                        if(!evJour.isEmpty())
                        {
                            rectangle.setId("eventBox");
                            date.setId("eventText");
                            rectangle.setOnMouseClicked(mouseEvent -> {
                            ev_container=evJour;
                            container_V.getChildren().clear();
                            generer();
                            
                            jour_selectione.setText(currentDate+"/"+"0"+dateFocus.getMonthValue()+"/"+dateFocus.getYear());
                            });
                        }
                        
                        
                    }
                    if(today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate){
                        rectangle.setStyle("-fx-stroke: #1f7ed7;");
                    }
                }
                calendrier.getChildren().add(stackPane);
                
            }
        
    }
}
    
    public void ajouterEvenement(ActionEvent e) throws IOException
    {
        AjouterEvenementController controller=new AjouterEvenementController();
        controller.setIdUser(id_user);
        
        FXMLLoader loader=new FXMLLoader();
        
        loader.setController(controller);
        loader.setLocation(getClass().getResource("AjouterEvenement.fxml"));
        
        Parent root1 = (Parent) loader.load();
        
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    
    ArrayList<Evenement> verifierEvenement(ZonedDateTime dateFocus) throws SQLException
    {
        String name=null;
        ArrayList<Evenement> tableauEv=new ArrayList<Evenement>();
        String url="jdbc:mysql://localhost/dai";
	Connection conn=DriverManager.getConnection(url, "root", "");

            
	PreparedStatement ps1=conn.prepareStatement("Select * from evenement where EXTRACT(month FROM date_ev)=? and EXTRACT(year FROM date_ev)=? order by debut_ev"); //verifiyi 7ta l3am extract year form date_ev=
        ps1.setInt(1,dateFocus.getMonth().getValue());
        ps1.setInt(2, dateFocus.getYear());
	ResultSet res1=ps1.executeQuery();
	while(res1.next())
        {
           
            PreparedStatement ps2=conn.prepareStatement("Select nom_prof,prenom_prof from professeur where id_prof=?");
            ps2.setInt(1,res1.getInt("id_prof")); 
            ResultSet res2=ps2.executeQuery();
            if(res2.next()){
                name=res2.getString("nom_prof")+" "+res2.getString("prenom_prof");
            }
            Evenement e=new Evenement();
            e.id_ev=res1.getInt("id_ev");
            e.id_prof=res1.getInt("id_prof");
            e.proprietaire=name;
            e.nom=res1.getString("nom_ev");
            e.date=res1.getDate("date_ev").toLocalDate();
            e.debut=res1.getString("debut_ev");
            e.fin=res1.getString("fin_ev");
            e.description=res1.getString("description_ev");
            e.id_prof=res1.getInt("id_prof");
            e.id_user_courant=id_user;
            tableauEv.add(e);
        }
        return tableauEv;
    }
    
    public void refresh(ActionEvent e)
    {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        calendrier.getChildren().clear();
        container_V.getChildren().clear();
        try {
            drawCalendar();
            jour_selectione.setText(today.getDayOfMonth()+"/"+today.getMonthValue()+"/"+today.getYear());
            chargerEvents();
        } catch (SQLException ex) {
            Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Professeur.Etudiants;

import static java.lang.Double.parseDouble;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
/**
 * FXML Controller class
 * @author CHARI Milouda
 */
public class EtudiantsController implements Initializable {
    private int id_user;
    public void setIdUser(int id_user) {
        this.id_user = id_user;
    }
    @FXML private TableColumn<Etudiants, String> assiduite;
    @FXML private TableColumn<Etudiants, String> ds1;
    @FXML private TableColumn<Etudiants, String> ds2;
    @FXML private TableColumn<Etudiants, String> ds3;
    @FXML private TableColumn<Etudiants, String> moyenne;
    @FXML private TableColumn<Etudiants, Integer> id;
    @FXML private TableColumn<Etudiants, String> naissance;
    @FXML private TableColumn<Etudiants, String> nom;
    @FXML private TableColumn<Etudiants, String> numApog;
    @FXML private TableColumn<Etudiants, String> prenom;
    @FXML private TableView<Etudiants> table;
    @FXML private Label matiere1;
    @FXML private Label matiere2; 
    ObservableList<Etudiants> listEtud=FXCollections.observableArrayList();
    ObservableList<Etudiants> datalist=FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            nbrMatieres();
        } catch (SQLException ex) {
            Logger.getLogger(EtudiantsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void nbrMatieres() throws SQLException
        {
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps2=conn.prepareStatement(" SELECT COUNT(*) FROM MATIERE WHERE id_prof=? ");
            ps2.setInt(1, id_user);
            ResultSet res2=ps2.executeQuery();
            while(res2.next())
            {
                int nbrMatieres=res2.getInt(1);
                if(nbrMatieres==1)
                {   matiere2.setVisible(false);
                }
            }  
        PreparedStatement ps1=conn.prepareStatement("Select intitule_matiere from matiere where id_prof=?");
        ps1.setInt(1,id_user);
        ResultSet res=ps1.executeQuery();
        String []tab={"","",""};
        int i=0;
        while(res.next())
        {   String item=res.getString(1);
            //item.replaceAll("'", "\\'");//ne donne aucun effet
            tab[i]=item;
            if(i==0)
            {
                matiere1.setText(tab[0]);
            }
            else{
                if(i==1)
                {
                    matiere2.setText(tab[1]);
                }
            }
            i++;
        }
        PreparedStatement ps=conn.prepareStatement(" SELECT id_matiere FROM MATIERE WHERE id_prof=? ");
        ps.setInt(1, id_user);
        ResultSet res1=ps.executeQuery();
        int ii=0;
        int []tab2={0,0,0};
        while(res1.next())
        {
            tab2[ii]=res1.getInt(1);
            System.out.println(res1.getInt(1));//khedama
            ii++;
        }
        System.out.println("tab2.length="+tab2.length+tab2[0]+ ' '+tab2[1]+ ' '+tab2[2]);
        if(tab2[0]!=0 &&tab2[1]==0&&tab2[2]==0)
        {
            showEtudiants(tab2[0]);
        }
        else
        {
            if(tab2[0]!=0 &&tab2[1]!=0&&tab2[2]==0)
            {
                showEtudiants2(tab2[0],tab2[1]);
            }
            else
            {
                showEtudiants3(tab2[0],tab2[1],tab2[2]);
            }
        }}
    public void showEtudiants(int idM) throws SQLException
    {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps3=conn.prepareStatement("select * from etudiant");
        ResultSet res3=ps3.executeQuery();
        while(res3.next())
        {
            int id_=res3.getInt("id_etudiant");
            System.out.println(id_);
            String nom_=res3.getString("nom_etudiant");
            String prenom_=res3.getString("prenom_etudiant");
            String naissance_=res3.getDate("dateNaissance_etudiant").toString();
            String numApg_=res3.getString("numeroApogee_etudiant");
            PreparedStatement ps4=conn.prepareStatement("select * from note_matiere where id_etudiant=? and id_matiere=?");
            ps4.setInt(1,id_);
            ps4.setInt(2, idM);
            ResultSet res4=ps4.executeQuery();
            if(res4.next())
            {   
                String ds1=res4.getString("ds1");
                String ds2=res4.getString("ds2");
                String ds3=res4.getString("ds3");
                String assiduite=res4.getString("assiduite");
                String avg = res4.getString("valeur_note_matiere");
                listEtud.add(new Etudiants(id_,nom_,prenom_,naissance_,numApg_,ds1,ds2,ds3,assiduite,avg)); 
            }
            else
            {
                listEtud.add(new Etudiants(id_,nom_,prenom_,naissance_,numApg_));
            }
        }
        id.setCellValueFactory(new PropertyValueFactory<Etudiants,Integer>("id_"));
        numApog.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("numApg_"));
        nom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("nom_"));
        prenom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("prenom_"));
        naissance.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("naissance_"));
        ds1.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds1_"));
        ds2.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds2_"));
        ds3.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds3_"));
        assiduite.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("assiduite"));
        moyenne.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("avg"));
        table.setItems(listEtud);
        table.setEditable(true);
        ds1.setCellFactory(TextFieldTableCell.forTableColumn());
        ds2.setCellFactory(TextFieldTableCell.forTableColumn());
        ds3.setCellFactory(TextFieldTableCell.forTableColumn());
        assiduite.setCellFactory(TextFieldTableCell.forTableColumn());
        conn.close();
    }
    private void showEtudiants2(int idM1, int idM2) throws SQLException {
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement ps3=conn.prepareStatement("select * from etudiant");
        ResultSet res3=ps3.executeQuery();
        while(res3.next())
        {
            int id_=res3.getInt("id_etudiant");
            System.out.println(id_);
            String nom_=res3.getString("nom_etudiant");
            String prenom_=res3.getString("prenom_etudiant");
            String naissance_=res3.getDate("dateNaissance_etudiant").toString();
            String numApg_=res3.getString("numeroApogee_etudiant");
            System.out.println(nom_+" "+prenom_+" "+naissance_+" "+numApg_+" ");
            PreparedStatement ps4=conn.prepareStatement("select * from note_matiere where id_etudiant=? and id_matiere=? ");
            ps4.setInt(1,id_);
            ps4.setInt(2, idM1);
            //ps4.setInt(3, idM2);
            ResultSet res4=ps4.executeQuery();
            if(res4.next())
            {   System.out.println("dhb");
                String ds1=res4.getString("ds1");
                String ds2=res4.getString("ds2");
                String ds3=res4.getString("ds3");
                String assiduite=res4.getString("assiduite");
                String avg=res4.getString("valeur_note_matiere");
                listEtud.add(new Etudiants(id_,nom_,prenom_,naissance_,numApg_,ds1,ds2,ds3,assiduite,avg)); 

            }
            else
            {
                listEtud.add(new Etudiants(id_,nom_,prenom_,naissance_,numApg_));
            }
        }
        id.setCellValueFactory(new PropertyValueFactory<Etudiants,Integer>("id_"));
        numApog.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("numApg_"));
        nom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("nom_"));
        prenom.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("prenom_"));
        naissance.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("naissance_"));
        ds1.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds1_"));
        ds2.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds2_"));
        ds3.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("ds3_"));
        assiduite.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("assiduite"));
        moyenne.setCellValueFactory(new PropertyValueFactory<Etudiants,String>("avg"));
        table.setItems(listEtud);
        table.setEditable(true);
        System.out.println("taille"+listEtud.size());///fonctionne 
        ds1.setCellFactory(TextFieldTableCell.forTableColumn());
        ds2.setCellFactory(TextFieldTableCell.forTableColumn());
        ds3.setCellFactory(TextFieldTableCell.forTableColumn());
        assiduite.setCellFactory(TextFieldTableCell.forTableColumn());conn.close(); }
    private void showEtudiants3(int i, int i0, int i1) {
    }
    @FXML void oneditchange(TableColumn.CellEditEvent<Etudiants,String> etudiantDoubleEditEvent) {
        Etudiants e =table.getSelectionModel().getSelectedItem();
        e.setDs1_(etudiantDoubleEditEvent.getNewValue());
        System.out.println(e.getId_()+" "+e.getAssiduite()+" "+e.getDs1_()+" "+e.getDs2_());
    }    
    @FXML void oneditchange2(TableColumn.CellEditEvent<Etudiants,String> etudiantDoubleEditEvent) {
        Etudiants e =table.getSelectionModel().getSelectedItem();
        e.setDs2_(etudiantDoubleEditEvent.getNewValue());
        System.out.println(e.getId_()+" "+e.getAssiduite()+" "+e.getDs1_()+" "+e.getDs2_());
    }
    @FXML void oneditchange3(TableColumn.CellEditEvent<Etudiants,String> etudiantDoubleEditEvent) {
        Etudiants e =table.getSelectionModel().getSelectedItem();
        e.setDs3_(etudiantDoubleEditEvent.getNewValue());
        System.out.println(e.getId_()+" "+e.getAssiduite()+" "+e.getDs1_()+" "+e.getDs2_());
    }
    @FXML void oneditchange4(TableColumn.CellEditEvent<Etudiants,String> etudiantDoubleEditEvent) {
        Etudiants e =table.getSelectionModel().getSelectedItem();
        e.setAssiduite(etudiantDoubleEditEvent.getNewValue());
        System.out.println(e.getId_()+" "+e.getAssiduite()+" " + e.getDs1_()+" "+e.getDs2_());
    }
    public void add_edit(ActionEvent ae) throws SQLException
    {
        datalist=table.getItems(); 
        String url="jdbc:mysql://localhost/dai";
        Connection conn=DriverManager.getConnection(url, "root", "");
        PreparedStatement pstmt=conn.prepareStatement(" SELECT id_matiere FROM MATIERE WHERE id_prof=? ");
        pstmt.setInt(1, id_user);
        ResultSet res1=pstmt.executeQuery();
        while(res1.next())
        {
            int id=res1.getInt("id_matiere");
            for(Etudiants e : datalist)
                {
                    int idE=e.getId_();
                    PreparedStatement prs=conn.prepareStatement("select id_note_matiere from note_matiere where id_matiere=? and id_etudiant=?");
                    prs.setInt(1,id);
                    prs.setInt(2, idE);
                    ResultSet ress=prs.executeQuery();
                    if(ress.next()){
                        int id_note=ress.getInt(1);
                        PreparedStatement ps=conn.prepareStatement("UPDATE note_matiere set id_matiere=?,id_etudiant=?,ds1=?,ds2=?,ds3=?,assiduite=?,valeur_note_matiere=? where id_note_matiere=?");
                        ps.setInt(1,id );
                        ps.setInt(2, idE);
                        ps.setInt(8,id_note);
                        //ds1
                        String ds1=e.getDs1_();
                        if(ds1==null){ps.setString(3,"" );}else{ps.setString(3,e.getDs1_() );}
                        //ds2
                        String ds2=e.getDs2_();
                        if(ds2==null){ps.setString(4, "");}else{ps.setString(4, e.getDs2_());}
                        //ds3
                        String ds3=e.getDs3_();
                        if(ds3==null){ps.setString(5, "");}else{ps.setString(5, e.getDs3_());}
                        //  assiduite
                        String assiduitee=e.getAssiduite();
                        if(assiduitee==null){ps.setString(6, "");}else{ps.setString(6, e.getAssiduite());}
                        ps.setString(7,"" );
                        int nbr1=ps.executeUpdate();
                        System.out.print(nbr1+" lignes modifiées");
                        
                    }
                    else{
                        System.out.println("non");
                        PreparedStatement prepst=conn.prepareStatement(" SELECT count(*) FROM note_matiere");
                        ResultSet resultset=prepst.executeQuery();
                        if(resultset.next())
                        {
                            int nbr_lignes=resultset.getInt(1);
                             System.out.println("nbr_lignes"+nbr_lignes);

                            if(nbr_lignes!=0)
                            {
                                PreparedStatement preps=conn.prepareStatement(" SELECT id_note_matiere FROM note_matiere order by id_note_matiere desc limit 1 ");
                                ResultSet rs=preps.executeQuery();
                                if(rs.next())
                                {
                                    int idnote_mat=rs.getInt("id_note_matiere");
                                    System.out.println(idnote_mat);
                                    idnote_mat++; 
                                    PreparedStatement ps=conn.prepareStatement("INSERT INTO note_matiere (`id_note_matiere`, `id_matiere`, `id_etudiant`, `ds1`, `ds2`, `ds3`, `assiduite`, `valeur_note_matiere`) VALUES(?,?,?,?,?,?,?,?) ");
                                    ps.setInt(1,idnote_mat );
                                    ps.setInt(2, id);
                                    ps.setInt(3,idE);
                                    String ds1=e.getDs1_();
                                    if(ds1==null){ps.setString(4, "");}else{ps.setString(4, e.getDs1_());}
                                    String ds2=e.getDs2_();
                                    if(ds2==null){ps.setString(5, "");}else{ps.setString(5, e.getDs2_());}
                                    String ds3=e.getDs3_();
                                    if(ds3==null){ps.setString(6, "");}else{ps.setString(6, e.getDs3_());}
                                    //assiduite
                                    String assiduitee=e.getAssiduite();
                                    if(assiduitee==null){ps.setString(7, "");}else{ps.setString(7, e.getAssiduite()); }
                                    ps.setString(8,"" );
                                    int nbr1=ps.executeUpdate();
                                    System.out.print(nbr1+" lignes  ajoutées ");
                                    idnote_mat++;
                                 }
                            }
                            else
                            {
                                PreparedStatement ps=conn.prepareStatement("INSERT INTO note_matiere (`id_note_matiere`, `id_matiere`, `id_etudiant`, `ds1`, `ds2`, `ds3`, `assiduite`, `valeur_note_matiere`) VALUES(?,?,?,?,?,?,?,?) ");
                                ps.setInt(1,1 );
                                ps.setInt(2, id);
                                ps.setInt(3,idE);
                                //ds1
                                String ds1=e.getDs1_();
                                if(ds1==null){ps.setString(4, "");}else{ps.setString(4, e.getDs1_());}
                                String ds2=e.getDs2_();
                                if(ds2==null){ps.setString(5, "");}else{ps.setString(5, e.getDs2_());}
                                String ds3=e.getDs3_();
                                if(ds3==null){ps.setString(6, "");}else{ps.setString(6, e.getDs3_());}
                                String assiduitee=e.getAssiduite();
                                if(assiduitee==null){ps.setString(7, "");}else{ps.setString(7, e.getAssiduite()); }
                                ps.setString(8,"" );
                                int nbr1=ps.executeUpdate();
                                System.out.print(nbr1+" lignes  ajoutées ");
                             }
                            
                        }
              
                }    
        } }
        conn.close();
    }    
    @FXML void calculerAvg(ActionEvent event) throws SQLException {
        ObservableList<Etudiants> l=FXCollections.observableArrayList();
                String url="jdbc:mysql://localhost/dai";
                Connection conn=DriverManager.getConnection(url, "root", "");
                PreparedStatement pstmt=conn.prepareStatement(" SELECT id_matiere FROM MATIERE WHERE id_prof=? ");
                pstmt.setInt(1, id_user);
                ResultSet res1=pstmt.executeQuery();
                while(res1.next())
                {
                    int idd=res1.getInt("id_matiere");
                    l=table.getItems();
                    for(Etudiants e : l)
                    {
                        ArrayList <Double> arr=new ArrayList();
                        String ds1=e.getDs1_();
                        String ds2=e.getDs2_();
                        String ds3=e.getDs3_();
                        String assiduite=e.getAssiduite();
                        if(ds1.isBlank() &&ds2.isBlank()&&ds3.isBlank()&&assiduite.isBlank())
                        {
                            System.out.println("invalid ");
                            continue;
                        }
                        if(!ds1.isBlank()&& isNumeric(ds1))
                        {
                            arr.add(parseDouble(ds1));
                        }if(!ds2.isBlank() && isNumeric(ds2))
                        {
                            arr.add(parseDouble(ds2));
                        }
                        if(!ds3.isBlank()&& isNumeric(ds3))
                        {
                            arr.add(parseDouble(ds3));
                        }
                        if(!assiduite.isBlank()&& isNumeric(assiduite))
                        {
                            arr.add(parseDouble(assiduite));
                        }

                        double sum=0;
                            for (double i :arr)
                            {
                                System.out.println(i);
                                sum+=i;
                            }
                            System.out.println("somme="+sum);//ok
                            double avg=(double)sum/arr.size();//ok 
                            System.out.println("avg="+avg);//ok
                            if(Double.isNaN(avg))
                            {
                             
                                PreparedStatement ps=conn.prepareStatement("update note_matiere set valeur_note_matiere=? where id_matiere=? and id_etudiant=? ");
                                ps.setString(1, "");
                                ps.setInt(2, idd);
                                ps.setInt(3,e.getId_());
                                int nbr=ps.executeUpdate();
                                System.out.println(nbr+ "lignes modifiées ");
                            }
                            else{
                                DecimalFormat df = new DecimalFormat("0.00");
                                String avg_s=String.valueOf(df.format(avg));
                                PreparedStatement ps=conn.prepareStatement("update note_matiere set valeur_note_matiere=? where id_matiere=? and id_etudiant=? ");
                                ps.setString(1, avg_s);
                                ps.setInt(2, idd);
                                ps.setInt(3,e.getId_());
                                int nbr=ps.executeUpdate();
                                System.out.println(nbr+ "lignes modifiées ");
                            }
                            }
                         table.getItems().clear();
                         showEtudiants(idd);
                }
                conn.close();
    }
    private boolean isNumeric(String s) {
        try{
            Double.parseDouble(s);return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        
        
    }
}

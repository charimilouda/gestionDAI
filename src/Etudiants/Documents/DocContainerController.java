/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Etudiants.Documents;


import Professeur.Documents.*;
import java.io.*;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class DocContainerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int iddoc;

    public void setIddoc(int iddoc) {
        this.iddoc = iddoc;
    }
    
    @FXML
    private Label datedepot_doc;

    @FXML
    private Label extension_doc;

    @FXML
    private Label nom_doc;

    @FXML
    private Label proprietaire_doc;

    @FXML
    private Label taille_doc;

    @FXML
    private Label titre_doc;
    
        Blob contenu_doc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void setNom(String str)
    {
          nom_doc.setText(str);
    }
    
    public void setTitre(String str)
    {
          titre_doc.setText(str);
    }
    public void setExtension(String str)
    {
          extension_doc.setText(str);
    }
    public void setTaille(String str)
    {
          taille_doc.setText(str);
    }
    public void setDateDepot(String str)
    {
          datedepot_doc.setText(str);
    }
    public void setProprietaire(String str)
    {
          proprietaire_doc.setText(str);
    }
    
    public void setContenu(Blob contenu)
    {
        contenu_doc=contenu;
    }
    
    public void download(ActionEvent e) throws FileNotFoundException, SQLException, IOException
    {
        FileChooser save=new FileChooser();
	FileChooser.ExtensionFilter fileExtensions =new FileChooser.ExtensionFilter("*."+extension_doc.getText(),"document");
        save.getExtensionFilters().add(fileExtensions);

	save.setInitialFileName(nom_doc.getText());
	File file=save.showSaveDialog(null);
        
        
	byte byteArray[] = contenu_doc.getBytes(1,(int)contenu_doc.length());
        String name=file.getAbsoluteFile()+"."+extension_doc.getText();
        file=new File(name);
	FileOutputStream output=new FileOutputStream(file);
	output.write(byteArray);
        output.close();
        
    }
    
    
}

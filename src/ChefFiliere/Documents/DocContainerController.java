/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Documents;


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
    int destination;
    public void setDestination(int dest)
    {
        destination=dest;
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
    
    public void modifier(ActionEvent e) throws IOException
    {
        ModifierDocumentController c=new ModifierDocumentController();
        c.setId(iddoc);
        c.setTitre(titre_doc.getText());
        c.setContenu(contenu_doc);
        c.setName(nom_doc.getText()+"."+extension_doc.getText());
        c.setTaille(Long.valueOf(taille_doc.getText()));
        c.setDestination(destination);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("ModifierDocument.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
   
    public void supprimer(ActionEvent e) throws IOException
    {
        SupprimerDocumentController c=new SupprimerDocumentController();
        c.setId(iddoc);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("SupprimerDocument.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
}

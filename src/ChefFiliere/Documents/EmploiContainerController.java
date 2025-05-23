/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ChefFiliere.Documents;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Doha Ess
 */
public class EmploiContainerController implements Initializable {

    int idemploi;

    public void setIdemploi(int idemploi) {
        this.idemploi = idemploi;
    }
    
    @FXML
    private Label datedepot_doc;

    @FXML
    private Label extension_doc;

    @FXML
    private Label nom_doc;

    @FXML
    private Label taille_doc;

    @FXML
    private Label titre_doc;
    Blob contenu_doc;
    
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

    
    public void setContenu(Blob contenu)
    {
        contenu_doc=contenu;
    }
    
    @FXML
    void download(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
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

    @FXML
    void modifier(ActionEvent event) throws IOException {
        ModifierEmploiController c=new ModifierEmploiController();
        c.setId(idemploi);
        c.setTitre(titre_doc.getText());
        c.setContenu(contenu_doc);
        c.setName(nom_doc.getText()+"."+extension_doc.getText());
        c.setTaille(Long.valueOf(taille_doc.getText()));
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("ModifierEmploi.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    void supprimer(ActionEvent event) throws IOException {
        SupprimerEmploiController c=new SupprimerEmploiController();
        c.setId(idemploi);
        FXMLLoader loader=new FXMLLoader();
        loader.setController(c);
        loader.setLocation(getClass().getResource("SupprimerEmploi.fxml"));
        Parent root1 = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Authentification;

/**
 *
 * @author user
 */
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Main extends Application 
{

	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Parent root=FXMLLoader.load(getClass().getResource("Authentification.fxml")); 
		Scene scene=new Scene(root,1366,700);
		
		stage.setTitle("Application de gestion");
		stage.setResizable(false);
		//stage.setMaximized(true);
		
		Text text=new Text();
		text.setText("Application de gestion des etudiants");
		text.setFont(Font.font("Arial",56));
		text.setFill(Color.RED);
		
		
		stage.setScene(scene);
		stage.show();
		
		
		
		}
}

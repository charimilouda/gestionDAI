/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package Authentification;


import Professeur.Home.HomeController;
import ChefFiliere.Home.Home2Controller;
import Etudiants.Home.Home3Controller;

import java.io.IOException;
import java.sql.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthentificationController
{
	@FXML private TextField tf_email;
	@FXML private TextField tf_mdp;
	@FXML private Label label_msg_err;
        int id_user_int;
	public void clicked(ActionEvent e) throws SQLException, IOException
	{
		String email=tf_email.getText();
		String mdp=tf_mdp.getText();
		if(email.isBlank()||mdp.isBlank()) //controle saisie
		{
			label_msg_err.setText("Veuillez remplir les champs");
		}
		else
		{
			String url="jdbc:mysql://localhost/dai";
			Connection conn=DriverManager.getConnection(url, "root", "");
			PreparedStatement ps1=conn.prepareStatement("Select * from authentification where email=? and password=?");
			ps1.setString(1, email);
			ps1.setString(2, mdp);
			ResultSet res1=ps1.executeQuery();
			if(res1.next()) 
			{       
                                int status=res1.getInt("status");
                                switch(status)
                                {
                                    case 0:redirectEtud(e,email);break;
                                    case 1:redirectProf(e,email);break;
                                    case 2:redirectChef(e,email);break;
                                    
                                }
			}
			else
			{
				label_msg_err.setText("Email ou Mot de passe incorrects!");
				tf_email.setText("");
				tf_mdp.setText("");
			}
		}
		
	}

        public void redirectProf(ActionEvent e,String email) throws IOException, SQLException
        {
            System.out.println(email);
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps1=conn.prepareStatement("Select id_prof from professeur where email_prof=?");
            ps1.setString(1, email);
            ResultSet res1=ps1.executeQuery();
            Parent root=null;
            if(res1.next())
            {
                id_user_int=res1.getInt("id_prof");
                HomeController c=new HomeController();
                c.setIdSessionCourante(id_user_int);
                FXMLLoader loader=new FXMLLoader();
                loader.setController(c);
                loader.setLocation(getClass().getResource("../Professeur/Home/Home.fxml"));
                root=loader.load();

            }

            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
         public void redirectChef(ActionEvent e,String email) throws IOException, SQLException
        {
            System.out.println(email);
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps1=conn.prepareStatement("Select id_prof from professeur where email_prof=?");
            ps1.setString(1, email);
            ResultSet res1=ps1.executeQuery();
            Parent root=null;
            if(res1.next())
            {
                id_user_int=res1.getInt("id_prof");
                Home2Controller c=new Home2Controller();
                c.setIdSessionCourante(id_user_int);
                FXMLLoader loader=new FXMLLoader();
                loader.setController(c);
                loader.setLocation(getClass().getResource("../ChefFiliere/Home/Home.fxml"));
                root=loader.load();
            }

            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
         public void redirectEtud(ActionEvent e,String email) throws IOException, SQLException
        {
         
            String url="jdbc:mysql://localhost/dai";
            Connection conn=DriverManager.getConnection(url, "root", "");
            PreparedStatement ps1=conn.prepareStatement("Select id_etudiant from etudiant where email_etudiant=?");
            ps1.setString(1, email);
            ResultSet res1=ps1.executeQuery();
            Parent root=null;
            if(res1.next())
            {
                id_user_int=res1.getInt("id_etudiant");
                Home3Controller c=new Home3Controller();
                c.setIdSessionCourante(id_user_int);
                FXMLLoader loader=new FXMLLoader();
                loader.setController(c);
                loader.setLocation(getClass().getResource("../Etudiants/Home/Home3.fxml"));
                root=loader.load();
            }
            Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
}
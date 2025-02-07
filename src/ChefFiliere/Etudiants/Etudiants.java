/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChefFiliere.Etudiants;

/**
 *
 * @author CHARI Milouda
 */
public class Etudiants {
    int id_;
    String nom_,prenom_,naissance_,numApg_,tel_,email_;
    public String getTel_() {
        return tel_;
    }

    public void setTel_(String tel_) {
        this.tel_ = tel_;
    }

    public String getEmail_() {
        return email_;
    }

    public void setEmail_(String email_) {
        this.email_ = email_;
    }
    public String getNumApg_() {
        return numApg_;
    }
    public void setNumApg_(String numApg_) {
        this.numApg_ = numApg_;
    }
    public int getId_() {
        return id_;
    }
    public void setId_(int id_) {
        this.id_ = id_;
    }
    public String getNom_() {
        return nom_;
    }
    public void setNom_(String nom_) {
        this.nom_ = nom_;
    }
    public String getPrenom_() {
        return prenom_;
    }
    public void setPrenom_(String prenom_) {
        this.prenom_ = prenom_;
    }
    public String getNaissance_() {
        return naissance_;
    }
    public void setNaissance_(String naissance_) {
        this.naissance_ = naissance_;
    }

    public Etudiants(int id_, String nom_, String prenom_, String naissance_, String numApg_, String tel_, String email_) {
        this.id_ = id_;
        this.nom_ = nom_;
        this.prenom_ = prenom_;
        this.naissance_ = naissance_;
        this.numApg_ = numApg_;
        this.tel_ = tel_;
        this.email_ = email_;
        
        
    }

    
    
    
}

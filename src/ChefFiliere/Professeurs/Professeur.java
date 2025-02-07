/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ChefFiliere.Professeurs;

/**
 *
 * @author CHARI Milouda
 */
public class Professeur {
    private int id_;
    private String nom_,prenom_,tel_,email_,_genre_;
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
    public String getGenre_() {
        return _genre_;
    }
    public void setGenre_(String _genre_) {
        this._genre_ = _genre_;
    }
    public Professeur(int id_, String nom_, String prenom_, String tel_, String email_, String _genre_) {
        this.id_ = id_;
        this.nom_ = nom_;
        this.prenom_ = prenom_;
        this.tel_ = tel_;
        this.email_ = email_;
        this._genre_ = _genre_;
    }
    
}

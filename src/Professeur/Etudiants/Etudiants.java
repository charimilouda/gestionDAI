/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Professeur.Etudiants;

/**
 *
 * @author user
 */
public class Etudiants {
    //variables d'insatnce & getters &setters
    int id_;
    String nom_,prenom_,naissance_,numApg_;
    
    String ds1_,ds2_,ds3_,assiduite,avg;

    public Etudiants(int id_, String nom_, String prenom_, String naissance_, String numApg_) {
        this.id_ = id_;
        this.nom_ = nom_;
        this.prenom_ = prenom_;
        this.naissance_ = naissance_;
        this.numApg_ = numApg_;
    }

    public Etudiants(int id_, String nom_, String prenom_, String naissance_, String numApg_, String ds1_, String ds2_, String ds3_, String assiduite, String avg) {
        this.id_ = id_;
        this.nom_ = nom_;
        this.prenom_ = prenom_;
        this.naissance_ = naissance_;
        this.numApg_ = numApg_;
        this.ds1_ = ds1_;
        this.ds2_ = ds2_;
        this.ds3_ = ds3_;
        this.assiduite = assiduite;
        this.avg = avg;
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

    public String getNumApg_() {
        return numApg_;
    }

    public void setNumApg_(String numApg_) {
        this.numApg_ = numApg_;
    }

    public String getDs1_() {
        return ds1_;
    }

    public void setDs1_(String ds1_) {
        this.ds1_ = ds1_;
    }

    public String getDs2_() {
        return ds2_;
    }

    public void setDs2_(String ds2_) {
        this.ds2_ = ds2_;
    }

    public String getDs3_() {
        return ds3_;
    }

    public void setDs3_(String ds3_) {
        this.ds3_ = ds3_;
    }

    public String getAssiduite() {
        return assiduite;
    }

    public void setAssiduite(String assiduite) {
        this.assiduite = assiduite;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public Etudiants(String avg) {
        this.avg = avg;
    }

    
    
}

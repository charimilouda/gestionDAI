/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Etudiants.Notes;

/**
 *
 * @author CHARI Milouda
 * 
 */
public class NoteMatiere {
    private String matiere_,ds1,ds2,ds3,assiduite,moyenne;
    public String getMatiere_() {
        return matiere_;
    }
    public void setMatiere_(String matiere_) {
        this.matiere_ = matiere_;
    }
    public String getDs1() {
        return ds1;
    }
    public void setDs1(String ds1) {
        this.ds1 = ds1;
    }
    public String getDs2() {
        return ds2;
    }
    public void setDs2(String ds2) {
        this.ds2 = ds2;
    }
    public String getDs3() {
        return ds3;
    }
    public void setDs3(String ds3) {
        this.ds3 = ds3;
    }
    public String getAssiduite() {
        return assiduite;
    }
    public void setAssiduite(String assiduite) {
        this.assiduite = assiduite;
    }
    public String getMoyenne() {
        return moyenne;
    }
    public void setMoyenne(String moyenne) {
        this.moyenne = moyenne;
    }
    public NoteMatiere(String matiere_, String ds1, String ds2, String ds3, String assiduite, String moyenne) {
        this.matiere_ = matiere_;
        this.ds1 = ds1;
        this.ds2 = ds2;
        this.ds3 = ds3;
        this.assiduite = assiduite;
        this.moyenne = moyenne;
    }
    public NoteMatiere(String matiere_) {
        this.matiere_ = matiere_;
    }
    
}

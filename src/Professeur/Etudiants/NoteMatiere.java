/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Professeur.Etudiants;

/**
 *
 * @author CHARI Milouda
 */
public class NoteMatiere {
    private int id_note;
    private int id_etud;
    private int id_matiere;
    private double note;

    public NoteMatiere(int id_note, int id_etud, int id_matiere, double note) {
        this.id_note = id_note;
        this.id_etud = id_etud;
        this.id_matiere = id_matiere;
        this.note = note;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getId_etud() {
        return id_etud;
    }

    public void setId_etud(int id_etud) {
        this.id_etud = id_etud;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public NoteMatiere( double note) {
        
        this.note = note;
    }
    
    
}

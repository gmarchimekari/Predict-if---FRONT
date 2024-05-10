/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author ttvu
 */
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String genre;
    private String numTel;
    @OneToMany(mappedBy="employe")
    private List<Consultation> listeConsultation;
    private Boolean disponible = true;
    
    public Employe() {
    }

    public Employe(String prenom, String nom, String mail, String motDePasse, String genre, String numTel) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.genre = genre;
        this.numTel = numTel;
    }

    public void ajouterConsultation(Consultation consultation){
        this.listeConsultation.add(consultation);
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Consultation> getListeConsultation() {
        return listeConsultation;
    }
    
    public Consultation getConsultationenCours(){
        Consultation consultationEnCours = this.listeConsultation.get(this.listeConsultation.size()-1);
        return consultationEnCours;
    }
    
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getMail() {
        return mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public String getGenre() {
        return genre;
    }

    public String getNumTel() {
        return numTel;
    }
   
    
}

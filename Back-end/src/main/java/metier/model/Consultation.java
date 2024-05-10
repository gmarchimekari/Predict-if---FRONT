/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author ttvu
 */
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateConsultation;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Client client;
    private String commentaire;

    public Consultation(Employe employe, Medium medium, Client client) {
        this.employe = employe;
        this.medium = medium;
        this.client = client;
        LocalDateTime datetime = LocalDateTime.now();
        this.dateConsultation = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public Consultation() {
    }

    public Long getId() {
        return Id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public Employe getEmploye() {
        return employe;
    }

    public Medium getMedium() {
        return medium;
    }

    public Client getClient() {
        return client;
    }


    public String getCommentaire() {
        return commentaire;
    }

    @Override
    public String toString() {
        return "Consultation{" + "dateConsultation=" + dateConsultation + ", employe=" + employe + ", medium=" + medium + ", client=" + client + ", commentaire=" + commentaire + '}';
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    
}

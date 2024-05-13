/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author ttvu
 */
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Medium {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String genre;
    private String presentation;
    private String denomination;
    private Integer nbConsultation = 0 ;

    public Long getId() {
        return id;
    }
    
    public String getGenre() {
        return genre;
    }

    public String getDenomination() {
        return denomination;
    }

    public Integer getNbConsultation() {
        return nbConsultation;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }

    public void setNbConsultation(Integer nbConsultation) {
        this.nbConsultation = nbConsultation;
    }

    public Medium() {
    }

    public Medium(String genre, String presentation, String denomination) {
        this.genre = genre;
        this.presentation = presentation;
        this.denomination = denomination;
    }
    
}

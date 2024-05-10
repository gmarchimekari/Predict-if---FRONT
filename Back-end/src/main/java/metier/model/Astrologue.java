/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import javax.persistence.Entity;

/**
 *
 * @author ttvu
 */
@Entity
public class Astrologue extends Medium{
    private String formation;
    private String promotion;

    public Astrologue() {
    }

    public Astrologue(String genre, String presentation, String denomination,String formation, String promotion) {
        super(genre, presentation, denomination);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    
    
}

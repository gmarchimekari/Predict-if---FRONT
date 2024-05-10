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
public class Cartomancien extends Medium{

    public Cartomancien(String genre, String presentation, String denomination) {
        super(genre, presentation, denomination);
    }

    public Cartomancien() {
    }
    
}

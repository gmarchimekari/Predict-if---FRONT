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
public class Spirite extends Medium {
    private String support;

    public Spirite() {
    }

    public Spirite(String genre, String presentation, String denomination, String support) {
        super(genre, presentation, denomination);
        this.support = support;
    }
    
}

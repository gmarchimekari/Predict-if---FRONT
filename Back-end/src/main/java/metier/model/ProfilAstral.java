/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.model;

import javax.persistence.Embeddable;

/**
 *
 * @author ttvu
 */
@Embeddable
public class ProfilAstral {
    private String couleur;
    private String signeZodiaque;
    private String signeChinois;
    private String animalTotem;

    public ProfilAstral() {
        
    }
    
    
    public ProfilAstral(String couleur, String signeZodiaque, String signeChinois, String animalTotem) {
        this.couleur = couleur;
        this.signeZodiaque = signeZodiaque;
        this.signeChinois = signeChinois;
        this.animalTotem = animalTotem;
    }
    
    
    public String getCouleur() {
        return couleur;
    }

    public String getSigneZodiaque() {
        return signeZodiaque;
    }

    public String getSigneChinois() {
        return signeChinois;
    }

    public String getAnimalTotem() {
        return animalTotem;
    }
    

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public void setSigneZodiaque(String signeZodiaque) {
        this.signeZodiaque = signeZodiaque;
    }

    public void setSigneChinois(String signeChinois) {
        this.signeChinois = signeChinois;
    }

    public void setAnimalTotem(String animalTotem) {
        this.animalTotem = animalTotem;
    }
    
    
    
}

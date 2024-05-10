package metier.model;



import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ttvu
 */
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    private String nom;
    private String prenom;
    @Column(unique = true)
    private String mail;
    private String motDePasse;
    private String adressePostale;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    private String numTel;
    private Double latitude;
    private Double longitude;
    
    @Embedded
    private ProfilAstral profilAstral;
    @OneToMany(mappedBy="client")
    private List<Consultation> listeRdv;
    
    public void ajouterConsultation(Consultation consultation){
        this.listeRdv.add(consultation);
    }
   
    public Client(){}
    
    public Client(String nom,String prenom,String mail,String adressePostale,Date dateNaissance,String numTel){
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.adressePostale = adressePostale;
        this.dateNaissance = dateNaissance;
        this.numTel = numTel;
        
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getNumTel() {
        return numTel;
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

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAdressePostale() {
        return adressePostale;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    

    @Override
    public String toString() {
        return "Client{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", motDePasse=" + motDePasse + ", adressePostale=" + adressePostale + ", latitude=" ;
    }
    
    public String printInfo() {
        return "Client #" + id + ": " + nom.toUpperCase() + " " + prenom + " <" + mail + ">, habitant à " + adressePostale + "]";
        
    }

    public List<Consultation> getListeRdv() {
        return listeRdv;
    }
    
    
}


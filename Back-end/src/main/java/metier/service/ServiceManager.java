package metier.service;


import com.google.maps.model.LatLng;
import dao.ClientDao;
import dao.ConsultationDao;
import dao.EmployeDao;
import dao.JpaUtil;
import dao.MediumDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import metier.model.Astrologue;
import metier.model.Cartomancien;
import metier.model.Client;
import metier.model.Medium;
import metier.model.Consultation;
import metier.model.Employe;
import metier.model.ProfilAstral;
import metier.model.Spirite;
import util.AstroNetApi;
import util.GeoNetApi;
import util.Message;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ttvu
 */
public class ServiceManager {
    public Boolean inscrireClient(Client client){
        System.out.println(client);
        ClientDao clientDao = new ClientDao();
        Boolean result = null;
        try { 
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();
           
           AstroNetApi astroApi = new AstroNetApi();
           
           
           List<String> profil = astroApi.getProfil(client.getPrenom(),client.getDateNaissance());
           String signeZodiaque = profil.get(0);
           String signeChinois = profil.get(1);
           String couleur = profil.get(2);
           String animal = profil.get(3);
           ProfilAstral profilas = new ProfilAstral(couleur,signeZodiaque,signeChinois,animal);
           client.setProfilAstral(profilas);
           
           LatLng coords = GeoNetApi.getLatLng(client.getAdressePostale());
           if (coords != null) {
               client.setLatitude(coords.lat);
                client.setLongitude(coords.lng);
           }
           else {
               System.out.println("Ne peut pas trouver les coordonnées");
           }
           


           clientDao.create(client);
           JpaUtil.validerTransaction();
           
           Message.envoyerMail("service@gmail.com", client.getMail(), "Mail de confirmation", "Inscription Réussie");
           
           result = true;
        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            
             Message.envoyerMail("service@gmail.com", client.getMail(), "Mail d'infirmation", "Inscription échouée");
            
            result = false;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
        
    }
    
    public Client authentifierClient(String mail, String motDePasse){
        ClientDao clientDao = new ClientDao();
        Client result = null;
        try {
           JpaUtil.creerContextePersistance();
           Client test = clientDao.findbyMail(mail);
           if (test != null && test.getMotDePasse().equals(motDePasse)){
               result = test;
           }
        }
        catch (Exception ex) {
           ex.printStackTrace();
            
        }   
         finally {
            JpaUtil.fermerContextePersistance();
        }     
        return  result; 
           
    }
    
     public Employe authentifierEmploye(String mail, String motDePasse){
        EmployeDao employeDao = new EmployeDao();
        Employe result = null;
        try {
           JpaUtil.creerContextePersistance();
           Employe test = employeDao.findbyMail(mail);
           if (test != null && test.getMotDePasse().equals(motDePasse)){
               result = test;
           }
        }
        catch (Exception ex) {
           ex.printStackTrace();
            
        }   
         finally {
            JpaUtil.fermerContextePersistance();
        }     
        return  result; 
           
    }
    
    public Client modifierProfilClient(Client client, String mail, String mdp) {
        ClientDao clientDao = new ClientDao();
        try {
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            // modifier profil
            client.setMail(mail);
            client.setMotDePasse(mdp);
            
            // merge 
            clientDao.update(client);
            
            JpaUtil.validerTransaction();

            System.out.println("Modification réussie");
        } catch (Exception ex) {
            ex.printStackTrace();

            JpaUtil.annulerTransaction();
            
            System.out.println("Modification échouée");

        } finally {
            JpaUtil.fermerContextePersistance();
        }       
        
        return client;
    }
   
    
    public List<Client> consulterListeClients() {
        ClientDao clientDao = new ClientDao();
        List<Client> result = null;
        try {
           JpaUtil.creerContextePersistance();
           result = clientDao.findAll();
        }
        catch (Exception ex) {
           ex.printStackTrace();
        }   
         finally {
            JpaUtil.fermerContextePersistance();
        }     
        return  result; 
    }
    
    public Boolean initialiserEmploye(){
        EmployeDao employeDao = new EmployeDao();
        Boolean result = null;
        try { 
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();
           Employe a = new Employe("Jean","Jacques","jj@paris.fr","1234","H","0101010101");
           Employe b = new Employe("Anne","Marie","an@paris.fr","azerty","F","0201010101");
           Employe c = new Employe("Jean","Philippe","jp@paris.fr","1234","H","0301010101");
           Employe d = new Employe("François","Honoré","fh@paris.fr","azerty","H","0401010101");
           Employe e = new Employe("Martin","Dupont","md@paris.fr","1234","H","0501010101");
           Employe f = new Employe("Louis","Quatorze","lq@paris.fr","azerty","H","0601010101");
           Employe g = new Employe("Espoir","Fraternité","ef@paris.fr","1234","H","0701010101");
           Employe h = new Employe("Matthieu","Thomas","mt@paris.fr","azerty","H","0801010101");
           Employe i = new Employe("Chloé","Camille","cc@paris.fr","1234","F","0901010101");
           Employe j = new Employe("Lara","Fura","lr@paris.fr","azerty","F","0121010101");
           Employe k = new Employe("Gabriella","Guoguen","gg@paris.fr","1234","F","0221010101");
           Employe l = new Employe("Florine","Galvis","fg@paris.fr","azerty","F","0321010101");
           Employe m = new Employe("Reine","DesNeiges","rd@paris.fr","1234","F","0421010101");
           Employe n = new Employe("Pauline","Trem","pt@paris.fr","azerty","F","0521010101");
           Employe o = new Employe("Zoé","Deux","zd@paris.fr","1234","F","0611010101");
           
           employeDao.create(a);
           employeDao.create(b);
           employeDao.create(c);
           employeDao.create(d);
           employeDao.create(e);
           employeDao.create(f);
           employeDao.create(g);
           employeDao.create(h);
           employeDao.create(i);
           employeDao.create(j);
           employeDao.create(k);
           employeDao.create(l);
           employeDao.create(m);
           employeDao.create(n);
           employeDao.create(o);
           
           //employeDao.create(hhh);
           JpaUtil.validerTransaction();
           result = true;
           
           System.out.println("Initialization Employe réussie");
        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            result = false;
            System.out.println("Initialization Employe échouée");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
        
    }
     
    public Boolean initialiserMedium(){
        MediumDao mediumDao = new MediumDao();
        Boolean result = null;
        try { 
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();  
           Cartomancien irma = new Cartomancien("F","Comprenez votre entourage grâce à mes cartes !","Irma");
           Cartomancien mc = new Cartomancien("H","Je suis l'as de trèfle qui pique ton coeur.","MC");
           Spirite gwenaelle = new Spirite("F","Spécialiste des grandes conversations au delà de TOUTES fontières !","Gwenaelle","boule de cristal");
           Spirite tran = new Spirite("H","Votre avenir est devant vous : regardons-le ensemble !","Mr Tran","boule de cristal, patte de lapin");
           Astrologue serena = new Astrologue("F","Basée à Champigny-sur-Marne, Serena vous révèlera son avenir pour éclairer votre passé.","Serena","ENS","2006");
           Astrologue m = new Astrologue("H","Avenir que nous réserves-tu ?.","Mr M","INSA","2010");
           Cartomancien carter = new Cartomancien("H","Piochez votre destinée","Carter");
           Cartomancien hecate = new Cartomancien("F","Trouvez vos atouts pour trouver le bonheur","Hecate");
           Spirite jeanne = new Spirite("F","Vivez longtemps : venez éviter les embuches","Jeanne","boule de cristal");
           Spirite pan = new Spirite("H","Don't worry, be happy","Pan","marc de café");
           Astrologue copernic = new Astrologue("H","Trouvez votre étoile.","Copernic","ENS","1996");
           Astrologue et = new Astrologue("F","Je viens des étoiles pour vous montrer la voie.","ET","INSA","2003");
           Cartomancien dyonisos = new Cartomancien("H","Amours envolées, solitude omniprésente, j'ai la solution !","Dyonisos");
           Cartomancien tornade = new Cartomancien("F","Laissez moi emporter tous vos tracas !","Tornade");
           Spirite athéna = new Spirite("F","Des pouvoirs qui viennent tout droit de Delphes, en descendance directe, à votre service !","Athéna","boule de cristal");
           Spirite davy = new Spirite("H","Votre avenir fraichement chassé","Davy Crockett","patte de lapin");
           Astrologue celui = new Astrologue("H","Le nuit tous les chats sont gris, mais votre destin lui est brillant.","Celui qui savait","ENS","2002");
           Astrologue willy = new Astrologue("F","Gardez les pieds sur terre, les astres viendront à vous.","Willy","ENS","2001");

         
           mediumDao.create(irma);
           mediumDao.create(gwenaelle);
           mediumDao.create(serena);
           mediumDao.create(m);
           mediumDao.create(mc);
           mediumDao.create(tran);
           mediumDao.create(carter);
           mediumDao.create(hecate);
           mediumDao.create(jeanne);
           mediumDao.create(pan);
           mediumDao.create(copernic);
           mediumDao.create(et);
           mediumDao.create(dyonisos);
           mediumDao.create(tornade);
           mediumDao.create(athéna);
           mediumDao.create(davy);
           mediumDao.create(celui);
           mediumDao.create(willy);
           JpaUtil.validerTransaction();
           
           System.out.println("Initialization Médium réussie");
           result = true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            result = false;
            System.out.println("Initialization Médium échouée");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return result;
        
    }
    
    private Employe choisirEmploye(List<Employe> dispo){
        /*LocalDateTime datetime = LocalDateTime.now();
        Date dateActuelle = Date.from(datetime.atZone(ZoneId.systemDefault()).toInstant());
        Instant instant2 = dateActuelle.toInstant();
        LocalDate localDate2 = instant2.atZone(ZoneId.systemDefault()).toLocalDate();*/
        Integer cpt = 0;
        Employe employeMin = dispo.get(0);
        Integer cptEmployeMin = Integer.MAX_VALUE;
        for (Employe employe : dispo){
                cpt= 0;
                List<Consultation> liste = employe.getListeConsultation();
                for (Consultation consultation : liste){
                   
                    cpt++;
                        
                }
                if (cpt < cptEmployeMin){
                    employeMin = employe;
                    cptEmployeMin = cpt;
                }
        } 
        return employeMin;
    }
    
    public Boolean creerConsultation(String medium, Client client){
        ConsultationDao consultationDao = new ConsultationDao();
        MediumDao mediumDao = new MediumDao();
        EmployeDao employeDao = new EmployeDao();
        ClientDao clientDao = new ClientDao();
        List<Employe> employesDispos = null;
        Boolean res = true;
        try { 
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();  

           Medium leMedium = mediumDao.findbyDenomination(medium);
           String genre = leMedium.getGenre();
           employesDispos = employeDao.findbyDispoGenre(genre);
           if (employesDispos.size() == 0) {
               res = false;
               System.out.println("Médium pas disponible pour le moment");
           }
           else {
                    Employe lEmploye = choisirEmploye(employesDispos);
                    Consultation consult = new Consultation(lEmploye,leMedium,client);
                    consultationDao.create(consult);      
                    lEmploye.ajouterConsultation(consult);
                    client.ajouterConsultation(consult);
                    lEmploye.setDisponible(false);
                    clientDao.update(client);
                    employeDao.update(lEmploye);
                    res = true;
                    Message.envoyerNotification(lEmploye.getNumTel(), "Bonjour "+ lEmploye.getPrenom()+ ". Consultation requise pour "+client.getPrenom()+" "+client.getNom()+". Medium à incarner : "+leMedium.getDenomination());
                }
           JpaUtil.validerTransaction();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            res = false;
             System.out.println("Ne peut pas créér cette consultation");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return res;
        
    }
    
    public void demarrerConsultation(Long idConsultation){
            
        ConsultationDao consultationDao = new ConsultationDao();
        try { 
           JpaUtil.creerContextePersistance();

           Consultation consultationEnCours = consultationDao.findbyId(idConsultation);
           Client client = consultationEnCours.getClient();
           Employe employe = consultationEnCours.getEmploye();
           Medium medium = consultationEnCours.getMedium();
           Message.envoyerNotification(client.getNumTel(), "Bonjour "+client.getPrenom()+". J'ai bien reçu votre demande de consultation du "+consultationEnCours.getDateConsultation()
                   +". Vous pouvez dès à présent me contacter au "+employe.getNumTel()+". A tout de suite ! Mediumiquement vôtre, "+medium.getDenomination());

           
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas démarrer cette consultation");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    public void finConsultation(Long idConsultation, String commentaire){
        ConsultationDao consultationDao = new ConsultationDao();
        MediumDao mediumDao = new MediumDao();
        EmployeDao employeDao = new EmployeDao();
        try { 
           JpaUtil.creerContextePersistance();
           JpaUtil.ouvrirTransaction();  
           Consultation consultationEnCours = consultationDao.findbyId(idConsultation);
           if (consultationEnCours == null) {
               System.out.println("Ne peut pas trouver la consultation.");
           }
           else {
                Client client = consultationEnCours.getClient();
                Employe employe = consultationEnCours.getEmploye();
                Medium medium = consultationEnCours.getMedium();
                consultationEnCours.setCommentaire(commentaire);
                medium.setNbConsultation(medium.getNbConsultation()+1);
                employe.setDisponible(true);
                employeDao.update(employe);
                mediumDao.update(medium);
                consultationDao.update(consultationEnCours);
                JpaUtil.validerTransaction(); 
                System.out.println("C'est la fin pour cette consultation.");
           }
           
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            System.out.println("Ne peut pas finir cette consultation.");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        
    }
     public List<Consultation> afficherHistoriqueClient(Long idClient){
        ClientDao clientDao = new ClientDao();
        List<Consultation> historiqueClient = null;
        try { 
            
           JpaUtil.creerContextePersistance();
           Client client = clientDao.findById(idClient);
           historiqueClient = client.getListeRdv();
           
           if (historiqueClient.size() == 0) {
               System.out.println("Vous n'avez pas pris aucune consultation");
           }
           else {
               System.out.println("Voici votre historique :");
                for (Consultation consult : historiqueClient){
                    System.out.println(consult.toString());
                }
           }
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas afficher.");
        }
        finally {
            JpaUtil.fermerContextePersistance();
            return historiqueClient;
        }
     } 
     
    public List<Consultation> afficherHistoriqueEmploye(Long idEmploye){
        EmployeDao employeDao = new EmployeDao();
        List<Consultation> historiqueEmploye = null;
        try { 
            
           JpaUtil.creerContextePersistance(); 
           Employe employe = employeDao.findById(idEmploye);
           historiqueEmploye = employe.getListeConsultation();
           System.out.println("Voici votre historique :");
           for (Consultation consult : historiqueEmploye){
               System.out.println(consult.toString());
           }
           
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas afficher.");
        }
        finally {
            JpaUtil.fermerContextePersistance();
            return historiqueEmploye;
        }
    }   
     
    public List<String> recupererPredictions(Client client, int amour, int sante, int travail) {
        AstroNetApi astroApi = new AstroNetApi();
        List<String> predictions = null;
        try{
            predictions = astroApi.getPredictions(client.getProfilAstral().getCouleur(), client.getProfilAstral().getAnimalTotem(), amour, sante, travail);
        } catch(Exception ex){
            System.out.println("Bon courage. Vous devrez le faire tout seul =)) ");
            ex.printStackTrace();
        } 
        
        return predictions;
    }
    
    public Map<String, Integer> statistiquesEmployee(Employe employe) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        ConsultationDao consultationDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        
        try {
            JpaUtil.creerContextePersistance();
            List<Client> list_client = consultationDao.findClientsByEmploye(employe);
            System.out.println("Voici votre statistique ");
            for (Client client : list_client) {
                /*System.out.println(client);
                System.out.println(consultationDao.getNbConsultationByClientEmploye(client, employe));*/
                map.put(client.getNom().toUpperCase() + " " + client.getPrenom(), consultationDao.getNbConsultationByClientEmploye(client, employe));
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas afficher votre statistique. ");
        } finally{
            JpaUtil.fermerContextePersistance();
        }

        return map;
    }
     
     public Medium trouverMediumParId(Long idMedium){
         MediumDao mediumDao = new MediumDao();
         Medium medium = null;
         try{   
           JpaUtil.creerContextePersistance();
           medium = mediumDao.findById(idMedium);
           if(medium != null){
               System.out.println("Voici le medium à trouver :");
               System.out.println(medium);
           }
           else{
               System.out.println("Ne peut pas trouvé ce medium");
           }
           }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas trouvé ce medium");
        }
        finally {
            JpaUtil.fermerContextePersistance();
            return medium;
        }
         
     }
     
      public Client trouverClientParId(Long idClient){
         ClientDao clientDao = new ClientDao();
         Client client= null;
         try {
           JpaUtil.creerContextePersistance();
           client = clientDao.findById(idClient);
           if(client != null){
               System.out.println("Voici le client à trouver :");
               System.out.println(client);
           }
           else{
               System.out.println("Ne peut pas trouvé ce client.");
           }
        }
        catch (Exception ex) {
           ex.printStackTrace();
           System.out.println("Ne peut pas trouvé ce client.");
        }
        finally {
             JpaUtil.fermerContextePersistance();
        }
         
         return client;
     }
      
       public Employe trouverEmployeParId(Long idEmploye){
         EmployeDao employeDao = new EmployeDao();
         Employe employe= null;
         try{   
           JpaUtil.creerContextePersistance();
           employe = employeDao.findById(idEmploye);
           if(employe != null){
               System.out.println("Voici l'employe à trouver :");
               System.out.println(employe);
           }
           else{
               System.out.println("Ne peut pas trouvé cet employe");
           }
           }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas trouvé cet employe");
        }
        finally {
            JpaUtil.fermerContextePersistance();
            return employe;
        }
         
     }
       
     public Consultation trouverConsultationParId(Long idConsultation){
         ConsultationDao consultationDao = new ConsultationDao();
         Consultation consultation = null;
         try{   
           JpaUtil.creerContextePersistance();
           consultation = consultationDao.findbyId(idConsultation);
           
           if(consultation != null){
               System.out.println("Voici la consultation à trouver :");
               System.out.println(consultation);
           }
           else{
               System.out.println("Ne peut pas trouvé cette consultation");
           }
           }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas trouvé cette consultation");
        }
        finally {
            JpaUtil.fermerContextePersistance();
            return consultation;
        }
         
     }
     
    public  Map<String, Integer> nbConsultMedium (){
        Map<String, Integer> stats = new HashMap<>(); 
        MediumDao mediumDao = new MediumDao();
        List<Medium> listeMedium = null;
        try{   
           JpaUtil.creerContextePersistance();
           listeMedium = mediumDao.findAll();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ne peut pas trouvé la liste des médiums");
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        for (Medium mediumact : listeMedium){
            stats.put(mediumact.getDenomination(), mediumact.getNbConsultation());
        }
        return stats;
    }
    
    public List<Medium> getTopFiveMedium() {
        MediumDao mediumDao= new MediumDao();
        List<Medium> liste_medium = null;
        List<Medium> top_list = new ArrayList<>();

        try {
            JpaUtil.creerContextePersistance();
            liste_medium = mediumDao.findAll();

            Collections.sort(liste_medium, (Medium m1, Medium m2) -> (m2.getNbConsultation() - m1.getNbConsultation()));
            
            for (int i = 0; i < 5; i++) {
                top_list.add(liste_medium.get(i));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur trouvé");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return top_list;
    }
    
    // ajouter par l'equipe de gab
    public List<Medium> getAllMediums() {
        MediumDao mediumDao= new MediumDao();
        List<Medium> liste_medium = null;
        List<Medium> top_list = new ArrayList<>();

        try {
            JpaUtil.creerContextePersistance();
            liste_medium = mediumDao.findAll();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur trouvé");
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return top_list;
    }
}

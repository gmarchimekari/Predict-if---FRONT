package ihm;


//import com.sun.security.ntlm.Client;
import dao.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import metier.model.Client;
import metier.model.Consultation;
import metier.model.Employe;
import metier.model.Medium;
import metier.service.ServiceManager;
import util.Saisie;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ttvu
 */
public class Main {
    public static void main(String[] args){
        ServiceManager check = new ServiceManager();
        JpaUtil.creerFabriquePersistance();
        
        check.initialiserEmploye();
        check.initialiserMedium();
        
        Boolean stop = false;
        Client client = null;
        Employe employe = null;
        Medium medium = null;
        Consultation consultation = null;
        while (!stop) {
            System.out.println("Veuillez choisir une action :");
            System.out.println("   0. Exit");

            System.out.println("//// Bienvenue à Predict'IF ()");
            System.out.println("   1. Creer un nouveau compte");
            System.out.println("   2. Se connecter");

            System.out.println("/// ESPACE CLIENT ");
            System.out.println("   3. Modifier le profil");
            System.out.println("   4. Voir votre profil Astral");
            System.out.println("   5. Voir l'historique des consultations");
            System.out.println("   6. Demander une consultation");

            System.out.println("//// ESPACE EMPLOYEE ");
            System.out.println("   7. Afficher les 5 meilleurs médiums");
            System.out.println("   8. Voir les statistiques du médiums");
            System.out.println("   9. Voir votre statistiques ");
            System.out.println("   10. Voir la carte");
            System.out.println("   11. Voir l'historique des consultations");

            System.out.println("///// CONSULTATION PAGE (Après avoir demandé une consultation)");
            System.out.println("   12. Démarrer une consultation");
            System.out.println("   13. Aider avec les prédiction");
            System.out.println("   14. Fin de consultation");

            List<Integer> list_option = new ArrayList<>(); 
            for (int i=0; i<=14; ++i) 
                list_option.add(i);

            int option = Saisie.lireInteger("Votre option:", list_option);

            String nom, prenom, adressePostale, numTel, dateNaissance, mail, mp1, mp2, mp, inscrire;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            switch (option) {
                case 1:   
                    System.out.println("///////// INSCRIPTION");
                    nom = Saisie.lireChaine("Nom :");
                    prenom = Saisie.lireChaine("Prénom :");
                    adressePostale = Saisie.lireChaine("Adresse :");
                    numTel = Saisie.lireChaine("Tel :");
                    dateNaissance = Saisie.lireChaine("Date de naissance (DD/MM/AAAA) :");
                    mail = Saisie.lireChaine("Mail :");
                    mp1 = Saisie.lireChaine("Mot de passe :");
                    mp2 = Saisie.lireChaine("Confirmer mot de passe :");

                    inscrire = Saisie.lireChaine("S'inscrire (Y/N) ?");
                    if (inscrire.equals("Y")) {
                        while (!mp1.equals(mp2)) {
                            System.out.println("/!\\ Mot de passe n'est pas cohérent /!\\");
                            mp2 = Saisie.lireChaine("Confirmer mot de passe :");
                        }

                        try {
                            client = new Client(nom, prenom, mail, adressePostale, dateFormat.parse(dateNaissance), numTel);
                            client.setMotDePasse(mp1);
                            Boolean etat = check.inscrireClient(client);

                            if (etat) {
                                System.out.println("Inscription avec succès");
                            } else {
                                System.out.println("Inscription en échec");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    System.out.println("///////// CONNEXION");
                    System.out.println("Vous êtes :");
                    System.out.println("  1. Un client");
                    System.out.println("  2. Un employé");
                    
                    List<Integer> list_connexion_option = new ArrayList<>(); 
                    list_connexion_option.add(1);
                    list_connexion_option.add(2);

                    int connexion_option = Saisie.lireInteger("Votre option:", list_connexion_option);
                    
                    if (connexion_option == 1) {
                        mail = Saisie.lireChaine("Mail :");
                        mp = Saisie.lireChaine("Mot de passe :");

                        client = check.authentifierClient(mail, mp);
                        if (client == null) {
                            System.out.println("Mot de passe incorrect. La connexion a échoué");
                        }
                        else {
                            System.out.println("Connexion réussie pour :");
                            System.out.println(client.getNom().toUpperCase() + " " + client.getPrenom());
                        }
                    }
                    else {
                        mail = Saisie.lireChaine("Mail :");
                        mp = Saisie.lireChaine("Mot de passe :");

                        employe = check.authentifierEmploye(mail, mp);
                        if (employe == null) {
                            System.out.println("Mot de passe incorrect. La connexion a échoué");
                        }
                        else {
                       
                            System.out.println("Connexion réussi pour : ");
                            System.out.println(employe.getNom().toUpperCase() + " " + employe.getPrenom());
                            System.out.println("Il y a une consultation à venir : ");
                            System.out.println(employe.getConsultationenCours());
                        }
                    }
                    
                    

                    break;
                case 3:
                    System.out.println("///////// CONNEXION");
                    mail = Saisie.lireChaine("Mail :");
                    mp = Saisie.lireChaine("Mot de passe :");

                    client = check.authentifierClient(mail, mp);
                    if (client == null) {
                        System.out.println("");
                    }
                    else {
                        System.out.println("Connexion réussie");
                    }
                    System.out.println("///////// Modifier le profil de client");
                    mail = Saisie.lireChaine("New email :");
                    mp = Saisie.lireChaine("New password :");
                    check.modifierProfilClient(client, mail, mp);
                    
                    break;
                
                case 4:
                    
                    System.out.println("///////// VOTRE PROFIL ASTRAL EST :");
                    System.out.println("Animal totem :  " + client.getProfilAstral().getAnimalTotem());
                    System.out.println("Signe du zodiaque :  " + client.getProfilAstral().getSigneZodiaque());
                    System.out.println("Couleur :  " + client.getProfilAstral().getCouleur());
                    System.out.println("Signe astrologique chinois :  " + client.getProfilAstral().getSigneChinois());
                    break;
                    
                case 5:
                    
                    System.out.println("///////// L'HISTORIQUE DES CONSULTATIONS:");

                    List<Consultation> hist = check.afficherHistoriqueClient(client.getId());

                    break;
                case 6:
                    
                    System.out.println("///////// DEMANDER UNE CONSULTATION");
                    String medium_name = Saisie.lireChaine("Le nom de votre médium choisi:");


                    Boolean etat = check.creerConsultation(medium_name, client);
                    if (etat) {
                        System.out.println("Vous avez demandé la consultation. Veuillez lui attendre de vous contacter");
                    } else {
                        System.out.println("Demande échec");
                    }

                    break;
                case 7:
                    System.out.println("/////////AFFICHER LES 5 MEILLEURS MEDIUM");
                    List<Medium> top5 = check.getTopFiveMedium();

                    int rank = 1;
                    for (Medium m : top5) {
                        System.out.println(rank + ". " + m.getDenomination());
                        ++rank;
                    }
                    System.out.println();

                    break;
                case 8:
                    System.out.println("///////// VOIR LES STATISTIQUES DES MEDIUM ");
                    Map<String, Integer> stats_med = check.nbConsultMedium();
                    for (Map.Entry<String, Integer> e : stats_med.entrySet()) {
                        System.out.println(e.getKey() + " " + e.getValue());
                    }
                    break;    

                case 9:
                    System.out.println("/////////  VOIR VOTRE STATISTIQUES");
                    Map<String, Integer> stats_client = check.statistiquesEmployee(employe);
                    for (Map.Entry<String, Integer> e : stats_client.entrySet()) {
                        System.out.println(e.getKey() + " " + e.getValue());
                    }

                    break;

                case 10:
                    System.out.println("///////// VOIR LA CARTE");
                    List<Client> list_client = check.consulterListeClients();
                    
                    for (Client c: list_client) {
                        System.out.println(c.getId() + " | " + c.getNom().toUpperCase() + " " + c.getPrenom() + " | Lat = " + c.getLatitude() + " Long = " + c.getLongitude());
                    }
                    
                    break;

                case 11:
                    System.out.println("///////// L'HISTORIQUE DES CONSULTATIONS : ");
                    check.afficherHistoriqueEmploye(employe.getId());
                    break;

                case 12:
                    System.out.println("///////// DEMARRER UNE CONSULTATION");
                    Integer idcons = Saisie.lireInteger("id consultation");
                    Long id = new Long(idcons);
                    check.demarrerConsultation(id);
                    break;

                case 13:
                    System.out.println("///////// PRENDRE DES PREDICTIONS");
                    Integer niveauAmour = Saisie.lireInteger("Niveau de l'amour :");
                    Integer niveauSante = Saisie.lireInteger("Niveau de la santé :");
                    Integer niveauTravail = Saisie.lireInteger("Niveau du travail :");
                    Integer idclient = Saisie.lireInteger("id client");
                    Long id2 = new Long(idclient);
                    List<String> predictions = check.recupererPredictions(check.trouverClientParId(id2), niveauAmour, niveauSante, niveauTravail);
                    String predictionAmour = predictions.get(0);
                    String predictionSante = predictions.get(1);
                    String predictionTravail = predictions.get(2);

                    System.out.println("-----> Prédictions");
                    System.out.println("Amour : " + predictionAmour);
                    System.out.println("Santé : " + predictionSante);
                    System.out.println("Travail: " + predictionTravail);

                    break;

                case 14:
                    System.out.println("///////// FIN DE CONSULTATION");
                    Integer ida = Saisie.lireInteger("id consultation");
                    Long id3 = new Long(ida);
                    String commentaire = Saisie.lireChaine("Commentaire : ");
                    check.finConsultation(id3, commentaire);
                    break;

                default:
                    stop = true;
                    break;
            }
            
            String ok = Saisie.lireChaine("Tapez quelque chose pour continuer :");
        }
        
        
        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = null;
        try{
            
           dateNaissance = simpleDateFormat.parse("12/07/2003");
        }
        
        catch (ParseException ex) {
            ex.printStackTrace();
            
        }

        Client kh1 = new Client("Doan","Adrien","vhugo@paris.fr","Annecy",dateNaissance,"0101010101");
        kh1.setMotDePasse("dm");
        Client kh2 = new Client("Vu","Thi Tho","myourcenar@gmail.com","Tooloose",dateNaissance,"0101010101");
        Client kh3 = new Client("Zola","Emile","ezola@gmail.com","Lyon",dateNaissance,"0101010101");
  
        
        check.initialiserEmploye();
        check.initialiserMedium();
 
        
        
        if (check.inscrireClient(kh1) == true) {
        System.out.println("Inscription réussie pour " + kh1.getNom().toUpperCase() + " " + kh1.getPrenom()+ " " + kh1.getMail() );
        
        }
        else{
        System.out.println("Inscription échouée pour " + kh1.getNom().toUpperCase() + " " + kh1.getPrenom()+ " " + kh1.getMail() );
        }
        
        if (check.inscrireClient(kh2) == true) {
        System.out.println("Inscription réussie pour " + kh2.getNom().toUpperCase() + " " + kh2.getPrenom()+ " " + kh2.getMail() );
        }
        else{
        System.out.println("Inscription échouée pour " + kh2.getNom().toUpperCase() + " " + kh2.getPrenom()+ " " + kh2.getMail() );
        }
         
         if (check.inscrireClient(kh3) == true) {
        System.out.println("Inscription réussie pour " + kh3.getNom().toUpperCase() + " " + kh3.getPrenom()+ " " + kh3.getMail() );
        }
        else{
        System.out.println("Inscription échouée pour " + kh3.getNom().toUpperCase() + " " + kh3.getPrenom()+ " " + kh3.getMail() );
        }
        check.creerConsultation("irma",kh3);
        check.creerConsultation("irma",kh3);
        check.creerConsultation("irma",kh3);
        Long id = new Long(8);
        check.demarrerConsultation(id);
        check.finConsultation(id,"C'etait chouette !");
        check.creerConsultation("irma",kh3);
        check.creerConsultation("irma",kh3);
        Long id1 = new Long(7);
        check.afficherHistoriqueClient(id1);
        // Client auth = check.authentifierClient("vhugo@paris.fr", "dm");
        
        Employe emp = check.trouverEmployeParId(Long.valueOf(2));
        Map<Client, Integer> res = check.statistiquesEmployee(emp);
        
        for (Map.Entry<Client, Integer> e : res.entrySet()) 
  
            // Printing key-value pairs 
            System.out.println(e.getKey() + " \n"
                               + e.getValue()); 
        
        
        
        // Tests pour l'inscription
        /*System.out.println("===== Inscription =====");
        
        String nom = Saisie.lireChaine("NOM:");
        String prenom = Saisie.lireChaine("Prénom:");
        String mail = Saisie.lireChaine("Mail:");
        String adresse = Saisie.lireChaine("Adresse:");
        String mdp = Saisie.lireChaine("Mot de passe:");
        
        Client new_client = new Client(nom, prenom, mail, adresse,);
        new_client.setMotDePasse(mdp);
        check.inscrireClient(new_client);
        
        List<Client> liste = check.consulterListeClients();
        for (int i=0; i<liste.size(); ++i) {
            System.out.println(liste.get(i).printInfo());
        }
        
        // Test pour authentification
        System.out.println("===== Authentification =====");
        String mail_auth = Saisie.lireChaine("Mail:");
        String mdp_auth = Saisie.lireChaine("Mot de passe:");
        
        Client auth_res = check.authentifierClient(mail_auth, mdp_auth);
        System.out.println(auth_res);
        
        // Test pour recherche de clients
        System.out.println("===== Rechecher de client par ID =====");
        String id = Saisie.lireChaine("Client's ID:");
        Client res = check.rechercherClientParID(Long.valueOf(id));
        System.out.println(res);
*/
    }
}

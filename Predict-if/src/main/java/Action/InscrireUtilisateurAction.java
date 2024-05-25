/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import com.google.maps.model.LatLng;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.model.Client;
import metier.service.ServiceManager;
import util.GeoNetApi;

/**
 *
 * @author dhabib
 */
public class InscrireUtilisateurAction extends Action {

    public InscrireUtilisateurAction(ServiceManager service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) throws ParseException{
        
        String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        
        String d = (String) request.getParameter("dateNaissance");
        System.out.println(d);
        String dateNaissanceString = "" + d.charAt(8) + d.charAt(9) + "/" + d.charAt(5) + d.charAt(6) + "/" + d.charAt(0) + d.charAt(1) + d.charAt(2) + d.charAt(3);
        System.out.println(dateNaissanceString);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(dateNaissanceString);
        
        String rue = (String) request.getParameter("rue");
        String ville = (String) request.getParameter("ville");
        String adressePostale = rue + " " + ville;
        
        String numeroTel = (String) request.getParameter("numeroTel");
        String mail = (String) request.getParameter("mail");
        String motDePasse = (String) request.getParameter("motDePasse");
        
        Client client = new Client(nom, prenom, mail, adressePostale, dateNaissance, numeroTel);
        client.setMotDePasse(motDePasse);
        
        request.setAttribute("action", "inscription");
        
        LatLng coords = GeoNetApi.getLatLng(client.getAdressePostale());
        if (coords != null) {
            Boolean result = service.inscrireClient(client);
            if (result){
                request.setAttribute("Utilisateur", client);
                request.setAttribute("coords", "pas null");
                System.out.println("inscription ok");
            } else {
                request.setAttribute("Utilisateur", null);
                System.out.println("inscription pas ok");
            }
        } else {
            request.setAttribute("Utilisateur", client);
            request.setAttribute("coords", "null");
            System.out.println("inscription pas ok");
        }
        
        
        
        
        
        
    }
}

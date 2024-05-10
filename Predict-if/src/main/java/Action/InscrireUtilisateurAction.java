/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import metier.model.Client;
import metier.service.ServiceManager;

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
        
        String dateNaissanceString = (String) request.getParameter("dateNaissance");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateNaissance = dateFormat.parse(dateNaissanceString);
        
        String rue = (String) request.getParameter("rue");
        String ville = (String) request.getParameter("ville");
        String adressePostale = rue + ville;
        
        String numeroTel = (String) request.getParameter("numeroTel");
        String mail = (String) request.getParameter("mail");
        String motDePasse = (String) request.getParameter("motDePasse");
        
        Client client = new Client(nom, prenom, mail, adressePostale, dateNaissance, numeroTel);
        client.setMotDePasse(motDePasse);
        
        Boolean result = service.inscrireClient(client);
        
        if (result){
            request.setAttribute("client", client);
            System.out.println("inscription ok");
        } else {
            request.setAttribute("client", null);
            System.out.println("inscription pas ok");
        }
        
        
    }
}

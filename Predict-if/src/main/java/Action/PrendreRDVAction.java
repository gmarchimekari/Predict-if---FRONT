/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Client;
import metier.model.Medium;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
public class PrendreRDVAction extends Action {

    public PrendreRDVAction(ServiceManager service) {
        super(service);
    }
    
    @Override
    public void execute(HttpServletRequest request) throws ParseException {  
        HttpSession session = request.getSession(true);
        
        Long clientId = (Long) session.getAttribute("UtilisateurId");
        String mediumDenomination = (String) request.getParameter("denominationMedium");
        
        Client client = service.trouverClientParId(clientId);
        Boolean consultationOk = service.creerConsultation(mediumDenomination, client);

        request.setAttribute("consultationOk", consultationOk);

    }
    
}

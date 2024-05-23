/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Client;
import metier.model.Consultation;
import metier.model.Employe;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
public class GenererPredictionAction extends Action {

    public GenererPredictionAction(ServiceManager service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        
        Long employeId = (Long) session.getAttribute("UtilisateurId");
        
        if(employeId != null) {
            Employe employe = service.trouverEmployeParId(employeId);
            request.setAttribute("Employe", employe);
            Client client = employe.getConsultationenCours().getClient();
            
            int amour = Integer.parseInt(request.getParameter("amour"));
            int sante = Integer.parseInt(request.getParameter("sante"));
            int travail = Integer.parseInt(request.getParameter("travail"));
            
            List<String> predictions = service.recupererPredictions(client, amour, sante, travail);
            request.setAttribute("predictions", predictions);
        } else {
            request.setAttribute("Employe", null);
        }

    }
    
}
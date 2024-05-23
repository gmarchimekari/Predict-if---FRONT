/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Consultation;
import metier.model.Employe;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
public class AfficherMenuEmployeAction extends Action {

    public AfficherMenuEmployeAction(ServiceManager service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        
        Long employeId = (Long) session.getAttribute("UtilisateurId");
        
        if(employeId != null) {
            Employe employe = service.trouverEmployeParId(employeId);
            request.setAttribute("Employe", employe);
            Consultation consultationAvenir = employe.getConsultationenCours();
            if(consultationAvenir.getCommentaire() != null) {
                consultationAvenir = null;
            }
            
            if(consultationAvenir != null) {
                request.setAttribute("consultationOk", true);
                request.setAttribute("Consultation", consultationAvenir);
                request.setAttribute("Client", consultationAvenir.getClient());
                request.setAttribute("Medium", consultationAvenir.getMedium());
                request.setAttribute("listeConsultationsClient", consultationAvenir.getClient().getListeRdv());
            } else {
                request.setAttribute("consultationOk", false);
            }
        } else {
            request.setAttribute("Employe", null);
            request.setAttribute("consultationOk", false);
        }
        
        
    }
    
}

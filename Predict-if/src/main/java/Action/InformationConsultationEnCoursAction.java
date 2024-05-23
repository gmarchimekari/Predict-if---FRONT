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
public class InformationConsultationEnCoursAction extends Action {

    public InformationConsultationEnCoursAction(ServiceManager service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        
        Long employeId = (Long) session.getAttribute("UtilisateurId");
        
        if(employeId != null) {
            Employe employe = service.trouverEmployeParId(employeId);
            request.setAttribute("Employe", employe);
            
        } else {
            request.setAttribute("Employe", null);
        }
    }
    
}

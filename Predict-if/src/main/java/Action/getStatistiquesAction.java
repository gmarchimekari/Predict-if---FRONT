/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import metier.model.Employe;
import metier.model.Medium;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
public class getStatistiquesAction extends Action {

    public getStatistiquesAction(ServiceManager service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        
        Long employeId = (Long) session.getAttribute("UtilisateurId");
        
        if(employeId != null) {
            Employe employe = service.trouverEmployeParId(employeId);
            request.setAttribute("Employe", employe);
            Map<String, Integer> repartitionClients = service.statistiquesEmployee(employe);
            request.setAttribute("repartitionClients", repartitionClients);
        } else {
            request.setAttribute("repartitionClients", null);
        }
        
        Map<String, Integer> nbConsultationsParMedium = service.nbConsultMedium();
        request.setAttribute("nbConsultationsParMedium", nbConsultationsParMedium);
        
        List<Medium> topFiveMedium = service.getTopFiveMedium();
        request.setAttribute("topFiveMedium", topFiveMedium);
    }
    
}

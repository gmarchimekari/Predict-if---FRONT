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
import metier.model.Medium;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
public class AfficherMenuClientAction extends Action {

    public AfficherMenuClientAction(ServiceManager service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) throws ParseException {
        HttpSession session = request.getSession(true);
        
        Long clientId = (Long) session.getAttribute("UtilisateurId");
        List<Medium> lesMediums = service.getAllMediums();
        System.out.println("sizeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee " +lesMediums.size());
        request.setAttribute("Mediums", lesMediums);
        
        if(clientId != null) {
            Client client = service.trouverClientParId(clientId);
            request.setAttribute("Client", client);
        } else {
            request.setAttribute("Client", null);
        }
        
    }
    
}

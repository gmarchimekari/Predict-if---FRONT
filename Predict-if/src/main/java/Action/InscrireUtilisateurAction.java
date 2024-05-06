/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import javax.servlet.http.HttpServletRequest;
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
    public void execute(HttpServletRequest request){
        
        
        // String login = (String) request.getParameter("login");
        
        // request.setAttribute("Utilisateur", lovelace);
        
    }
}

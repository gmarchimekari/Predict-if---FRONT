/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import javax.servlet.http.HttpServletRequest;
import metier.model.Client;
import metier.model.Employe;
import metier.service.ServiceManager;

/**
 *
 * @author gmarchimek
 */
public class AuthentifierUtilisateurAction extends Action {

    public AuthentifierUtilisateurAction(ServiceManager service) {
        super(service);
    }
    
    
    @Override
    public void execute(HttpServletRequest request){
        
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        
        Client client = service.authentifierClient(login, password);
        Employe employe = service.authentifierEmploye(login, password);
        
        if(client != null) {
            request.setAttribute("Utilisateur", client);
        } else if(employe != null) {
            request.setAttribute("Utilisateur", employe);
        } else {
            request.setAttribute("Utilisateur", null);
        }
    }
    
}

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
 * @author gmarchimek
 */
public class AuthentifierUtilisateurAction extends Action {

    public AuthentifierUtilisateurAction(ServiceManager service) {
        super(service);
    }
    
    
    @Override
    public void execute(HttpServletRequest request){
        
        //ServiceManager service = new ServiceManager();
        TestUtilisateur lovelace = new TestUtilisateur("1024", "Lovelace", "Ada", "ada.lovelace@insa-lyon.fr");
        String login = (String) request.getParameter("login");
        String password = (String) request.getParameter("password");
        
        if(!login.equals(lovelace.getMail())) {
            lovelace = null;
        }
        System.out.println(lovelace);
        request.setAttribute("Utilisateur", lovelace);
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
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
        
    }
    
}

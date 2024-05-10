

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
public abstract class Action {
    
    ServiceManager service;
    public Action(ServiceManager service) {
        this.service = service;
    }
    
    public  abstract void execute(HttpServletRequest request) throws ParseException;
    
}

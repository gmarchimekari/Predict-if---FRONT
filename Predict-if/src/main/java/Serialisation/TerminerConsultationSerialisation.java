/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dhabib
 */
public class TerminerConsultationSerialisation extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        
        // car le service finConsultation renvoie void !!
        container.addProperty("consultationTerminee", Boolean.TRUE);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
    }
    
}

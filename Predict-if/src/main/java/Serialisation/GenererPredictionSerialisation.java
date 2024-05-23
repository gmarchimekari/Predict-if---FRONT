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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.model.Astrologue;
import metier.model.Cartomancien;
import metier.model.Employe;
import metier.model.Medium;
import metier.model.Spirite;

/**
 *
 * @author dhabib
 */
public class GenererPredictionSerialisation extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        List<String> predictions = (List<String>) request.getAttribute("predictions");
       
        container.addProperty("amour", predictions.get(0));
        container.addProperty("sante", predictions.get(1));
        container.addProperty("travail", predictions.get(2));
        
        gsonBuilder.toJson(container);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
    }
    
}

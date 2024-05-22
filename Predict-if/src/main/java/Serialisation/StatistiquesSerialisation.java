/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.model.Consultation;
import metier.model.Employe;
import metier.model.Medium;

/**
 *
 * @author dhabib
 */
public class StatistiquesSerialisation extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        
        Employe employe = (Employe) request.getAttribute("Employe");
        if(employe == null) {
            container.addProperty("repartitionClients", "null");
            container.addProperty("clients", "null");
        } else {
            Map<String, Integer> repartitionClients = (Map<String, Integer>) request.getAttribute("repartitionClients");
            JsonArray jsonListeRepartitionClients = new JsonArray();
            
            for (Map.Entry<String, Integer> entry : repartitionClients.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                
                JsonObject jsonClient = new JsonObject();

                jsonClient.addProperty("nomClient", key);
                jsonClient.addProperty("repartitionClient", value);

                
                gsonBuilder.toJson(jsonClient);
                jsonListeRepartitionClients.add(jsonClient);
            }
            
            gsonBuilder.toJson(jsonListeRepartitionClients);
            container.add("repartitionClients", jsonListeRepartitionClients);
            
            List<Consultation> lesConsultations = (List<Consultation>) request.getAttribute("consultations");
            JsonArray jsonListeConsultationsClients = new JsonArray();

            for (Consultation c : lesConsultations) {
                JsonObject jsonClient = new JsonObject();


                jsonClient.addProperty("nom", c.getClient().getNom());
                jsonClient.addProperty("prenom", c.getClient().getPrenom());
                jsonClient.addProperty("longitude", c.getClient().getLongitude());
                jsonClient.addProperty("latitude", c.getClient().getLatitude());
                jsonClient.addProperty("longitude", c.getClient().getLongitude());

                gsonBuilder.toJson(jsonClient);
                jsonListeConsultationsClients.add(jsonClient);
            }
            gsonBuilder.toJson(jsonListeConsultationsClients);
            container.add("clients", jsonListeConsultationsClients);
        }
        
        Map<String, Integer> nbConsultations = (Map<String, Integer>) request.getAttribute("nbConsultationsParMedium");
        JsonArray jsonListeNbConsultations = new JsonArray();

        for (Map.Entry<String, Integer> entry : nbConsultations.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            JsonObject jsonMedium = new JsonObject();

            jsonMedium.addProperty("denomination", key);
            jsonMedium.addProperty("nbConsultations", value);

            gsonBuilder.toJson(jsonMedium);
            jsonListeNbConsultations.add(jsonMedium);
        }
        gsonBuilder.toJson(jsonListeNbConsultations);
        container.add("nbConsultations", jsonListeNbConsultations);
        
        List<Medium> lesMediums = (List<Medium>) (Medium) request.getAttribute("topFiveMedium");
        for (Medium m : lesMediums) {
            JsonObject jsonMedium = new JsonObject();

            jsonMedium.addProperty("denomination", m.getDenomination());

            gsonBuilder.toJson(jsonMedium);
            jsonListeNbConsultations.add(jsonMedium);
        }
        gsonBuilder.toJson(jsonListeNbConsultations);
        container.add("nbConsultations", jsonListeNbConsultations);
        
        
        
        gsonBuilder.toJson(container);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
    }
    
}

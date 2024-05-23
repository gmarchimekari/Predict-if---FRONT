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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.model.Astrologue;
import metier.model.Cartomancien;
import metier.model.Client;
import metier.model.Medium;
import metier.model.ProfilAstral;
import metier.model.Spirite;

/**
 *
 * @author dhabib
 */
public class MenuClientSerialisation extends Serialisation {

    public MenuClientSerialisation() {
    }

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        Client client = (Client) request.getAttribute("Client");
        if(client != null) {
            JsonObject jsonUser = new JsonObject(); 
            jsonUser.addProperty("id", client.getId());
            jsonUser.addProperty("nom", client.getNom());
            jsonUser.addProperty("prenom", client.getPrenom());
            jsonUser.addProperty("mail", client.getMail());

            // Créer un objet SimpleDateFormat avec le format désiré
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Convertir la date en chaîne de caractères
            String dateString = dateFormat.format(client.getDateNaissance());
            jsonUser.addProperty("dateNaissance", dateString);
            
            JsonObject jsonAstral = new JsonObject();
            ProfilAstral profilAstral = client.getProfilAstral();
            jsonAstral.addProperty("couleur", profilAstral.getCouleur());
            jsonAstral.addProperty("signeZodiaque", profilAstral.getSigneZodiaque());
            jsonAstral.addProperty("signeChinois", profilAstral.getSigneChinois());
            jsonAstral.addProperty("animalTotem", profilAstral.getAnimalTotem());
            gsonBuilder.toJson(jsonAstral);
            
            jsonUser.add("profilAstral", jsonAstral);
            
            gsonBuilder.toJson(jsonUser);
            container.add("client", jsonUser);
        } else {
            container.add("client", null);
        } 
        
        List<Medium> mediums = (List<Medium>) request.getAttribute("Mediums");
        JsonArray jsonListeMediums = new JsonArray();

        for (Medium medium : mediums) {
            System.out.println(medium);
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("id", medium.getId());
            jsonMedium.addProperty("denomination", medium.getDenomination());
            jsonMedium.addProperty("genre", medium.getGenre());
            jsonMedium.addProperty("nbConsultations", medium.getNbConsultation());
            if(medium instanceof Astrologue) {
                jsonMedium.addProperty("type", "Astrologue");
                Astrologue astrologue = (Astrologue) medium;
                jsonMedium.addProperty("formation", astrologue.getFormation());
                jsonMedium.addProperty("promotion", astrologue.getPromotion());
            } else if(medium instanceof Cartomancien) {
                jsonMedium.addProperty("type", "Cartomancien");
            } else if(medium instanceof Spirite) {
                jsonMedium.addProperty("type", "Spirite");
                Spirite spirite = (Spirite) medium;
                jsonMedium.addProperty("support", spirite.getSupport());
            }
            jsonMedium.addProperty("presentation", medium.getPresentation());
            gsonBuilder.toJson(jsonMedium);
            jsonListeMediums.add(jsonMedium);
        }
        
        gsonBuilder.toJson(jsonListeMediums);
        container.add("mediums", jsonListeMediums);
        
        Boolean consultationaVenir = (Boolean) request.getAttribute("consultationaVenir");
        container.addProperty("consultationaVenir", consultationaVenir);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
        
        System.out.println(container);
    }
    
    
    
}

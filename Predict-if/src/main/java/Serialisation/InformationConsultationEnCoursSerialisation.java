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
import metier.model.Astrologue;
import metier.model.Cartomancien;
import metier.model.Employe;
import metier.model.Medium;
import metier.model.Spirite;

/**
 *
 * @author dhabib
 */
public class InformationConsultationEnCoursSerialisation extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        
        Employe employe = (Employe) request.getAttribute("Employe");
        Medium mediumaIncarner = employe.getConsultationenCours().getMedium();
        JsonObject jsonMedium = new JsonObject();
        jsonMedium.addProperty("denomination", mediumaIncarner.getDenomination());
        jsonMedium.addProperty("genre", mediumaIncarner.getGenre());
        if(mediumaIncarner instanceof Spirite){
            Spirite spiriteaIncarner = (Spirite) mediumaIncarner;
            jsonMedium.addProperty("support", spiriteaIncarner.getSupport());
        }
        if(mediumaIncarner instanceof Cartomancien){
            jsonMedium.addProperty("support", "cartes");
        }
        if(mediumaIncarner instanceof Astrologue){
            jsonMedium.addProperty("support", "boule de cristal");
        }
        gsonBuilder.toJson(jsonMedium);
        container.add("medium",jsonMedium);

        JsonObject jsonClient = new JsonObject();
        String nomPrenom = "" + employe.getConsultationenCours().getClient().getNom().toUpperCase() + " " + employe.getConsultationenCours().getClient().getPrenom();
        jsonClient.addProperty("nomPrenom", nomPrenom);

        
        JsonObject jsonProfilAstral = new JsonObject();
        jsonProfilAstral.addProperty("signeZodiaque", employe.getConsultationenCours().getClient().getProfilAstral().getSigneZodiaque());
        jsonProfilAstral.addProperty("signeChinois", employe.getConsultationenCours().getClient().getProfilAstral().getSigneChinois());
        jsonProfilAstral.addProperty("animalTotem", employe.getConsultationenCours().getClient().getProfilAstral().getAnimalTotem());
        jsonProfilAstral.addProperty("couleur", employe.getConsultationenCours().getClient().getProfilAstral().getCouleur());
        gsonBuilder.toJson(jsonProfilAstral);
        
        jsonClient.add("profilAstral", jsonProfilAstral);
        
        gsonBuilder.toJson(jsonClient);
        container.add("client",jsonClient);

        gsonBuilder.toJson(container);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
    }
    
}

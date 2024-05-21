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
import metier.model.Consultation;
import metier.model.Employe;
import metier.model.Medium;
import metier.model.ProfilAstral;
import metier.model.Spirite;

/**
 *
 * @author dhabib
 */
public class MenuEmployeSerialisation extends Serialisation {

    @Override
    public void serialise(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Employe employe = (Employe) request.getAttribute("Employe");
        Boolean consultationOk = (Boolean) request.getAttribute("consultationOk");
        Consultation consultation = (Consultation) request.getAttribute("Consultation"); 
        
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        
        
        if(employe != null) {
            JsonObject jsonEmploye = new JsonObject();
            jsonEmploye.addProperty("nom", employe.getNom());
            jsonEmploye.addProperty("prenom", employe.getPrenom());
            gsonBuilder.toJson(jsonEmploye);
            container.add("employe", jsonEmploye);
            container.addProperty("consultationEnCours", consultationOk);
            if(consultationOk) {
                if(consultation.getClient() != null) {
                    Client client = consultation.getClient();
                    JsonObject jsonClient = new JsonObject(); 
                    jsonClient.addProperty("id", client.getId());
                    jsonClient.addProperty("nom", client.getNom());
                    jsonClient.addProperty("prenom", client.getPrenom());

                    JsonObject jsonAstral = new JsonObject();
                    ProfilAstral profilAstral = client.getProfilAstral();
                    jsonAstral.addProperty("couleur", profilAstral.getCouleur());
                    jsonAstral.addProperty("signeZodiaque", profilAstral.getSigneZodiaque());
                    jsonAstral.addProperty("signeChinois", profilAstral.getSigneChinois());
                    jsonAstral.addProperty("animalTotem", profilAstral.getAnimalTotem());
                    gsonBuilder.toJson(jsonAstral);

                    jsonClient.add("profilAstral", jsonAstral);
                    gsonBuilder.toJson(jsonClient);
                    container.add("client", jsonClient);
                    
                    Medium medium = consultation.getMedium();
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
                    
                    container.add("medium", jsonMedium);
                    
                    List<Consultation> lesConsultations = (List<Consultation>) request.getAttribute("listeConsultationsClient");
                    JsonArray jsonListeConsultations = new JsonArray();

                    for (Consultation c : lesConsultations) {
                        JsonObject jsonConsultation = new JsonObject();
                        
                        jsonConsultation.addProperty("denomination", c.getMedium().getDenomination());
                        jsonConsultation.addProperty("commentaire", c.getCommentaire());
                        
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String dateString = dateFormat.format(c.getDateConsultation());
                        jsonConsultation.addProperty("date", dateString);
                        
                        gsonBuilder.toJson(jsonConsultation);
                        jsonListeConsultations.add(jsonConsultation);
                    }
                    gsonBuilder.toJson(jsonListeConsultations);
                    container.add("listeConsultations", jsonListeConsultations);
                    
                } else {
                    container.addProperty("client", "null");                
                }
                  
            }
        } else {
            container.addProperty("employe", "null");
        }
        
        gsonBuilder.toJson(container);
        
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
        
        System.out.println(container);
    }
    
}

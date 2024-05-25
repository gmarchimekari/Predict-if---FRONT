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
import metier.model.Client;
import metier.model.Employe;
import metier.model.ProfilAstral;

/**
 *
 * @author gmarchimek
 */
public class ProfilUtilisateurSerialisation extends Serialisation{
    
    @Override
    public void serialise (HttpServletRequest request, HttpServletResponse response) throws IOException{
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        if(request.getAttribute("Utilisateur") instanceof Client) {
            String action = (String) request.getAttribute("action");
            String coords = "pas null";
            if(action.equals("inscription")) {
                coords = (String) request.getAttribute("coords");
                if(coords.equals("null")) {
                    container.addProperty("coords", "non valide");
                } else if(coords.equals("pas null")) {
                    container.addProperty("coords", "valide");
                }
            }
            
            Client client = (Client) request.getAttribute("Utilisateur");
            JsonObject jsonUser = new JsonObject(); 
            jsonUser.addProperty("id", client.getId());
            jsonUser.addProperty("nom", client.getNom());
            jsonUser.addProperty("prenom", client.getPrenom());
            jsonUser.addProperty("mail", client.getMail());
            jsonUser.addProperty("motDePasse", client.getMotDePasse());
            jsonUser.addProperty("addressePostale", client.getAdressePostale());
            jsonUser.addProperty("dateNaissance", client.getDateNaissance().toString());
            jsonUser.addProperty("numTel", client.getNumTel());
            jsonUser.addProperty("latitude", client.getLatitude());
            jsonUser.addProperty("longitude", client.getLongitude());
            if(action.equals("inscription") && !coords.equals("null")) {
                JsonObject jsonAstral = new JsonObject();
                ProfilAstral profilAstral = client.getProfilAstral();
                jsonAstral.addProperty("couleur", profilAstral.getCouleur());
                System.out.println("okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                jsonAstral.addProperty("signeZodiaque", profilAstral.getSigneZodiaque());
                jsonAstral.addProperty("signeChinois", profilAstral.getSigneChinois());
                jsonAstral.addProperty("animalTotem", profilAstral.getAnimalTotem());
                gsonBuilder.toJson(jsonAstral);

                jsonUser.add("profilAstral", jsonAstral);
            }
            
            gsonBuilder.toJson(jsonUser);
            if(coords.equals("null")) {
                container.addProperty("operationOk", false);
            } else {
                container.addProperty("operationOk", true);
            }
            
            container.addProperty("typeUtilisateur", "client");
            container.add("utilisateur", jsonUser);
        } else if(request.getAttribute("Utilisateur") instanceof Employe) {
            Employe employe = (Employe) request.getAttribute("Utilisateur");
            JsonObject jsonUser = new JsonObject(); 
            jsonUser.addProperty("id", employe.getId());
            jsonUser.addProperty("nom", employe.getNom());
            jsonUser.addProperty("prenom", employe.getPrenom());
            jsonUser.addProperty("mail", employe.getMail());
            jsonUser.addProperty("motDePasse", employe.getMotDePasse());
            jsonUser.addProperty("genre", employe.getGenre());
            jsonUser.addProperty("disponible", employe.getDisponible());
            jsonUser.addProperty("numTel", employe.getNumTel());
            
            gsonBuilder.toJson(jsonUser);
            container.addProperty("operationOk", true);
            container.addProperty("typeUtilisateur", "employe");
            container.add("utilisateur", jsonUser);
        } else if(request.getAttribute("Utilisateur") == null) {
            container.addProperty("operationOk", false); 
            container.add("utilisateur", null);
            container.add("typeUtilisateur", null);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
        
        System.out.println(container);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialisation;

import Action.TestUtilisateur;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gmarchimek
 */
public class ProfilUtilisateurSerialisation extends Serialisation{
    
    @Override
    public void serialise (HttpServletRequest request, HttpServletResponse response) throws IOException{
        
        TestUtilisateur lovelace = (TestUtilisateur) request.getAttribute("Utilisateur");
        JsonObject jsonAda = new JsonObject();  
        JsonObject container = new JsonObject();
        Gson gsonBuilder = new GsonBuilder().create();
        System.out.println(lovelace);
        if(lovelace == null) {
            container.addProperty("connexion", false); 
            container.add("utilisateur", null);
        } else {
            jsonAda.addProperty("id", lovelace.getId());
            jsonAda.addProperty("nom", lovelace.getNom());
            jsonAda.addProperty("prenom", lovelace.getPrenom());
            jsonAda.addProperty("mail", lovelace.getMail());
            gsonBuilder.toJson(jsonAda);
            container.addProperty("connexion", true); 
            container.add("utilisateur", jsonAda);
        }
        
        
        

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(gsonBuilder.toJson(container));
        out.close();
        
        System.out.println(container);
        
    }
}

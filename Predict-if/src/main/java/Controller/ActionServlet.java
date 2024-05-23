package Controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Action.AfficherMenuClientAction;
import Action.AfficherMenuEmployeAction;
import Action.AuthentifierUtilisateurAction;
import Action.CommencerConsultationAction;
import Action.DeconnexionAction;
import Action.InscrireUtilisateurAction;
import Action.PrendreRDVAction;
import Action.TerminerConsultationAction;
import Serialisation.ConsultationSerialisation;
import Serialisation.MenuClientSerialisation;
import Serialisation.MenuEmployeSerialisation;
import Serialisation.ProfilUtilisateurSerialisation;
import dao.JpaUtil;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.service.ServiceManager;

/**
 *
 * @author dhabib
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.creerFabriquePersistance();
    }

    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
        JpaUtil.fermerFabriquePersistance();
    }
    
    
    
    
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        
        ServiceManager service = new ServiceManager();
        // ServiceManager service = New ServiceManager();
        String todo = request.getParameter("todo");

        System.out.println("[TEST] Appel de l’ActionServlet");
        System.out.println("Parametre todo est " + todo);
        switch(todo) {
            case "connecter":
                System.out.println("connecterrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                new AuthentifierUtilisateurAction(service).execute(request);
                new ProfilUtilisateurSerialisation().serialise(request, response);
                break;
                
            case "inscrire":
                System.out.println("inscriptionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                new InscrireUtilisateurAction(service).execute(request);
                new ProfilUtilisateurSerialisation().serialise(request, response);
                break;
            
            case "estConnecte":
                System.out.println("estConnecteeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
                new ProfilUtilisateurSerialisation().serialise(request, response);
                break;
                
            case "menuClient":
                System.out.println("menu clientttttttttttttttttttttttttttttttttttttttttttt");
                new AfficherMenuClientAction(service).execute(request);
                new MenuClientSerialisation().serialise(request, response);
                break;
                
            case "prendreRDV":
                System.out.println("prendre rdvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
                new PrendreRDVAction(service).execute(request);
                new ConsultationSerialisation().serialise(request, response);
                break;
                
            case "getInformationsConsultation":
                System.out.println("get Informations Consultationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                new AfficherMenuEmployeAction(service).execute(request);
                new MenuEmployeSerialisation().serialise(request, response);
                break;
                
            case "seDeconnecter":
                System.out.println("Deconnexionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                new DeconnexionAction(service).execute(request);
                //new MenuEmployeSerialisation().serialise(request, response);
                break;
                
            case "getStatistiques":
                System.out.println("statistiqueeeeeeeeeeeeeeeeeeeees");
                new DeconnexionAction(service).execute(request);
                //new MenuEmployeSerialisation().serialise(request, response);
                break;
                
            case "commencerConsultation":
                System.out.println("commencer consultationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                new CommencerConsultationAction(service).execute(request);
                //new MenuEmployeSerialisation().serialise(request, response);
                break;
                
            case "terminerConsultation":
                System.out.println("terminer consultationnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                new TerminerConsultationAction(service).execute(request);
                //new MenuEmployeSerialisation().serialise(request, response);
                break;
                
            default:
                System.out.println("je suis la hahahha");
                break; 
        } 

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

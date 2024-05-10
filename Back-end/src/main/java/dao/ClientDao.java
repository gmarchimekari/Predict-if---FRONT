package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.model.Client;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ttvu
 */
public class ClientDao {
    public void create(Client client){
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    public Client findbyMail(String mail){
        String s = "select c from Client c where c.mail= :email";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class).setParameter("email", mail);
        return (Client) query.getSingleResult();
    }
    public Client findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    public List<Client> findAll() {
        String s = "select c from Client c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Client.class);
        return query.getResultList();
    } 
    public Client update(Client client){
        return JpaUtil.obtenirContextePersistance().merge(client);
    }
}

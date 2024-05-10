/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.model.Client;
import metier.model.Medium;

/**
 *
 * @author ttvu
 */
public class MediumDao {
    public void create(Medium medium){
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    public Medium findbyDenomination(String denomination){
        String s = "select c from Medium c where c.denomination = :denomination";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class).setParameter("denomination", denomination);
        return (Medium) query.getSingleResult();
        
    }
    
    public Medium findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> findAll() {
        String s = "select c from Medium c order by c.denomination asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Medium.class);
        return query.getResultList();
    } 
    
    public Medium update(Medium medium){
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }
}

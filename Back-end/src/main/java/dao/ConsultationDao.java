/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.model.Client;
import metier.model.Consultation;
import metier.model.Employe;



/**
 *
 * @author ttvu
 */
public class ConsultationDao {
    public void create(Consultation consultation){
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public Consultation findbyId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    public Consultation update(Consultation consultation){
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    public List<Client> findClientsByEmploye(Employe emp) {
        String s = "select c.client from Consultation c where c.employe = :emp";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Consultation.class).setParameter("emp", emp);
        return query.getResultList();
    }
    
    public Integer getNbConsultationByClientEmploye(Client cl, Employe emp) {
        String s = "select count(c) as total from Consultation c where c.client = :cl and c.employe = :emp";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Consultation.class).setParameter("cl", cl).setParameter("emp", emp);
        
        Long res = (Long) query.getSingleResult();
        return res.intValue();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import metier.model.Consultation;
import metier.model.Employe;

/**
 *
 * @author ttvu
 */
public class EmployeDao {
    public void create(Employe employe){
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    public Employe findbyMail(String mail){
        String s = "select c from Employe c where c.mail= :email";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class).setParameter("email", mail);
        return (Employe) query.getSingleResult();
    }
    public Employe findById(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Employe.class, id);
    }
    public List<Employe> findAll() {
        String s = "select c from Employe c order by c.nom asc";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Employe.class);
        return query.getResultList();
    } 
    public List<Employe> findbyDispoGenre(String genre) {
        String s = "select c from Employe c where c.genre = :genre and c.disponible = 'true'";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s,Employe.class).setParameter("genre", genre);;
        return query.getResultList();
    } 
    public Employe update(Employe employe){
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }
}

package com.imobiliaria.repository;

import javax.persistence.EntityManager;
import java.util.List;

public class DAOGenerico<T> {
    private final Class<T> classe;
    protected EntityManager em;

    public DAOGenerico(Class<T> classe, EntityManager em) {
        this.classe = classe;
        this.em = em;
    }

    public void save(T entity) {
        em.getTransaction().begin();
        if (em.contains(entity) || (entity.getId() != null && em.find(classe, entity.getId()) != null)) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        em.getTransaction().commit();
    }

    public T findById(Integer id) {
        return em.find(classe, id);
    }

    public List<T> findAll() {
        return em.createQuery("FROM " + classe.getName(), classe).getResultList();
    }

    public void deleteById(Integer id) {
        em.getTransaction().begin();
        T entity = em.find(classe, id);
        if (entity != null) {
            em.remove(entity);
        }
        em.getTransaction().commit();
    }
}

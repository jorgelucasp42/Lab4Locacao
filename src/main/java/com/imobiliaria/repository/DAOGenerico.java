package com.imobiliaria.repository;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class DAOGenerico<T> {

    private final EntityManager manager;

    public DAOGenerico(EntityManager manager) {
        this.manager = manager;
    }

    public T buscaPorId(Class<T> classe, Serializable id) {
        return manager.find(classe, id);
    }

    public T salvaOuAtualiza(T t) {
        return manager.merge(t);
    }

    public void remove(T t) {
        T entity = manager.merge(t);
        manager.remove(entity);
    }
}

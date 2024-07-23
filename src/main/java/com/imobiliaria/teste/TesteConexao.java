package com.imobiliaria.teste;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConexao {

    public static void main(String[] args) {
        EntityManagerFactory factory = null;
        EntityManager manager = null;

        try {
            factory = Persistence.createEntityManagerFactory("lab_jpa");
            manager = factory.createEntityManager();
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (manager != null) {
                manager.close();
            }
            if (factory != null) {
                factory.close();
            }
        }
    }
}

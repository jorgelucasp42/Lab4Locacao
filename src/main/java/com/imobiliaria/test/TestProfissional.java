package com.imobiliaria.test;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.repository.ProfissionalRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;


public class TestProfissional {

    public static void main(String[] args) {
        EntityManager manager = JPAUtil.getEntityManager();
        ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);

        // Criar um novo profissional
        Profissional profissional = new Profissional();
        profissional.setNome("Ana Martins");
        profissional.setProfissao("Eletricista");
        profissional.setTelefone1("999123456");
        profissional.setValorHora(50.00);
        profissional.setObs("Profissional com 5 anos de experiência.");

        manager.getTransaction().begin();
        Profissional savedProfissional = profissionalRepository.salvaOuAtualiza(profissional);
        manager.getTransaction().commit();
        System.out.println("Profissional salvo: " + savedProfissional);

        // Buscar profissional por ID
        Profissional foundProfissional = profissionalRepository.buscaPor(savedProfissional.getId());
        System.out.println("Profissional encontrado: " + foundProfissional);

        // Atualizar profissional
        savedProfissional.setTelefone1("888654321");
        manager.getTransaction().begin();
        Profissional updatedProfissional = profissionalRepository.salvaOuAtualiza(savedProfissional);
        manager.getTransaction().commit();
        System.out.println("Profissional atualizado: " + updatedProfissional);

        // Listar todos os profissionais
        List<Profissional> profissionais = profissionalRepository.buscaTodos();
        profissionais.forEach(p -> System.out.println("Profissional: " + p));

        // Remover profissional
        manager.getTransaction().begin();
        profissionalRepository.remove(updatedProfissional);
        manager.getTransaction().commit();
        System.out.println("Profissional removido");

        manager.close();
    }
}

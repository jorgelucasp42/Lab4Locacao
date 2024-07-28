package com.imobiliaria.repository;

import com.imobiliaria.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ClienteRepository extends DAOGenerico<Cliente> {
    public ClienteRepository(EntityManager manager) {
        super(manager);
    }

    @Override
    public Cliente salvaOuAtualiza(Cliente cliente) {
        EntityManager em = getManager();

        // Verificar se já existe um cliente com o mesmo CPF
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
        query.setParameter("cpf", cliente.getCpf());
        List<Cliente> resultados = query.getResultList();

        if (resultados.isEmpty()) {
            em.persist(cliente);  // Inserir novo cliente
        } else {
            Cliente clienteExistente = resultados.get(0);
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setTelefone(cliente.getTelefone());
            clienteExistente.setEmail(cliente.getEmail());
            clienteExistente.setDtNascimento(cliente.getDtNascimento());
            cliente = em.merge(clienteExistente);    // Atualizar cliente existente
        }
        return cliente;
    }

    public boolean isCpfUnique(String cpf) {
        EntityManager em = getManager();
        TypedQuery<Cliente> query = em.createQuery("SELECT c FROM Cliente c WHERE c.cpf = :cpf", Cliente.class);
        query.setParameter("cpf", cpf);
        List<Cliente> resultados = query.getResultList();
        return resultados.isEmpty();
    }
}

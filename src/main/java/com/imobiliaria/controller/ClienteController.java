package com.imobiliaria.controller;

import com.imobiliaria.model.Cliente;
import com.imobiliaria.repository.ClienteRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final ClienteRepository clienteRepository = new ClienteRepository(manager);

    @POST
    public Response createCliente(Cliente cliente) {
        manager.getTransaction().begin();
        Cliente savedCliente = clienteRepository.salvaOuAtualiza(cliente);
        manager.getTransaction().commit();
        return Response.ok(savedCliente).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCliente(@PathParam("id") Integer id, Cliente cliente) {
        cliente.setId(id);
        manager.getTransaction().begin();
        Cliente updatedCliente = clienteRepository.salvaOuAtualiza(cliente);
        manager.getTransaction().commit();
        return Response.ok(updatedCliente).build();
    }

    @GET
    @Path("/{id}")
    public Response getClienteById(@PathParam("id") Integer id) {
        Cliente cliente = clienteRepository.buscaPor(id);
        return Response.ok(cliente).build();
    }

    @GET
    @Path("/search")
    public Response getClientesByName(@QueryParam("nome") String nome) {
        List<Cliente> clientes = clienteRepository.buscaPor(nome);
        return Response.ok(clientes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCliente(@PathParam("id") Integer id) {
        Cliente cliente = clienteRepository.buscaPor(id);
        if (cliente != null) {
            manager.getTransaction().begin();
            clienteRepository.remove(cliente);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}


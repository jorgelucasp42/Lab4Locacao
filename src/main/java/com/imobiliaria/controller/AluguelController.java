package com.imobiliaria.controller;

import com.imobiliaria.model.Aluguel;
import com.imobiliaria.repository.AluguelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/aluguels")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AluguelController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final AluguelRepository aluguelRepository = new AluguelRepository(manager);

    @POST
    public Response createAluguel(Aluguel aluguel) {
        manager.getTransaction().begin();
        Aluguel savedAluguel = aluguelRepository.salvaOuAtualiza(aluguel);
        manager.getTransaction().commit();
        return Response.ok(savedAluguel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAluguel(@PathParam("id") Integer id, Aluguel aluguel) {
        aluguel.setId(id);
        manager.getTransaction().begin();
        Aluguel updatedAluguel = aluguelRepository.salvaOuAtualiza(aluguel);
        manager.getTransaction().commit();
        return Response.ok(updatedAluguel).build();
    }

    @GET
    @Path("/{id}")
    public Response getAluguelById(@PathParam("id") Integer id) {
        Aluguel aluguel = aluguelRepository.buscaPor(id);
        return Response.ok(aluguel).build();
    }

    @GET
    public Response getAllAluguels() {
        List<Aluguel> aluguels = aluguelRepository.buscaTodos();
        return Response.ok(aluguels).build();
    }

    @GET
    @Path("/inquilino")
    public Response getAluguelsByInquilinoNome(@QueryParam("nome") String nome) {
        List<Aluguel> aluguels = aluguelRepository.buscaPorInquilinoNome(nome);
        return Response.ok(aluguels).build();
    }

    @GET
    @Path("/atrasados")
    public Response getAtrasados() {
        List<Aluguel> aluguels = aluguelRepository.buscaAtrasados();
        return Response.ok(aluguels).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAluguel(@PathParam("id") Integer id) {
        Aluguel aluguel = aluguelRepository.buscaPor(id);
        if (aluguel != null) {
            manager.getTransaction().begin();
            aluguelRepository.remove(aluguel);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

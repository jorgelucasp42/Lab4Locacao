package com.imobiliaria.controller;

import com.imobiliaria.model.ServicoImovel;
import com.imobiliaria.repository.ServicoImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/servicosImovel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ServicoImovelController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final ServicoImovelRepository servicoImovelRepository = new ServicoImovelRepository(manager);

    @POST
    public Response createServicoImovel(ServicoImovel servicoImovel) {
        manager.getTransaction().begin();
        ServicoImovel savedServicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);
        manager.getTransaction().commit();
        return Response.ok(savedServicoImovel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateServicoImovel(@PathParam("id") Integer id, ServicoImovel servicoImovel) {
        servicoImovel.setId(id);
        manager.getTransaction().begin();
        ServicoImovel updatedServicoImovel = servicoImovelRepository.salvaOuAtualiza(servicoImovel);
        manager.getTransaction().commit();
        return Response.ok(updatedServicoImovel).build();
    }

    @GET
    @Path("/{id}")
    public Response getServicoImovelById(@PathParam("id") Integer id) {
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPor(id);
        return Response.ok(servicoImovel).build();
    }

    @GET
    public Response getAllServicosImovel() {
        List<ServicoImovel> servicosImovel = servicoImovelRepository.buscaTodos();
        return Response.ok(servicosImovel).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteServicoImovel(@PathParam("id") Integer id) {
        ServicoImovel servicoImovel = servicoImovelRepository.buscaPor(id);
        if (servicoImovel != null) {
            manager.getTransaction().begin();
            servicoImovelRepository.remove(servicoImovel);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

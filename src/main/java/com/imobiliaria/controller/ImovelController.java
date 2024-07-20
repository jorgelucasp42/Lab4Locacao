package com.imobiliaria.controller;

import com.imobiliaria.model.Imovel;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/imoveis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImovelController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final ImovelRepository imovelRepository = new ImovelRepository(manager);

    @POST
    public Response createImovel(Imovel imovel) {
        manager.getTransaction().begin();
        Imovel savedImovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();
        return Response.ok(savedImovel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateImovel(@PathParam("id") Integer id, Imovel imovel) {
        imovel.setId(id);
        manager.getTransaction().begin();
        Imovel updatedImovel = imovelRepository.salvaOuAtualiza(imovel);
        manager.getTransaction().commit();
        return Response.ok(updatedImovel).build();
    }

    @GET
    @Path("/{id}")
    public Response getImovelById(@PathParam("id") Integer id) {
        Imovel imovel = imovelRepository.buscaPor(id);
        return Response.ok(imovel).build();
    }

    @GET
    public Response getAllImoveis() {
        List<Imovel> imoveis = imovelRepository.buscaTodos();
        return Response.ok(imoveis).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteImovel(@PathParam("id") Integer id) {
        Imovel imovel = imovelRepository.buscaPor(id);
        if (imovel != null) {
            manager.getTransaction().begin();
            imovelRepository.remove(imovel);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

package com.imobiliaria.controller;

import com.imobiliaria.model.TipoImovel;
import com.imobiliaria.repository.TipoImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tiposImovel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipoImovelController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final TipoImovelRepository tipoImovelRepository = new TipoImovelRepository(manager);

    @POST
    public Response createTipoImovel(TipoImovel tipoImovel) {
        manager.getTransaction().begin();
        TipoImovel savedTipoImovel = tipoImovelRepository.salvaOuAtualiza(tipoImovel);
        manager.getTransaction().commit();
        return Response.ok(savedTipoImovel).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateTipoImovel(@PathParam("id") Integer id, TipoImovel tipoImovel) {
        tipoImovel.setId(id);
        manager.getTransaction().begin();
        TipoImovel updatedTipoImovel = tipoImovelRepository.salvaOuAtualiza(tipoImovel);
        manager.getTransaction().commit();
        return Response.ok(updatedTipoImovel).build();
    }

    @GET
    @Path("/{id}")
    public Response getTipoImovelById(@PathParam("id") Integer id) {
        TipoImovel tipoImovel = tipoImovelRepository.buscaPor(id);
        return Response.ok(tipoImovel).build();
    }

    @GET
    public Response getAllTiposImovel() {
        List<TipoImovel> tiposImovel = tipoImovelRepository.buscaTodos();
        return Response.ok(tiposImovel).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTipoImovel(@PathParam("id") Integer id) {
        TipoImovel tipoImovel = tipoImovelRepository.buscaPor(id);
        if (tipoImovel != null) {
            manager.getTransaction().begin();
            tipoImovelRepository.remove(tipoImovel);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

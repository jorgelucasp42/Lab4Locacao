package com.imobiliaria.controller;

import com.imobiliaria.model.Profissional;
import com.imobiliaria.repository.ProfissionalRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/profissionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfissionalController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final ProfissionalRepository profissionalRepository = new ProfissionalRepository(manager);

    @POST
    public Response createProfissional(Profissional profissional) {
        manager.getTransaction().begin();
        Profissional savedProfissional = profissionalRepository.salvaOuAtualiza(profissional);
        manager.getTransaction().commit();
        return Response.ok(savedProfissional).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateProfissional(@PathParam("id") Integer id, Profissional profissional) {
        profissional.setId(id);
        manager.getTransaction().begin();
        Profissional updatedProfissional = profissionalRepository.salvaOuAtualiza(profissional);
        manager.getTransaction().commit();
        return Response.ok(updatedProfissional).build();
    }

    @GET
    @Path("/{id}")
    public Response getProfissionalById(@PathParam("id") Integer id) {
        Profissional profissional = profissionalRepository.buscaPor(id);
        return Response.ok(profissional).build();
    }

    @GET
    public Response getAllProfissionais() {
        List<Profissional> profissionais = profissionalRepository.buscaTodos();
        return Response.ok(profissionais).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProfissional(@PathParam("id") Integer id) {
        Profissional profissional = profissionalRepository.buscaPor(id);
        if (profissional != null) {
            manager.getTransaction().begin();
            profissionalRepository.remove(profissional);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

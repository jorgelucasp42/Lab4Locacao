package com.imobiliaria.controller;

import com.imobiliaria.model.Locacao;
import com.imobiliaria.repository.LocacaoRepository;
import com.imobiliaria.repository.ImovelRepository;
import com.imobiliaria.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/locacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LocacaoController {

    private final EntityManager manager = JPAUtil.getEntityManager();
    private final LocacaoRepository locacaoRepository = new LocacaoRepository(manager);
    private final ImovelRepository imovelRepository = new ImovelRepository(manager);

    @POST
    public Response createLocacao(Locacao locacao) {
        if (!imovelRepository.estaDisponivelParaLocacao(locacao.getImovel().getId())) {
            return Response.status(Response.Status.CONFLICT).entity("Imóvel não está disponível para locação").build();
        }
        manager.getTransaction().begin();
        Locacao savedLocacao = locacaoRepository.salvaOuAtualiza(locacao);
        manager.getTransaction().commit();
        return Response.ok(savedLocacao).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateLocacao(@PathParam("id") Integer id, Locacao locacao) {
        locacao.setId(id);
        manager.getTransaction().begin();
        Locacao updatedLocacao = locacaoRepository.salvaOuAtualiza(locacao);
        manager.getTransaction().commit();
        return Response.ok(updatedLocacao).build();
    }

    @GET
    @Path("/{id}")
    public Response getLocacaoById(@PathParam("id") Integer id) {
        Locacao locacao = locacaoRepository.buscaPor(id);
        return Response.ok(locacao).build();
    }

    @GET
    public Response getAllLocacoes() {
        List<Locacao> locacoes = locacaoRepository.buscaTodos();
        return Response.ok(locacoes).build();
    }

    @GET
    @Path("/inquilino/{inquilinoId}")
    public Response getLocacoesByInquilinoId(@PathParam("inquilinoId") Integer inquilinoId) {
        List<Locacao> locacoes = locacaoRepository.buscaPorInquilinoId(inquilinoId);
        return Response.ok(locacoes).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLocacao(@PathParam("id") Integer id) {
        Locacao locacao = locacaoRepository.buscaPor(id);
        if (locacao != null) {
            manager.getTransaction().begin();
            locacaoRepository.remove(locacao);
            manager.getTransaction().commit();
        }
        return Response.noContent().build();
    }
}

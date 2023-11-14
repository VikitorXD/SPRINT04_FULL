package org.example.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.models.Solicitacao;
import org.example.repository.SolicitacaoRepository;

import java.sql.SQLException;
import java.util.List;

@Path("solicitacao")
public class SolicitacaoResource {

    private final SolicitacaoRepository repository = new SolicitacaoRepository();

    @GET
    @Path("todos")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Solicitacao> getAll() throws SQLException {
        return repository.findSolit();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Solicitacao getSolitById(@PathParam("id") int id) throws SQLException {
        return repository.findby(id).orElse(null);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Solicitacao solicitacao) throws SQLException {
        repository.add(solicitacao);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response UpdateSolit(@PathParam("id") int id, Solicitacao solicitacao) throws SQLException {
        if (repository.findby(id).isPresent()) {
            solicitacao.setIdsolit(id);
            repository.update(solicitacao);
            return Response.status(Response.Status.CREATED).entity(solicitacao).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity(solicitacao).build();
    }

}

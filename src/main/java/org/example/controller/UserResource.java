package org.example.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.example.models.User;
import org.example.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

@Path("usuario")
public class UserResource {

    private final UserRepository repository = new UserRepository();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> findUser() throws SQLException {
        return repository.findUser();
    }
}

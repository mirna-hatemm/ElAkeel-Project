package com.example.toolsproject.Resources;

import com.example.toolsproject.Entities.User;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @PersistenceContext(unitName = "inMemory")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    // user register
    @POST
    @Path("/register")
    public String register(User user) {
        try {
            userTransaction.begin();
            entityManager.persist(user);
            userTransaction.commit();
            return "Registered successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Registration failed";
        }
    }

    // user login
    @POST
    @Path("/login")
    public String login(User user) {
        try {
            User user1 = entityManager.find(User.class, user.getUsername());
            if (user1.getPassword().equals(user.getPassword())) {
                return "Login successful";
            } else {
                return "Login failed";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Login failed";
        }
    }
}

package com.example.toolsproject.Resources;

import com.example.toolsproject.Entities.Meal;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/meal")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MealResource {
    @PersistenceContext(unitName = "inMemory")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    @POST
    @Path("/addMeal")
    public String addMeal(Meal meal) {
        try {
            userTransaction.begin();
            entityManager.persist(meal);
            userTransaction.commit();
            return "Meal added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Meal not added";
        }

    }

    @PUT
    @Path("/updateMeal")
    public String updateMeal(Meal meal) {
        try {
            userTransaction.begin();
            entityManager.merge(meal);
            userTransaction.commit();
            return "Meal updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Meal not updated";
        }
    }

    @DELETE
    @Path("/deleteMeal/{id}")
    public String deleteMeal(@PathParam("id") Long id) {
        try {
            Meal meal = entityManager.find(Meal.class, id);
            userTransaction.begin();
            entityManager.remove(entityManager.contains(meal) ? meal : entityManager.merge(meal));
            userTransaction.commit();
            return "Meal deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Meal not deleted";
        }
    }

    @GET
    @Path("/getMealById/{id}")
    public Meal getMealById(@PathParam("id") Long id) {
        return entityManager.find(Meal.class, id);
    }

    @GET
    @Path("/getAllMeals")
    public List<Meal> getAllMeals() {
        return entityManager.createQuery("SELECT meal FROM Meal meal", Meal.class).getResultList();
    }

}


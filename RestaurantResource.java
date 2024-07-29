package com.example.toolsproject.Resources;

import com.example.toolsproject.Entities.Meal;
import com.example.toolsproject.Entities.Restaurant;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/restaurant")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestaurantResource {

    @PersistenceContext(unitName = "inMemory")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;


    // add Restaurant
    @POST
    @Path("/addRestaurant")
    public String addRestaurant(Restaurant restaurant) {
        try {
            userTransaction.begin();
            entityManager.persist(restaurant);
            userTransaction.commit();
            return "Restaurant added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Restaurant addition failed";
        }
    }

    // delete Restaurant
    @DELETE
    @Path("/deleteRestaurant")
    public String deleteRestaurant(Restaurant restaurant) {
        try {
            userTransaction.begin();
            entityManager.remove(restaurant);
            userTransaction.commit();
            return "Restaurant deleted successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Restaurant deletion failed";
        }
    }

    // update Restaurant
    @PUT
    @Path("/updateRestaurant")
    public String updateRestaurant(Restaurant restaurant) {
        try {
            userTransaction.begin();
            entityManager.merge(restaurant);
            userTransaction.commit();
            return "Restaurant updated successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Restaurant updation failed";
        }
    }

    // get Restaurant by id
    @GET
    @Path("/getRestaurant/{id}")
    public Restaurant getRestaurant(@PathParam("id") Long id) {
        try {
            return entityManager.find(Restaurant.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // get all Restaurants
    @GET
    @Path("/getAllRestaurants")
    public List<Restaurant> getAllRestaurants() {
        try {
            return entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // add meals to restaurant
    @PUT
    @Path("/addMealsToRestaurant/{mealId}")
    public String addMealsToRestaurant(@PathParam("mealId") Long mealId) {
        try {
            Meal meal = entityManager.find(Meal.class, mealId);
            Restaurant restaurant = entityManager.find(Restaurant.class, meal.getRestaurantId());
            restaurant.getMeals().add(meal);
            userTransaction.begin();
            entityManager.merge(restaurant);
            userTransaction.commit();
            return "Meals added to restaurant successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Meals addition to restaurant failed";
        }
    }

    // return all meals in a restaurant by id
    @GET
    @Path("/getMealsByRestaurantId/{id}")
    public List<Meal> getMealsByRestaurantId(@PathParam("id") Long id) {
        Restaurant restaurant = entityManager.find(Restaurant.class, id);
        List<Meal> meals = new ArrayList<>();

        for (int i = 0; i < restaurant.getMeals().size(); i++) {
            meals.add(restaurant.getMeals().get(i));
        }
        return meals;
    }
}

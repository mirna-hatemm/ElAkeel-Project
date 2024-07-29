package com.example.toolsproject.Resources;

import com.example.toolsproject.Entities.Meal;
import com.example.toolsproject.Entities.Order;
import com.example.toolsproject.Entities.Restaurant;
import com.example.toolsproject.Status.OrderStatus;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    @PersistenceContext(unitName = "inMemory")
    private EntityManager entityManager;

    @Resource
    private UserTransaction userTransaction;

    private List<Meal> meals;


    @POST
    @Path("/addMeals/{mealId}/{restaurantId}")
    public String addMeals(@PathParam("restaurantId") Long restaurantId,
                           @PathParam("mealId") Long mealId) {
        try {
            Restaurant restaurant = entityManager.find(Restaurant.class, restaurantId);
            Meal meal = entityManager.find(Meal.class, mealId);
            if (!restaurant.getMeals().contains(meal)) {
                return "Restaurant doesn't serve this meal.";
            }

            List<Meal> meals = new ArrayList<>();
            meals.add(meal);

            setMeals(meals);
            return "Meals added successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Meals addition failed";
        }
    }


    @POST
    @Path("/makeOrder")
    public String makeOrder() {
        try {
            Order order = new Order();
            float totalPrice = 0;
            for (Meal meal : getMeals()) {
                totalPrice += meal.getPrice();
            }

            order.setTotalPrice(totalPrice);
            order.setStatus(OrderStatus.PREPARING);
            order.setMeals(getMeals());

            userTransaction.begin();
            entityManager.persist(order);
            userTransaction.commit();
            return "Order made successfully";
        } catch (Exception e) {
            e.printStackTrace();
            return "Order failed";
        }
    }


    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}

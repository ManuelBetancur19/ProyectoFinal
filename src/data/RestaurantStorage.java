package data;

import domain.*;
import java.util.ArrayList;

public class RestaurantStorage {

    public static void saveRestaurant(Restaurant restaurant) {
        try {
            CSVManager.saveCustomers(restaurant.getCustomers());
            CSVManager.saveMenu(restaurant.getMenu());
            CSVManager.saveOrders(restaurant.getOrders());
            System.out.println("Restaurant saved successfully");
        } catch (Exception e) {
            System.out.println("Error saving restaurant: " + e.getMessage());
        }
    }

    public static Restaurant loadRestaurant() {
        Restaurant r = new Restaurant();

        try {
            ArrayList<Customer> customers = CSVManager.loadCustomers();
            ArrayList<MenuItem> menu = CSVManager.loadMenu();
            ArrayList<Order> orders = CSVManager.loadOrders(customers);

            r.setCustomers(customers);
            r.setMenu(menu);
            r.setOrders(orders);

            System.out.println("Restaurant loaded successfully");

        } catch (Exception e) {
            System.out.println("Error loading restaurant: " + e.getMessage());
        }

        return r;
    }
}

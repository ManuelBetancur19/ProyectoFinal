package ui;

import data.*;
import domain.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {

            Restaurant restaurant = RestaurantStorage.loadRestaurant();
            restaurant.fixCounters();

            // initial data to test
            if (restaurant.getTables().isEmpty()) {
                restaurant.addTable(2);
                restaurant.addTable(4);
                restaurant.addTable(6);
            }

            if (restaurant.getMenu().isEmpty()) {
                restaurant.addMenuItem("Bandeja Paisa", "Carne, frijoles, arroz, huevo", 35000);
                restaurant.addMenuItem("Ajiaco", "Sopa típica", 25000);
            }

            boolean running = true;
            while (running) {
                try {
                    System.out.println("\n===== RESTAURANT MANAGEMENT SYSTEM =====");
                    System.out.println("1. Manage Menu Items");
                    System.out.println("2. Manage Tables");
                    System.out.println("3. Manage Customers");
                    System.out.println("4. Manage Orders");
                    System.out.println("5. Reports");
                    System.out.println("6. Exit");
                    System.out.print("Choose an option: ");
                    String option = scanner.nextLine();

                    switch (option) {
                        case "1":
                            manageMenu(restaurant);
                            break;
                        case "2":
                            manageTables(restaurant);
                            break;
                        case "3":
                            manageCustomers(restaurant);
                            break;
                        case "4":
                            manageOrders(restaurant);
                            break;
                        case "5":
                            manageReports(restaurant);
                            break;
                        case "6":
                            running = false;
                            RestaurantStorage.saveRestaurant(restaurant);
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            System.out.println("Program finished.");
        } catch (Exception e) {
            System.out.println("Fatal error: " + e.getMessage());
        }
    }

    // ---------------- MENU ----------------
    private static void manageMenu(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- MENU MANAGEMENT ---");
                System.out.println("1. Add Menu Item");
                System.out.println("2. List Menu Items");
                System.out.println("3. Delete Menu Item");
                System.out.println("4. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter description: ");
                            String desc = scanner.nextLine();

                            System.out.print("Enter price: ");
                            double price = Double.parseDouble(scanner.nextLine());

                            if (price < 0) {
                                System.out.println("Price cannot be negative.");
                                break;
                            }

                            MenuItem mi = r.addMenuItem(name, desc, price);
                            System.out.println("Added: " + mi);
                        } catch (Exception e) {
                            System.out.println("Error adding menu item.");
                        }
                        break;

                    case "2":
                        try {
                            listMenuItems(r);
                        } catch (Exception e) {
                            System.out.println("Error listing menu.");
                        }
                        break;

                    case "3":
                        try {
                            listMenuItems(r);
                            System.out.print("Enter menu ID to delete: ");
                            int mid = Integer.parseInt(scanner.nextLine());
                            boolean removed = r.deleteMenuItem(mid);
                            System.out.println(removed ? "Deleted." : "Not found.");
                        } catch (Exception e) {
                            System.out.println("Error deleting menu item.");
                        }
                        break;

                    case "4":
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void listMenuItems(Restaurant r) {
        try {
            System.out.println("\n--- MENU ITEMS ---");
            for (MenuItem m : r.getMenu())
                System.out.println(m);
        } catch (Exception e) {
            System.out.println("Error printing menu.");
        }
    }

    // ---------------- TABLES ----------------
    private static void manageTables(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- TABLE MANAGEMENT ---");
                System.out.println("1. Add Table");
                System.out.println("2. List Tables");
                System.out.println("3. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter number of seats: ");
                            int seats = Integer.parseInt(scanner.nextLine());
                            if (seats <= 0) {
                                System.out.print("Number of seats cannot be negative");
                            } else {
                                Table t = r.addTable(seats);
                                System.out.println("Added table: " + t);
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding table.");
                        }
                        break;

                    case "2":
                        try {
                            System.out.println("\n--- TABLES ---");
                            for (Table table : r.getTables())
                                System.out.println(table);
                        } catch (Exception e) {
                            System.out.println("Error listing tables.");
                        }
                        break;

                    case "3":
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- CUSTOMERS ----------------
    private static void manageCustomers(Restaurant r) {
        boolean loop = true;
        while (loop) {
            try {
                System.out.println("\n--- CUSTOMER MANAGEMENT ---");
                System.out.println("1. Add Customer (auto-assign first free table)");
                System.out.println("2. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            Table free = r.getFirstFreeTable();
                            if (free == null) {
                                System.out.println("All tables are occupied. Cannot add customer.");
                                break;
                            }

                            System.out.print("Enter customer name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter identification number: ");
                            String idNumber = scanner.nextLine();

                            Customer exists = r.getCustomerByIdentification(idNumber);
                            if (exists != null) {
                                System.out.println("Error: A customer with that ID already exists.");
                            } else {
                                Customer c = r.addCustomer(name, idNumber);
                                System.out.println("Added: " + c);
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding customer.");
                        }
                        break;

                    case "2":
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- ORDERS ----------------
    private static void manageOrders(Restaurant r) {
        boolean loop = true;
        Order currentOrder = null;
        while (loop) {
            try {
                System.out.println("\n--- ORDERS MANAGEMENT ---");
                System.out.println("1. Create Order");
                System.out.println("2. Add Item to Order");
                System.out.println("3. Remove Item from Order");
                System.out.println("4. Close Order");
                System.out.println("5. View active orders");
                System.out.println("6. Cancel order");
                System.out.println("7. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        try {
                            System.out.print("Enter customer identification number: ");
                            String identification = scanner.nextLine();
                            if (identification == null) {
                                identification = "";
                            }

                            Order active = r.getActiveOrderByCustomer(identification);
                            if (active != null) {
                                System.out.println("\"Error: This customer already has an active order. Order ID: "
                                        + active.getId());
                            } else {
                                Order o = r.createOrder(identification);
                                currentOrder = o;
                                System.out.println(o != null ? "Order created: " + o : "Customer not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error creating order.");
                        }
                        break;

                    case "2":
                        try {
                            if (r.getActiveOrders().isEmpty()) {
                                System.out.println("There are no active orders.");
                            } else {
                                for (Order or : r.getActiveOrders()) {
                                    System.out.println(or);
                                }
                                System.out.print("Enter order ID: ");
                                int oid = Integer.parseInt(scanner.nextLine());

                                Order ord = r.getOrderById(oid);
                                if (ord == null || ord.isClosed()) {
                                    System.out.println("This ID does not belong to any order.");
                                    break;
                                }

                                System.out.println("Menu items:");
                                for (MenuItem mi : r.getMenu())
                                    System.out.println(mi);

                                System.out.print("Enter menu item ID to add (0 to stop): ");
                                while (true) {
                                    try {
                                        int mid = Integer.parseInt(scanner.nextLine());
                                        if (mid == 0)
                                            break;

                                        boolean ok = r.addItemToOrder(oid, mid);
                                        System.out.println(
                                                ok ? "Item added." : "Failed to add (order or item not found).");
                                        System.out.print("Add another (menu ID) or 0 to stop: ");
                                    } catch (Exception e) {
                                        System.out.println("Invalid input.");
                                    }
                                }
                            }
                        } catch (Exception e) {
                            System.out.println("Error adding items.");
                        }
                        break;

                    case "3":
                        try {
                            for (Order or : r.getActiveOrders()) {
                                System.out.println(or);
                            }
                            System.out.print("Enter order ID: ");
                            int oid2 = Integer.parseInt(scanner.nextLine());

                            System.out.println("Order items:");
                            Order ord = r.getOrderById(oid2);

                            if (ord == null) {
                                System.out.println("Order not found.");
                                break;
                            }

                            if (ord.getItems().isEmpty()) {
                                System.out.println("The order has no items to remove");
                            } else {
                                for (MenuItem mi : ord.getItems())
                                    System.out.println(mi);

                                System.out.print("Enter menu item ID to remove: ");
                                int rid = Integer.parseInt(scanner.nextLine());
                                System.out.print("How many to remove?: ");
                                int qty = Integer.parseInt(scanner.nextLine());

                                r.removeItemFromOrder(oid2, rid, qty);
                                System.out.println("Removed (if present).");
                            }
                        } catch (Exception e) {
                            System.out.println("Error removing item.");
                        }
                        break;

                    case "4":
                        try {
                            for (Order or : r.getActiveOrders()) {
                                System.out.println(or);
                            }
                            System.out.print("Enter order ID to close: ");
                            int oid3 = Integer.parseInt(scanner.nextLine());

                            Order order = r.getOrderById(oid3);
                            if (order == null) {
                                System.out.println("Order not found.");
                                break;
                            }

                            boolean closed = r.closeOrder(oid3);

                            if (closed) {
                                Customer cust = order.getCustomer();
                                Table t = cust.getAssignedTable();
                                if (t != null)
                                    r.freeTable(t.getId());

                                System.out.println("Order closed. Total: $" + order.calculateTotal());
                            } else {
                                System.out.println("Order not found.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error closing order.");
                        }
                        break;

                    case "5":
                        System.out.println("\n--- ACTIVE ORDERS ---");
                        ArrayList<Order> active = r.getActiveOrders();

                        if (active.isEmpty()) {
                            System.out.println("No active orders.");
                        } else {
                            for (Order o : active) {
                                System.out.println(o);
                            }
                        }
                        break;

                    case "6":
                        try {
                            if (r.getActiveOrders().isEmpty()) {
                                System.out.println("There are no active orders to cancel.");
                                break;
                            }

                            System.out.println("--- ACTIVE ORDERS ---");
                            for (Order or : r.getActiveOrders()) {
                                System.out.println(or);
                            }

                            System.out.print("Enter order ID to cancel: ");
                            int cid = Integer.parseInt(scanner.nextLine());

                            boolean cancelled = r.cancelOrder(cid);

                            if (cancelled) {
                                System.out.println("Order cancelled successfully.");
                            } else {
                                System.out.println("Order not found or already closed.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error cancelling order.");
                        }
                        break;

                    case "7":
                        if (currentOrder != null && currentOrder.calculateTotal() == 0) {
                            r.deleteOrder(currentOrder.getId());
                            System.out.println("Empty order remove");
                        }
                        loop = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- REPORTS ----------------
    private static void manageReports(Restaurant r) {
        boolean loop = true;

        while (loop) {
            try {
                System.out.println("\n--- REPORTS ---");
                System.out.println("1. Most Sold Dish");
                System.out.println("2. Top Customer");
                System.out.println("3. List of customers");
                System.out.println("4. List of orders");
                System.out.println("5. Back");
                System.out.print("Choose: ");
                String op = scanner.nextLine();

                switch (op) {
                    case "1":
                        MenuItem most = r.getMostSoldDish();
                        if (most == null) {
                            System.out.println("No dishes registered.");
                        } else {
                            System.out.println(
                                    "Most sold dish: " + most.getName() +
                                            " (sold " + most.getTimesSold() + " times)");
                        }
                        break;

                    case "2":
                        Customer top = r.getTopCustomer();
                        if (top == null) {
                            System.out.println("No customers registered.");
                        } else {
                            System.out.println(
                                    "Top customer: " + top.getName() +
                                            " (purchases: " + top.getPurchases() + ")");
                        }
                        break;

                    case "3":
                        try {
                            System.out.println("\n--- CUSTOMERS ---");
                            if (r.getCustomers().isEmpty()) {
                                System.out.println("No customers registered.");
                                break;
                            }
                            for (Customer cu : r.getCustomers())
                                System.out.println(cu);
                        } catch (Exception e) {
                            System.out.println("Error listing customers.");
                        }
                        break;

                    case "4":
                        try {
                            System.out.println("\n--- ORDERS ---");
                            if (r.getOrders().isEmpty()) {
                                System.out.println("No orders registered.");
                                break;
                            }
                            for (Order or : r.getOrders())
                                System.out.println(or);
                        } catch (Exception e) {
                            System.out.println("Error listing orders.");
                        }
                        break;

                    case "5":
                        loop = false;
                        break;

                    default:
                        System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}

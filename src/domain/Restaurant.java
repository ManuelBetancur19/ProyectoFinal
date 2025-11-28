package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant implements Serializable {

    // variables
    private String name;
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private List<Customer> tempCustomers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();
    private ArrayList<Order> activeOrders = new ArrayList<>();
    private List<Integer> freeOrderIds = new ArrayList<>();
    private int nextOrderId = 1;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

    // ---------------- TABLE HELPERS ----------------

    // returns the first free table
    public Table getFirstFreeTable() {
        for (Table t : tables) {
            if (!t.isOccupied()) {
                return t;
            }
        }
        return null;
    }

    // frees up a table when an order is closed
    public void freeTable(int tableId) {
        for (Table t : tables) {
            if (t.getId() == tableId) {
                t.setOccupied(false);
                return;
            }
        }
    }

    // ---------------- CUSTOMERS ----------------

    // Set the full list of customers
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    // Add a new customer
    // Automatically assigns the first free table
    public Customer addCustomer(String name, String identificationNumber) {

        Table freeTable = getFirstFreeTable();
        if (freeTable == null) {
            return null;
        }

        Customer c = new Customer(name, identificationNumber);
        c.setAssignedTable(freeTable);

        freeTable.setOccupied(true);
        tempCustomers.add(c);

        return c;
    }

    // Get the list of all customers
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // Find a customer by their identification number
    public Customer getCustomerByIdentification(String idNumber) {
        for (Customer c : customers) {
            if (c.getIdentificationNumber().equals(idNumber)) {
                return c;
            }
        }
        return null;
    }

    // Find a temporary customer by identification number
    private Customer getTempCustomerById(String id) {
        for (Customer c : tempCustomers) {
            if (c.getIdentificationNumber().equals(id)) {
                return c;
            }
        }
        return null;
    }

    // Get the active order of a customer by their ID
    public Order getActiveOrderByCustomer(String identification) {
        for (Order o : activeOrders) {
            if (o.getCustomer().getIdentificationNumber().equals(identification) && !o.isClosed()) {
                return o;
            }
        }
        return null;
    }

    // Get the top customer (most purchases)
    public Customer getTopCustomer() {
        if (customers.isEmpty())
            return null;

        Customer top = customers.get(0);

        for (Customer c : customers) {
            if (c.getPurchases() > top.getPurchases()) {
                top = c;
            }
        }

        return top;
    }

    // ---------------- ORDERS ----------------

    // Set the full list of orders
    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    // Fix counters for Order IDs
    public void fixCounters() {
        int maxOrderId = 0;
        for (Order o : orders) {
            if (o.getId() > maxOrderId)
                maxOrderId = o.getId();
        }
        Order.setCounter(maxOrderId + 1);
        nextOrderId = maxOrderId + 1;
    }

    // Create a new order for a customer by their identification number
    public Order createOrder(String identificationNumber) {
        Customer c = getCustomerByIdentification(identificationNumber);
        if (c == null) {
            c = getTempCustomerById(identificationNumber);
        }
        if (c == null)
            return null;

        int id = generateOrderId();
        Order o = new Order(id, c);
        activeOrders.add(o);
        return o;
    }

    // Get all active orders
    public ArrayList<Order> getActiveOrders() {
        return activeOrders;
    }

    // Get all orders
    public ArrayList<Order> getOrders() {
        return orders;
    }

    // Find order by Id
    public Order getOrderById(int id) {
        for (Order o : activeOrders)
            if (o.getId() == id)
                return o;

        for (Order o : orders)
            if (o.getId() == id)
                return o;

        return null;
    }

    // Get an order by its ID
    private int generateOrderId() {
        if (!freeOrderIds.isEmpty()) {
            return freeOrderIds.remove(0);
        }
        return nextOrderId++;
    }

    // ---------------- MENU ----------------

    // Set the full list of menu items
    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    // Add a new menu item with name, description, and price
    public MenuItem addMenuItem(String name, String desc, double price) {
        MenuItem m = new MenuItem(name, desc, price);
        menu.add(m);
        return m;
    }

    // Delete a menu item by its ID
    public boolean deleteMenuItem(int id) {
        return menu.removeIf(m -> m.getId() == id);
    }

    // Get the full list of menu items
    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    // Get the menu item that has been sold the most times
    public MenuItem getMostSoldDish() {
        if (menu.isEmpty())
            return null;

        MenuItem most = menu.get(0);

        for (MenuItem item : menu) {
            if (item.getTimesSold() > most.getTimesSold()) {
                most = item;
            }
        }

        return most;
    }

    // ---------------- TABLES ----------------

    // Add a new table with a specific number of seats
    public Table addTable(int seats) {
        Table t = new Table(seats);
        tables.add(t);
        return t;
    }

    // Get the full list of tables
    public ArrayList<Table> getTables() {
        return tables;
    }

    // ---------------- ORDER ITEMS ----------------

    // Add a MenuItem to an existing order by orderId and menuItemId
    public boolean addItemToOrder(int orderId, int menuItemId) {
        Order o = getOrderById(orderId);
        if (o == null)
            return false;

        for (MenuItem m : menu) {
            if (m.getId() == menuItemId) {
                o.addItem(m);
                return true;
            }
        }
        return false;
    }

    // Remove a quantity of a MenuItem from an order
    public void removeItemFromOrder(int orderId, int menuId, int qty) {
        Order o = getOrderById(orderId);
        if (o == null)
            return;

        MenuItem toRemove = null;
        for (MenuItem m : o.getItems()) {
            if (m.getId() == menuId) {
                toRemove = m;
                break;
            }
        }

        if (toRemove != null) {
            for (int i = 0; i < qty; i++) {
                o.removeItem(toRemove);
            }
        }
    }

    // Close an order: update customer purchases, item sold count, free table, move temp customer to main list
    public boolean closeOrder(int orderId) {
        Order o = getOrderById(orderId);
        if (o == null)
            return false;

        if (o.getItems().isEmpty()) {
            activeOrders.remove(o);
            freeOrderIds.add(o.getId());
            Collections.sort(freeOrderIds);
            return true;
        }
        Customer c = o.getCustomer();

        if (c != null) {
            c.incrementPurchases();
        }

        for (MenuItem item : o.getItems()) {
            item.incrementSold();
        }

        o.closeOrder();
        activeOrders.remove(o);

        if (tempCustomers.contains(c)) {
            tempCustomers.remove(c);
            customers.add(c);
        }

        if (c.getAssignedTable() != null) {
            c.getAssignedTable().setOccupied(false);
        }

        if (!orders.contains(o)) {
            orders.add(o);
        }
        freeOrderIds.add(o.getId());
        Collections.sort(freeOrderIds);

        return true;
    }

    // Delete an order by ID (active orders only)
    public boolean deleteOrder(int id) {
        freeOrderIds.add(id);
        Collections.sort(freeOrderIds);

        return activeOrders.removeIf(o -> o.getId() == id);
    }

    // Cancel an active order without closing it, free the table and remove temp customer if needed
    public boolean cancelOrder(int orderId) {

        Order o = getOrderById(orderId);

        if (o == null || o.isClosed()) {
            return false;
        }

        activeOrders.remove(o);

        freeOrderIds.add(o.getId());
        Collections.sort(freeOrderIds);

        Customer c = o.getCustomer();

        if (c != null && c.getAssignedTable() != null) {
            c.getAssignedTable().setOccupied(false);
            c.setAssignedTable(null);
        }

        if (c != null && tempCustomers.contains(c)) {
            tempCustomers.remove(c);
        }

        return true;
    }
}

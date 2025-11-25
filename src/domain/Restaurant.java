package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable {

    // variables
    private String name;
    private ArrayList<MenuItem> menu = new ArrayList<>();
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Order> orders = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    // ---------------- CUSTOMERS ----------------

    public Customer addCustomer(String name, String identificationNumber) {
        Customer c = new Customer(name, identificationNumber);

        // assign a free table
        for (Table t : tables) {
            if (!t.isOccupied()) {
                t.setOccupied(true);
                c.setAssignedTable(t);
                break;
            }
        }

        customers.add(c);
        return c;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    // search customer by identification number
    public Customer getCustomerByIdentification(String idNumber) {
        for (Customer c : customers) {
            if (c.getIdentificationNumber().equals(idNumber)) {
                return c;
            }
        }
        return null;
    }

    // ---------------- ORDERS ----------------

    // create the order by searching for identificationNumber
    public Order createOrder(String identificationNumber) {
        Customer c = getCustomerByIdentification(identificationNumber);
        if (c == null)
            return null;

        Order o = new Order(c);
        orders.add(o);
        return o;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public Order getOrderById(int id) {
        for (Order o : orders) {
            if (o.getId() == id)
                return o;
        }
        return null;
    }

    // ---------------- MENU ----------------

    public MenuItem addMenuItem(String name, String desc, double price) {
        MenuItem m = new MenuItem(name, desc, price);
        menu.add(m);
        return m;
    }

    public boolean deleteMenuItem(int id) {
        return menu.removeIf(m -> m.getId() == id);
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    // ---------------- TABLES ----------------

    public Table addTable(int seats) {
        Table t = new Table(seats);
        tables.add(t);
        return t;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    // ---------------- ORDER ITEMS ----------------

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

    public boolean closeOrder(int orderId) {
        Order o = getOrderById(orderId);
        if (o == null)
            return false;

        o.closeOrder();
        return true;
    }


    // fix counters
    public void fixCounters() {
    // Fix MenuItem counter
    int maxMenuId = 0;
    for (MenuItem item : menu) {
        if (item.getId() > maxMenuId)
            maxMenuId = item.getId();
    }
    MenuItem.setCounter(maxMenuId + 1);

    // Fix Table counter
    int maxTableId = 0;
    for (Table t : tables) {
        if (t.getId() > maxTableId)
            maxTableId = t.getId();
    }
    Table.setCounter(maxTableId + 1);

    // Fix Customer counter
    int maxCustomerId = 0;
    for (Customer c : customers) {
        if (c.getId() > maxCustomerId)
            maxCustomerId = c.getId();
    }
    Customer.setCounter(maxCustomerId + 1);

    // Fix Order counter
    int maxOrderId = 0;
    for (Order o : orders) {
        if (o.getId() > maxOrderId)
            maxOrderId = o.getId();
    }
    Order.setCounter(maxOrderId + 1);
}


}

package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable {

    // variables
    private static int counter = 1;
    private int id;
    private Customer customer;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private boolean closed = false;

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
    }

    public Order(Customer customer) {
    this.id = counter++;
    this.customer = customer;
}

    // setters
    public static void setCounter(int value) {
        counter = value;
    }

    // getters
    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public boolean isClosed() {
        return closed;
    }

    // methods for add/remove list
    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem m : items)
            total += m.getPrice();
        return total;
    }

    public void closeOrder() {
        closed = true;
    }

    @Override
    public String toString() {
        return "Order (id=" + id + ", customer=" + customer.getName() + ", identification="
                + customer.getIdentificationNumber() + ", total=" + calculateTotal() +
                ", closed=" + closed +
                " )";
    }
}
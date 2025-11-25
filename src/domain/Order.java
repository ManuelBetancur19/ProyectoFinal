package domain;

import java.util.ArrayList;

public class Order {
    private static int counter = 1;
    private int id;
    private Customer customer;
    private ArrayList<MenuItem> items = new ArrayList<>();
    private boolean closed = false;

    public Order(Customer customer) {
        this.id = counter++;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem m : items) total += m.getPrice();
        return total;
    }

    public void closeOrder() {
        closed = true;
    }

    @Override
    public String toString() {
        return "Order (id=" + id +
               ", customer=" + customer.getName() +
               ", identification=" + customer.getIdentificationNumber() +
               ", total=" + calculateTotal() +
               ", closed=" + closed +
               " )";
    }
}

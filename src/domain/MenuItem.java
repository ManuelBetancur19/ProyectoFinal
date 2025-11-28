package domain;

import java.io.Serializable;

public class MenuItem implements Serializable {

    // variables
    private static int counter = 1;
    private int id;
    private String name;
    private String description;
    private double price;

    // constructor
    public MenuItem(String name, String description, double price) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // setters
    public static void setCounter(int value) {
        counter = value;
    }

    // getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getTimesSold() {
        return timesSold;
    }

    @Override
    public String toString() {
        return "MenuItem ( id=" + id + ", name='" + name + "', price=" + price + " )";
    }

    private int timesSold = 0;

    //increases the number of purchases of a dish
    public void incrementSold() {
        this.timesSold++;
    }
}

package domain;

public class MenuItem {
    private static int counter = 1;
    private int id;
    private String name;
    private String description;
    private double price;

    public MenuItem(String name, String description, double price) {
        this.id = counter++;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "MenuItem ( id=" + id + ", name='" + name + "', price=" + price + " )";
    }
}

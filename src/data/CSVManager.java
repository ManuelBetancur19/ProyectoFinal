package data;

import domain.*;
import java.io.*;
import java.util.*;

public class CSVManager {

    // save customers
    public static void saveCustomers(ArrayList<Customer> customers) {
        try (FileWriter fw = new FileWriter("customers.csv")) {

            for (Customer c : customers) {
                fw.write(c.getName() + "," + c.getIdentificationNumber() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error saving customers");
        }
    }

    // save menu
    public static void saveMenu(ArrayList<MenuItem> menu) {
        try (FileWriter fw = new FileWriter("menu.csv")) {

            for (MenuItem m : menu) {
                fw.write(m.getName() + "," + m.getPrice() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error saving menu.");
        }
    }

    // save orders
    public static void saveOrders(ArrayList<Order> orders) {
        try (FileWriter fw = new FileWriter("orders.csv")) {

            for (Order o : orders) {
                fw.write(o.getCustomer().getName() + "," + o.calculateTotal() + "\n");
            }

        } catch (Exception e) {
            System.out.println("Error saving orders");
        }
    }

    // load customers
    public static ArrayList<Customer> loadCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("customers.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                String name = data[0];
                String identification = data[1];

                Customer c = new Customer(name, identification);
                customers.add(c);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading customers.csv");
        }

        return customers;
    }

    // load menu
    public static ArrayList<MenuItem> loadMenu() {
        ArrayList<MenuItem> menu = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("menu.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String description = data[1];
                double price = Double.parseDouble(data[2]);

                MenuItem m = new MenuItem(name, description, price);
                menu.add(m);
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error loading menu.csv");
        }

        return menu;
    }

    public static ArrayList<Order> loadOrders(List<Customer> customers) {
        ArrayList<Order> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("orders.csv"))) {

            String line = br.readLine();

            while (line != null) {

                String[] data = line.split(",");

                if (data.length >= 2) {

                    String customerName = data[0];
                    double total = Double.parseDouble(data[1]);

                    Customer found = null;
                    for (Customer c : customers) {
                        if (c.getName().equals(customerName)) {
                            found = c;
                        }
                    }

                    if (found != null) {
                        Order o = new Order(found);

                        MenuItem fake = new MenuItem("Total", "", total);
                        o.addItem(fake);

                        list.add(o);
                    }
                }

                line = br.readLine();
            }

        } catch (Exception e) {
            System.out.println("Error loading orders.");
        }

        return list;
    }

}

package domain;

import java.io.Serializable;
import java.util.List;

public class Restaurant implements Serializable{
    
    //information of restaurant//
    private String name;

    //Lists
    private List <MenuItem> dishes;
    private List <Table> tables;
    private List <Order> orders;
    private List <Customer> customers;
    
    //constructor//
    public Restaurant(String name, List<MenuItem> dishes, List<Table> tables, List<Order> orders, List<Customer> customers){
        setName(name);
        setDishes(dishes);
        setTables(tables);
        setOrders(orders);
        setCustomers(customers);
    }

    //setters//
    public void setName(String name){
        if(name.isEmpty() || name == null){
            this.name = "Restaurant";
        }
    }
    public void setDishes(List<MenuItem> dishes){
        this.dishes = dishes;

        }
    public void setTables(List<Table> tables){
        this.tables = tables;
        }
    public void setOrders(List<Order> orders){
        this.orders= orders;
        }
    public void setCustomers(List<Customer> customers){
        this.customers = customers;
        }
    
    //getters//
    public String getName(){
        return name;
    }
    public List<MenuItem> getDishes(){
        return dishes;
    }
    public List<Table> getTables(){
        return tables;
    }
    public List<Order> getOrders(){
        return orders;
    }
    public List<Customer> getCustomers(){
        return customers;
    }

    //methods for add/remove in the lists//
    public void addMenuItem(MenuItem item){
        dishes.add(item);
    }
    public void removeMenuItem(MenuItem item){
        dishes.remove(item);
    }

    public void addTable(Table table){
        tables.add(table);
    }
    public void removeTable(Table table){
        tables.remove(table);
    }

    public void addOrder(Order order){
        orders.add(order);
    }
    public void removeOrder(Order order){
        orders.remove(order);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }
    public void removeCustomer(Customer customer){
        customers.remove(customer);
    }

}

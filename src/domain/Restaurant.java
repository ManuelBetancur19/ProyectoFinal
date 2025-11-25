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
        if(name.isEmpty()){
            this.name = "Restaurant";
        }
        else{
            this.name = name;
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
    
}

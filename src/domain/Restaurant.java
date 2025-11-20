package domain;

import java.io.Serializable;
import java.util.List;
import domain.Customer;

public class Restaurant implements Serializable{
    
    //information of restaurant//
    private String name;

    //Lists
    private List <MenuItem> dishes;
    private List <Table> tables;
    private List <Order> orders;
    private List <Customer> customers;
    
    //constructor//
    public Restaurant(String name){
        setName(name);
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
    //
    public Customer findCustomerById(int customerIdNumber){
        for (Customer customer : customers){
            if(customerIdNumber == customer.getCustomerIdNumber()){
                return customer;
            }
        }
        return null;
    }
    public Order findOrderById(int idOrder){
        for(Order order:orders){
            if (idOrder == order.getIdOrder()){
                return order;
            }
        }
        return null;
    }
    public Table findTableById(int tableNumber){
        for(Table table:tables){
            if(tableNumber == table.getTableNumber()){
                return table;
            }
        }
        return null;
    }
    public MenuItem findMenuItemById(int idItem){
        for(MenuItem dishe : dishes){
            if(idItem == dishe.getIdItem()){
                return dishe;
            }
        }
        return null;
    }
    

}

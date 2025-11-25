package domain;

import java.io.Serializable;
import java.util.ArrayList;

public class Order implements Serializable{
    
    //variables//
    private int idOrder;
    private ArrayList<MenuItem> menuItems;
    private Customer customer;
    private Table table;

    //constructor//
    public Order(int idOrder, ArrayList<MenuItem> menuItems, Customer customer, Table table){
        this.idOrder = idOrder;
        this.menuItems = menuItems;
        this.customer = customer;
        this.table = table;
    }

    //setters//
    public void setIdOrder(int idOrder){
        this.idOrder = idOrder;
    }
    public void setMenuItems(ArrayList<MenuItem> menuItems){
        this.menuItems = menuItems;
    }
    public void setCustomer(Customer customer){
        this.customer = customer;
    }
    public void setTable(Table table){
        this.table = table;
    }

    //getters//
    public int getIdOrder(){
        return idOrder;
    }
    public ArrayList<MenuItem> getMenuItems(){
        return menuItems;
    }
    public Customer getCustomer(){
        return customer;
    }
    public Table getTable(){
        return table;
    }

    //metods for add/remove in the list//
    public void addMenuItem(MenuItem item){
        menuItems.add(item);
    }
    public void removeMenuitem(MenuItem item){
        menuItems.remove(item);
    }
}

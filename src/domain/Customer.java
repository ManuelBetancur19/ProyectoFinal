package domain;

import java.io.Serializable;

public class Customer implements Serializable {

    // variables
    private static int counter = 1;
    private int id;
    private int purchases = 0;
    private String name;
    private String identificationNumber;
    private Table assignedTable;

    // constructor
    public Customer(String name, String identificationNumber) {
        this.id = counter++;
        this.name = name;
        this.identificationNumber = identificationNumber;
    }

    // setters
    public void setAssignedTable(Table assignedTable) {
        this.assignedTable = assignedTable;
    }

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

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public Table getAssignedTable() {
        return assignedTable;
    }

    public int getPurchases() {
        return purchases;
    }

    @Override
    public String toString() {
        return "Customer ( id=" + id + ", name='" + name + '\'' + ", identificationNumber='" + identificationNumber
                + '\'' + ", table=" + (assignedTable != null ? assignedTable.getId() : "None") + " )";
    }
    
    //increases the number of purchases a customer makes
    public void incrementPurchases() {
        purchases++;
    }

}

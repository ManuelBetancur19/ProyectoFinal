package domain;

import java.io.Serializable;

public class Customer implements Serializable {

    //information of customer//
    private String customerName;
    private int customerIdNumber;
    //MAS ATRIBUTOS

    //constructor//
    public Customer (String customerName){
        this.customerName = customerName;
    }

    //setters//
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void setCustomerIdNumber(int idNumber){
        if(idNumber > 0){
            this.customerIdNumber = idNumber;
        }
        else{
            this.customerIdNumber = 0;
        }
    }

    //getters//
    public String getCustomerName(){
        return customerName;
    }
    public int getCustomerIdNumber(){
        return customerIdNumber;
    }
}

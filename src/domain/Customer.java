package domain;

import java.io.Serializable;

public class Customer implements Serializable {

    //information of customer//
    private String customerName;
    private int idNumber;

    //constructor//
    public Customer (String customerName){
        this.customerName = customerName;
    }

    //setters//
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    public void setIdNumber(int idNumber){
        if(idNumber > 0){
            this.idNumber = idNumber;
        }
        else{
            this.idNumber = 0;
        }
    }

    //getters//
    public String getCustomerName(){
        return customerName;
    }
    public int getIdNumber(){
        return idNumber;
    }
}

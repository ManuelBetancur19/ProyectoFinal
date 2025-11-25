package domain;

import java.io.Serializable;

public class Table implements Serializable {

    //information of table//
    private int tableNumber;
    
    //constructor//
    public Table(int tableNumber){
        this.tableNumber = tableNumber;
    }

    //setter//
    public void setTableNumber(int tableNumber){
        if (tableNumber > 0){
            this.tableNumber = tableNumber;
        }else{
            this.tableNumber = 0;
        }
    }
    //getter//
    public int getTableNumber(){
        return tableNumber;
    }
}

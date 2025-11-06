package domain;
import java.io.Serializable;
public class Table implements Serializable {
    private int tableNumber;
    
    public Table(int tableNumber){
        this.tableNumber = tableNumber;
    }

    public void setId(int tableNumber){
        if (tableNumber > 0){
            this.tableNumber = tableNumber;
        }else{
            this.tableNumber = 0;
        }
    }
    
    public int getId(){
        return tableNumber;
    }
}

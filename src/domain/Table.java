package domain;

import java.io.Serializable;

public class Table implements Serializable {

    // variable
    private static int counter = 1;
    private int id;
    private int seats;
    private boolean occupied;

    // constructor
    public Table(int seats) {
        this.id = counter++;
        this.seats = seats;
        this.occupied = false;
    }

    // setters
    public void setOccupied(boolean o) {
        occupied = o;
    }
    public static void setCounter(int value){
        counter = value;
    }

    // getters
    public int getId() {
        return id;
    }

    public int getSeats() {
        return seats;
    }

    public boolean isOccupied() {
        return occupied;
    }

    @Override
    public String toString() {
        return "Table ( id=" + id +
                ", seats=" + seats +
                ", occupied=" + occupied +
                " )";
    }
}

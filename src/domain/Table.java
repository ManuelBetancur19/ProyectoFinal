package domain;

public class Table {
    private static int counter = 1;
    private int id;
    private int seats;
    private boolean occupied;

    public Table(int seats) {
        this.id = counter++;
        this.seats = seats;
        this.occupied = false;
    }

    public int getId() { return id; }
    public int getSeats() { return seats; }
    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean o) { occupied = o; }

    @Override
    public String toString() {
        return "Table ( id=" + id +
               ", seats=" + seats +
               ", occupied=" + occupied +
               " )";
    }
}

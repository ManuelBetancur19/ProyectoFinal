package domain;

public class Customer {
    private static int counter = 1;
    private int id;  
    private String name;
    private String identificationNumber; // ← antes era phone
    private Table assignedTable;

    public Customer(String name, String identificationNumber) {
        this.id = counter++;
        this.name = name;
        this.identificationNumber = identificationNumber;
    }

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

    public void setAssignedTable(Table assignedTable) {
        this.assignedTable = assignedTable;
    }

    @Override
    public String toString() {
        return "Customer ( id=" + id +
               ", name='" + name + '\'' +
               ", identificationNumber='" + identificationNumber + '\'' +
               ", table=" + (assignedTable != null ? assignedTable.getId() : "None") +
               " )";
    }
}

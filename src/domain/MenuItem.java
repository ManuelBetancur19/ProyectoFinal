package domain;

import java.io.Serializable;

public class MenuItem implements Serializable{
    //Variables
    private int idItem;
    private double priceItem;
    private String nameItem;
    private String descriptionItem;
    private String categoryItem;
    private boolean availableItem;
    //Constructors
    public MenuItem(int idItem, double priceItem, String nameItem, String descriptionItem, String categoryItem, boolean availableItem){
        setAvailableItem(availableItem);
        setCategoryItem(categoryItem);
        setDescriptionItem(descriptionItem);
        setIdItem(idItem);
        setNameItem(nameItem);
        setPriceItem(priceItem);
    }
    public MenuItem(){
        idItem = 1;
        priceItem = 10000;
        nameItem = "Sin nombrar";
        descriptionItem = "Sin descripcion";
        categoryItem = "Sin Categoria";
        availableItem = false;
    }
    //Setters
    public void setIdItem(int idItem){
        if(idItem <= 0){
            this.idItem = 1;
        }else{
            this.idItem = idItem;
        }
    }
    public void setPriceItem(double priceItem){
        if(priceItem <= 0){
            this.priceItem = 1000;
        }else{
            this.priceItem = priceItem;
        }
    }
    public void setNameItem(String nameItem){
        if(nameItem.isEmpty()){
            this.nameItem = "Desconocido";
        }else{
            this.nameItem = nameItem;
        }
    }
    public void setDescriptionItem(String descriptionItem){
        if(descriptionItem.isEmpty()){
            this.descriptionItem = "Sin descripción";
        }else{
            this.descriptionItem = descriptionItem;
        }
    }
    public void setCategoryItem(String categoryItem){
        if(categoryItem.isEmpty()){
            this.categoryItem = "Desconocido";
        }else{
            this.categoryItem = categoryItem;
        }
    }
    public void setAvailableItem(boolean availableItem){
        if(availableItem != false && availableItem != true){
            this.availableItem = false;
        }
    }
    //getters
    public int getIdItem(){
        return idItem;
    }
    public double getPriceItem(){
        return priceItem;
    }
    public String getNameItem(){
        return nameItem;
    }
    public String getDescriptionItem(){
        return descriptionItem;
    }
    public String getCategoryItem(){
        return categoryItem;
    }
    public boolean getAvailableItem(){
        return availableItem;
    }
}

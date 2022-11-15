package models;

import java.util.Objects;

public class Item {

  private String name;
  private double price;


    public Item(String name, double price) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name can not ne null or blank!");
        }
        if (price < 0){
            throw new IllegalArgumentException("Price can not be less than zero!");
        }
        this.name = name;
        this.price = price;
    }

    public Item(Item source) {
        this.name = source.name;
        this.price = source.price;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name can not ne null or blank!");
        }
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        if (price < 0){
            throw new IllegalArgumentException("Price can not be less than zero!");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name + ": $" + this.price + "   ";
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (!(o instanceof Item)) {
            return false;
        }
        Item item = (Item) o;
        return this.name.equals(item.name) && this.price == item.price;
    }

    
    

}

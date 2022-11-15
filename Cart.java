package models;

import java.util.ArrayList;

public class Cart {

    private ArrayList<Item> items;

    public Cart() {
        this.items = new ArrayList<Item>();
    }

    public Item getItem(int index) {
        return new Item(this.items.get(index));
    }

    public void setItem(Item item, int index) {
        this.items.set(index, new Item(item));
    }

    public boolean add(Item item) {

        if (this.items.contains(item)) {
            return false;
        }
        this.items.add(new Item(item));
        return true;
    }

    public void remove(String name) {
        if (this.items.isEmpty()){
            throw new IllegalStateException("Can not remove items from empty cart.");
        }
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                this.items.remove(i);
            }
        }
    }

    public boolean isEmpty(){
        if (this.items.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public String checkout() {
        if (this.items.isEmpty()){
            throw new IllegalStateException("Can not checkout an empty cart.");
        }
        double subtotal = 0;
        for (int i = 0; i < this.items.size(); i++) {
            subtotal += this.items.get(i).getPrice();
        }

        double tax = subtotal * 0.13;
        double total = subtotal + tax;

        return "\tRECEIPT\n\n" +
                "\tSubtotal: $" + subtotal + "\n" +
                "\tTax: $" + tax + "\n" +
                "\tTotal: $" + total + "\n";
    }

    @Override
    public String toString() {
        String temp = "";
        for (int i = 0; i < this.items.size(); i++) {
            temp += this.items.get(i).toString();
            temp += "\n";
        }
        return temp;
    }


}

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import models.Cart;
import models.Item;
import models.Store;

public class Main {
    static Store store = new Store();
    static Cart cart = new Cart();

    public static void main(String[] args) {

        try {
            loadItems("products.txt");
            manageItems();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    
    
    }

    static public void manageItems(){
        Scanner scan = new Scanner(System.in);
        while (true){
            System.out.println("\n\t******************************JAVA GROCERS******************************\n");
            System.out.println(store);
            System.out.println("choose to \n\ta) add  \n\tb) remove \n\tc) checkout");
            String decision = scan.nextLine();

            
            switch (decision){
                case "a":
                        System.out.print("\nChoose an aisle number between: 1 - 7: ");
                        if (!scan.hasNextInt()){
                            continue;
                        }
                        int row = scan.nextInt();
                        scan.nextLine();
                        System.out.print("Choose an item number between: 1 - 3: ");
                        if (!scan.hasNextInt()){
                            continue;
                        }
                        int column = scan.nextInt();
                        if (row < 1 || row > 7 || column < 1 || column > 3){
                            continue;
                        }
                        scan.nextLine();
                        if (cart.add(store.getItem(row-1, column-1))){
                            cart.add(new Item(store.getItem(row-1, column-1)));
                            System.out.println(store.getItem(row-1, column-1).getName() + " was added to your shopping cart.");
                        }else{
                            System.out.println(store.getItem(row-1, column-1).getName() + " is already in your shopping cart.");
                        }
                        break;
                case "b":
                        System.out.print("Enter the item you'd like to remove: ");
                        String name = scan.nextLine();
                        if (cart.isEmpty()){
                            continue;
                        }
                        cart.remove(name);
                        break;
                case "c":
                        if (cart.isEmpty()){
                            continue;
                        }
                        System.out.println(cart.checkout());
                        scan.close();
                        return;
                default: continue;
                
            }
            System.out.println("\n\nSHOPPING CART\n\n" + cart);
            System.out.print("Enter anything to continue: ");
            scan.nextLine();
        }
    }

    /**
     * Name: manageItems
     * Inside the function:
     *   • 1. Starts a new instance of Scanner;
     *   • 2. Creates an infinite loop:     
     *   •        The user can choose to a) add or b) remove c) checkout.
     *   •          case a: asks for the aisle and item number. Then, adds item to cart.
     *   •          case b: asks for the name. Then, removes item from cart.
     *   •          case c: prints the receipt and closes Scanner.
     *   •        Prints the updated shopping cart.
     * @throws FileNotFoundException
     */
 

     static public void loadItems(String filename) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(filename);
        Scanner scan = new Scanner(fis);

        for (int i=0; scan.hasNextLine(); i++){
            String line = scan.nextLine();
            String [] parts = line.split(";");
            for(int j=0; j<parts.length; j++){
                String[] words = parts[j].split("=");
                store.setItems(i, j, new Item(words[0], Double.parseDouble(words[1])));
            }
        }
        scan.close();
     }
  

}

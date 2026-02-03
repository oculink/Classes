import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantOrderSystem {
    
    static class MenuItem {
        String name;
        double price;
        
        MenuItem(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }
    
    static class Order {
        ArrayList<MenuItem> items = new ArrayList<>();
        
        void addItem(MenuItem item) {
            items.add(item);
        }
        
        double getTotal() {
            double total = 0;
            for (MenuItem item : items) {
                total += item.price;
            }
            return total;
        }
        
        void displayOrder() {
            System.out.println("\n=== YOUR ORDER ===");
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d. %s - RM %.2f\n", 
                    i + 1, items.get(i).name, items.get(i).price);
            }
            System.out.printf("\nTOTAL: RM %.2f\n", getTotal());
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();
        
        MenuItem[][] menu = {
            {
                new MenuItem("Nasi Lemak", 2.50),
                new MenuItem("Nasi Ayam", 6.00),
                new MenuItem("Mee Goreng", 7.50),
                new MenuItem("Roti Canai", 1.50)
            },
            {
                new MenuItem("Char Kuey Teow", 5.00),
                new MenuItem("Wan Tan Mee", 5.50),
                new MenuItem("Fried Rice", 6.00),
                new MenuItem("Dim Sum Set", 8.00)
            },
            {
                new MenuItem("Burger", 12.00),
                new MenuItem("Fries", 6.00),
                new MenuItem("Chicken Chop", 15.00),
                new MenuItem("Fish & Chips", 16.00)
            },
            {
                new MenuItem("Teh O", 2.50),
                new MenuItem("Milo", 3.50),
                new MenuItem("Sirap", 2.00),
                new MenuItem("Limau", 3.00)
            },
            {
                new MenuItem("Telur Goreng", 1.50),
                new MenuItem("Extra Rice", 1.50),
                new MenuItem("Coleslaw", 2.00),
                new MenuItem("Soup", 4.00),
                new MenuItem("Ayam Goreng", 4.50),
                new MenuItem("Sambal", 0.50)
            }
        };
        
        String[] categories = {
            "Malaysian Cuisine",
            "Chinese Cuisine", 
            "Western",
            "Beverage",
            "Add On (Ala Carte)"
        };
        // WHY ARE YOU LOOKING AT THIS CODE?!! WHY ARE YOU READING THIS??!! - oculink
        System.out.println("======================================");
        System.out.println("   WELCOME TO RESTAURANT ORDER SYSTEM");
        System.out.println("======================================\n");
        
        
        System.out.print("Enter table number: ");
        int tableNumber = sc.nextInt();
        System.out.println("\nTable " + tableNumber + " confirmed!");
        
        boolean addMore = true;
        
        while (addMore) {
            
            System.out.println("\n=== SELECT CATEGORY ===");
            for (int i = 0; i < categories.length; i++) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
            System.out.print("\nEnter category (1-" + categories.length + "): ");
            int categoryChoice = sc.nextInt() - 1;
            
            if (categoryChoice >= 0 && categoryChoice < categories.length) {
                
                System.out.println("\n=== " + categories[categoryChoice].toUpperCase() + " ===");
                for (int i = 0; i < menu[categoryChoice].length; i++) {
                    System.out.printf("%d. %s - RM %.2f\n", 
                        i + 1, 
                        menu[categoryChoice][i].name, 
                        menu[categoryChoice][i].price);
                }
                
                System.out.print("\nSelect item (1-" + menu[categoryChoice].length + "): ");
                int itemChoice = sc.nextInt() - 1;
                
                if (itemChoice >= 0 && itemChoice < menu[categoryChoice].length) {
                    order.addItem(menu[categoryChoice][itemChoice]);
                    System.out.println("\n✓ " + menu[categoryChoice][itemChoice].name + " added!");
                } else {
                    System.out.println("\nInvalid item selection!");
                }
            } else {
                System.out.println("\nInvalid category selection!");
            }
            
            
            System.out.print("\nAdd more items? (Y/N): ");
            String response = sc.next();
            addMore = response.equalsIgnoreCase("Y");
        }
        
        
        order.displayOrder();
        
        
        System.out.println("\n=== PAYMENT METHOD ===");
        System.out.println("1. Cash");
        System.out.println("2. QR Payment");
        System.out.println("3. Online Payment");
        System.out.print("\nSelect payment method (1-3): ");
        int payment = sc.nextInt();
        
        String paymentMethod = "";
        switch (payment) {
            case 1: paymentMethod = "Cash"; break;
            case 2: paymentMethod = "QR Payment"; break;
            case 3: paymentMethod = "Online Payment"; break;
            default: paymentMethod = "Cash";
        }
        
        System.out.println("\n======================================");
        System.out.println("   ORDER COMPLETED!");
        System.out.println("   Table: " + tableNumber);
        System.out.println("   Payment: " + paymentMethod);
        System.out.printf("   Total: RM %.2f\n", order.getTotal() + (order.getTotal()*6/100));
        System.out.println("   Thank you for your order!");
        System.out.println("======================================");
        
        sc.close();
    }
}
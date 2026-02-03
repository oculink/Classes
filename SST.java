import java.util.Scanner;

public class SST {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;
        
        do {
            System.out.print("Item name: ");
            String name = sc.nextLine();
            
            System.out.print("Item price: RM ");
            double price = sc.nextDouble();
            
            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            
            System.out.println("\nDiscount Menu:");
            System.out.println("1. No Discount (0%)");
            System.out.println("2. 5% Discount");
            System.out.println("3. 10% Discount");
            System.out.println("4. 15% Discount");
            System.out.println("5. 20% Discount");
            System.out.print("Select discount (1-5): ");
            int discountOption = sc.nextInt();
            
            double discount = 0;
            switch (discountOption) {
                case 1: discount = 0; break;
                case 2: discount = 0.05; break;
                case 3: discount = 0.10; break;
                case 4: discount = 0.15; break;
                case 5: discount = 0.20; break;
                default: discount = 0;
            }
            
            double subtotal = price * qty;
            double afterDiscount = subtotal - (subtotal * discount);
            double sst = afterDiscount * 0.06;
            double total = afterDiscount + sst;
            
            System.out.println("\n--- Receipt ---");
            System.out.println("Item: " + name);
            System.out.println("Price: RM " + price + " x " + qty);
            System.out.println("Subtotal: RM " + subtotal);
            System.out.println("Discount: " + (discount * 100) + "%");
            System.out.println("After Discount: RM " + afterDiscount);
            System.out.println("SST (6%): RM " + sst);
            System.out.println("Total: RM " + total);
            
            System.out.print("\nContinue? (Y/N): ");
            sc.nextLine();
            choice = sc.nextLine();
            System.out.println();
            
        } while (choice.equalsIgnoreCase("Y"));
        
        System.out.println("Thank you!");
        sc.close();
    }
}
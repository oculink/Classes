import java.util.Scanner;
import java.io.IOException;

public class oddeven {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.print("Enter a number: ");
            String input = scan.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number % 2 == 0) {
                    System.out.println(number + " is EVEN");
                } else {
                    System.out.println(number + " is ODD");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
            System.out.println();
            System.out.print("want to (R)epeat or (E)xit?: ");
            String choose = scan.nextLine();
            if (choose.equalsIgnoreCase("R")) {
                System.out.println();
            } else if (choose.equalsIgnoreCase("E")) {
                break;
            } else {
                System.out.println();
                System.out.println("Huh?!??!!?!?");
                try {
                    String[] cmd = {"hyprctl", "dispatch", "exit"}; // ONLY WORKS ON ARCH HYPRLAND -oculink
                    String[] cmd2 = {"shutdown", "/l"};
                    Thread.sleep(1000);
                    System.out.println("what are you");
                    Thread.sleep(500);
                    System.out.print("SAYING??!!");
                    Thread.sleep(500);
                    System.out.println("...");
                    Thread.sleep(500);
                    System.out.println("goodbye...");
                    Thread.sleep(2000);
                    Runtime.getRuntime().exec(cmd);
                    Runtime.getRuntime().exec(cmd2);
                } catch (InterruptedException | IOException e) {
                    System.out.println(e);
                }
                break;
            }
        }
        scan.close();
    }
}
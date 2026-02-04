public class az {
    public static void main(String[] args) {
        try {
            char letter = 'Z';
            while (letter >= 'A' ) {
                System.out.println(letter);
                Thread.sleep(1000); // change this number for delay -oculink
                letter--;
            }
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
    
}

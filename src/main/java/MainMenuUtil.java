import java.util.Scanner;

public class MainMenuUtil {
    public static int getChoice() {
        Scanner keyboard = new Scanner(System.in);
        int choice = -1;
        boolean valid = false;

        while (!valid) {
            System.out.println("Enter your choice");
            String input = keyboard.nextLine();

            try {
                choice = Integer.parseInt(input);

                if (choice >= 0 && choice < 3) {
                    valid = true;
                } else {
                    System.out.println("Invalid choice, try again.");
                }

            } catch (Exception e) {
                System.out.println("Invalid input, enter a number.");
            }
        }

        return choice;
    }


    public static void displayMenu(String[] menuOptions){
        System.out.println("""
        |========|
        |  MENU  |
        |========|
        """);
        for (String option : menuOptions)
        {
            System.out.println(option);
        }
    }
    public static String handleKeyGuess ()
    {
        Scanner  keyboard = new Scanner(System.in);
        boolean isRunning = true;
        int attemptCount=3;
        System.out.println("You may only attempt to enter decryption key 3 times before you get kicked out.");
        while(isRunning && attemptCount>0) {
            System.out.println("Enter key");
            String key = keyboard.nextLine();
            if (!key.equals(EncyrptionUtil.getKey())) {
                attemptCount--;
                System.out.println("You have " + attemptCount + " attempts left.");
                if(attemptCount == 0) {
                    return null;
                }
            }
            else{

               return key;
            }
        }
        return null; //backup , prevents crashes
    }
}

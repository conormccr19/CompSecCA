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

            } catch (NumberFormatException e) {
                System.out.println("Invalid input, enter a number.");
            }
        }

        return choice;
    }


    public static void displayMenu(String[] menuOptions,String menuTitle){
        System.out.println(menuTitle);
        System.out.println("Choose one");
        for (String option : menuOptions)
        {
            System.out.println(option);
        }
    }

}

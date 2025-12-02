import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the main menu");
        System.out.println("Options:");
        Scanner input = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
        String[] menuOptions = {
                "(0) Exit",
                "(1) Encrypt File",
                "(2) Decrypt File"
        };

            MainMenuUtil.displayMenu(menuOptions,"Menu");
        int menuChoice = MainMenuUtil.getChoice();
        switch (menuChoice)
        {
            case 1:
                System.out.println("1");
                System.out.println("Encrypt File Name");
                String fileName = input.nextLine();
                System.out.println(EncyrptionUtil.findTextFile(fileName));
                    isRunning = false;
                    break;

                case 2:
                    System.out.println("2");
                    isRunning = false;
                    break;

            case 0:
                System.out.println("0");
                isRunning = false;
                break;

                default:
                    System.out.println("Invalid choice");
                    break;
        }



        }


    }
}

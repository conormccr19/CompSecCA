import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) {
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

import java.io.*;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the main menu");
        System.out.println("Options:");
        Scanner input = new Scanner(System.in);
        String encryptedText;
        int key;
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
                System.out.println("Encrypt File Name");
                String fileName = input.nextLine();

                String filePath = EncyrptionUtil.findTextFile(fileName);
                File encryptionFile = new File(EncyrptionUtil.findTextFile(fileName));

                String preCipherText = "";

                    preCipherText+=encryptionFile;

                System.out.println(preCipherText);


                encryptedText = EncyrptionUtil.encrypt(preCipherText);

                System.out.println("enter file target");
                String fileTarget = input.nextLine();

                BufferedWriter writer = new BufferedWriter(new FileWriter(fileTarget));
                writer.write(encryptedText);
                writer.close();//writing to file code adapted from https://www.baeldung.com/java-write-to-file, particularly BufferedWriter object & import.

                System.out.println(encryptedText);
                System.out.println("The Encryption key is " + EncyrptionUtil.getKey());

                    break;

                case 2:
                    System.out.println("2");
                    System.out.println("Enter text to decrypt");
                    String dFileName = input.nextLine();
                    String dFilePath = EncyrptionUtil.findTextFile(dFileName);
                    System.out.println(EncyrptionUtil.decrypt(dFilePath));
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

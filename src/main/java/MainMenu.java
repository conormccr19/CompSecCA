import java.io.*;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws IOException {

        System.out.println("Welcome to the main menu");
        System.out.println("Options:");
        Scanner input = new Scanner(System.in);
        String encryptedText;
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


                String fileTarget = "ciphertext.txt";



                BufferedWriter writer = new BufferedWriter(new FileWriter(fileTarget));
                writer.write(encryptedText);
                writer.close();//writing to file code adapted from https://www.baeldung.com/java-write-to-file.

                System.out.println(encryptedText);
                System.out.println("The Encryption key is " + EncyrptionUtil.getKey());

                    break;

            case 2:
                boolean correct = false;
                String dFilePath = "";
                String dFileTarget="plaintext.txt";
                while (!correct) {
                    System.out.println("Enter filename to decrypt or 0 to cancel:");
                    String dFileName = input.nextLine();

                    if (dFileName.equals("0")) {
                        System.out.println("Returning to main menu.");
                        break; // exit loop
                    }

                    dFilePath = EncyrptionUtil.findTextFile(dFileName);

                    if (!dFilePath.isEmpty()) {
                        correct=true;
                    }
                }

                // asks for key when file accepted
                if (correct) {
                    String keyG = MainMenuUtil.handleKeyGuess();
                    if (keyG!=null) {
                        String decryptedText = EncyrptionUtil.decrypt(dFilePath, keyG);
                        if (!decryptedText.isEmpty()) {
                            BufferedWriter dwriter = new BufferedWriter(new FileWriter(dFileTarget));
                            dwriter.write(decryptedText);
                            dwriter.close();//writing to file code adapted from https://www.baeldung.com/java-write-to-file.
                            System.out.println("Decrypted text can be found at: " +dFileTarget );
                        } else {
                            System.out.println("Decryption failed.");
                        }
                    } else {
                        System.out.println("Failed to provide the correct key. Returning to main menu.");
                    }
                }
                break;

            case 3:
                EncyrptionUtil.generateKey();
            case 0:
                System.out.println("Exiting.");
                isRunning = false;
                break;

                default:
                    System.out.println("Invalid choice");
                    break;
        }



        }


    }
}

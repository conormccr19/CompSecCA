import java.io.*;
import java.util.Scanner;

public class MainMenu {
    public static void main(String[] args) throws IOException {


        Scanner input = new Scanner(System.in);
        String encryptedText;
        boolean isRunning = true;

        while (isRunning) {
        String[] menuOptions = {
                "(0) EXIT",
                "(1) ENCRYPT FILE",
                "(2) DECRYPT FILE"
        };

            MainMenuUtil.displayMenu(menuOptions);
        int menuChoice = MainMenuUtil.getChoice();
        switch (menuChoice)
        {
            case 1:
                boolean eCorrect =false;
                String eFileContents="";
                String eFileTarget = "ciphertext.txt";
                String eFileName="";
                while(!eCorrect)
                {
                    System.out.println("Enter filename to encrypt or 0 to cancel:");
                     eFileName = input.nextLine();

                    if(eFileName.equals("0"))
                    {
                        System.out.println("Returning to main menu.");
                        break;//exit loop
                    }
                    eFileContents=EncyrptionUtil.findTextFile(eFileName);

                    if(!eFileContents.isEmpty())
                    {
                        eCorrect=true;
                    }
                }

                System.out.println("PlainText: "+ eFileContents);
                encryptedText = EncyrptionUtil.encrypt(eFileContents);

                BufferedWriter writer = new BufferedWriter(new FileWriter(eFileTarget));
                writer.write(encryptedText);
                writer.close();//writing to file code adapted from https://www.baeldung.com/java-write-to-file.

                System.out.println("The cipher text can be found in: " + eFileTarget);
                System.out.println("The Encryption key is: " + EncyrptionUtil.getKey());

                    break;

            case 2:
                boolean dCorrect = false;
                String dFilePath = "";
                String dFileTarget="plaintext.txt";
                while (!dCorrect) {
                    System.out.println("Enter filename to decrypt or 0 to cancel:");
                    String dFileName = input.nextLine();

                    if (dFileName.equals("0")) {
                        System.out.println("Returning to main menu.");
                        break; // exit loop
                    }

                    dFilePath = EncyrptionUtil.findTextFile(dFileName);

                    if (!dFilePath.isEmpty()) {
                        dCorrect=true;
                    }
                }

                // asks for key when file accepted
                if (dCorrect) {
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

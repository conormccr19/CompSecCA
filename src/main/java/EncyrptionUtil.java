import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EncyrptionUtil {
    public static String findTextFile(String filename) throws FileNotFoundException {
        filename+=".txt";
        File file = new File(filename);

       try {
           Scanner fileScanner = new Scanner(file);
           String text = "";
           while (fileScanner.hasNextLine()) {
               text += fileScanner.nextLine();
           }
           return text;
       }
       catch (FileNotFoundException e) {
           System.out.println("Error: " + filename + " not found.");
           return "";
       }
    }
}

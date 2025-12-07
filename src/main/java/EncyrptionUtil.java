import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;
import java.util.Scanner;

//encrypt and decrypt logic adapted from https://www.geeksforgeeks.org/java/what-is-java-aes-encryption-and-decryption/ taken from MOODLE NOTES LINk

public class EncyrptionUtil {
    private static  String SecretKey = generateKey();

    private static final String SALT = "ConorMcCracken";

    public static String encrypt(String stringToEncrypt)
    {
    try {
            byte[]iv=  { 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };//default array of bytes
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(SecretKey.toCharArray(),SALT.getBytes(),65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher =Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);

        return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes(StandardCharsets.UTF_8)));

        }
    catch(Exception e)
        {
            System.out.println("An error has occured in the encryption process");
        }
    return(null);
    }
    public static String decrypt (String stringToDecrypt,String keyInput)
    {
        try
        {
            byte[] iv ={ 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };

            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(keyInput.toCharArray(),SALT.getBytes(),65536, 256);

            SecretKey tmp = factory.generateSecret(spec);

            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        }
        catch(Exception e)
        {
            System.out.println("An error has occured in the decryption process: ");
        }
        return(null);
    }

    public static String findTextFileContent(String filename) {
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
    public static String generateKey(){

        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 18;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString; // code for the generation of the key adapted from https://www.baeldung.com/java-random-string
    }
    public static String getKey()
    {
        return SecretKey;
    }

}

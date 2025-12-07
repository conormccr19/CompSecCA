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
import java.util.Scanner;

public class EncyrptionUtil {
    private static final String SecretKey = "CompSecCa2025";

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
            System.out.println("An error has occured in the encryption process: "+ e.toString());
        }
    return(null);
    }
    public static String decrypt (String stringToDecrypt)
    {
        try
        {
            byte[] iv ={ 0, 0, 0, 0, 0, 0, 0, 0,
                    0, 0, 0, 0, 0, 0, 0, 0 };

            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            KeySpec spec = new PBEKeySpec(SecretKey.toCharArray(),SALT.getBytes(),65536, 256);

            SecretKey tmp = factory.generateSecret(spec);

            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");


            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        }
        catch(Exception e)
        {
            System.out.println("An error has occured in the decryption process: "+ e.toString());
        }
        return(null);
    }

    public static String findTextFile(String filename) throws FileNotFoundException {
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

    public static String getKey()
    {
        return SecretKey;
    }
}

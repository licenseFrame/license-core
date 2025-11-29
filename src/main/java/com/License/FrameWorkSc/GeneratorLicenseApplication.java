package com.License.FrameWorkSc;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import static org.springframework.data.redis.serializer.RedisSerializationContext.java;

@SpringBootApplication
public class GeneratorLicenseApplication {
  // MUST MATCH THE KEY IN AppLicenseService
    private static final String SECRET_KEY = "ControlCout#2024"; 

	public static void main(String[] args) {
    try {
            // Set the expiration date you want for the client
            String expirationDate = "2025-06-01"; // YYYY-MM-DD
            
            String licenseToken = encrypt(expirationDate);
            
            System.out.println("=== GENERATED LICENSE TOKEN ===");
            System.out.println("Expire Date: " + expirationDate);
            System.out.println("Token: " + licenseToken);
            System.out.println("===============================");
            
            // Output example: "I/xxxx/yyyy..."
            // You copy this token and insert it into the client's 'app_license' table.
            
        } catch (Exception e) {
            e.printStackTrace();
        }
            
            
		SpringApplication.run(GeneratorLicenseApplication.class, args);
	
                
        }
    private static String encrypt(String strToEncrypt) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
    }

}

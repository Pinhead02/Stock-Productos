package ar.edu.udecy.web.inventory;

    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;

    import java.security.Key;

@SpringBootApplication
    public class Main {
        public static void main(String[] args) {

            SpringApplication.run(Main.class, args);

            // Generate a secure key for HS512
            Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

            // Print the key as a Base64-encoded string
            System.out.println("Generated Key: " + java.util.Base64.getEncoder().encodeToString(key.getEncoded()));

        }
    }
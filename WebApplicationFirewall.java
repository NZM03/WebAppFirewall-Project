package CyberSecurity;
import java.util.*;
import java.util.regex.*;

public class WebApplicationFirewall {

    private static final List<Pattern> threatPatterns = Arrays.asList(
            Pattern.compile(".*<script>.*", Pattern.CASE_INSENSITIVE), // Detects XSS
            Pattern.compile(".*(\\'|\\\")\\s*OR\\s*\\1.*", Pattern.CASE_INSENSITIVE), // Detects SQL injection
            Pattern.compile(".*\\b(drop|delete|truncate|alter|insert)\\b.*", Pattern.CASE_INSENSITIVE) // Detects dangerous SQL keywords
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Web Application Firewall initialized.");
        System.out.print("Enter HTTP request payload: ");
        String requestPayload = scanner.nextLine();

        if (inspectPayload(requestPayload)) {
            System.out.println("Threat detected! Request blocked.");
        } else {
            System.out.println("Request is safe. Proceeding...");
        }
    }

    private static boolean inspectPayload(String payload) {
        for (Pattern pattern : threatPatterns) {
            Matcher matcher = pattern.matcher(payload);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}

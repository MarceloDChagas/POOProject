package security;

import java.util.UUID;

public class IDGenerator {
    private static int lastProductId = 0;  // Contador estático para o último ID gerado
    public static String generateUniqueId() {
        return UUID.randomUUID().toString();
    }

    public static String generateProductId() {
        lastProductId++;
        return Integer.toString(lastProductId);
    }
}

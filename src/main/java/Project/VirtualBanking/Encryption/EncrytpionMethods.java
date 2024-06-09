package Project.VirtualBanking.Encryption;

public class EncrytpionMethods {

    public static String encryptData(String data, String encryptionKey) {
        StringBuilder encryptedData = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            encryptedData.append((char) (data.charAt(i) ^ encryptionKey.charAt(i % encryptionKey.length())));
        }

        return encryptedData.toString();
    }

    public static String decryptData(String encryptedData, String encryptionKey) {
        return encryptData(encryptedData, encryptionKey);
    }
}

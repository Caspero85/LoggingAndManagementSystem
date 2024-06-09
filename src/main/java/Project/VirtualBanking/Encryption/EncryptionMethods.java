package Project.VirtualBanking.Encryption;

public class EncryptionMethods {

    public static String encryptData(String data, String encryptionKey) {
        StringBuilder encryptedData = new StringBuilder();
        int keyLength = encryptionKey.length();

        for (int i = 0; i < data.length(); i++) {
            encryptedData.append((char) (data.charAt(i) ^ encryptionKey.charAt(i % keyLength)));
        }

        return encryptedData.toString();
    }

    public static String decryptData(String encryptedData, String encryptionKey) {
        return encryptData(encryptedData, encryptionKey);
    }
}

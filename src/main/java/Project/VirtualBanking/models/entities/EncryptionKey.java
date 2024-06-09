package Project.VirtualBanking.models.entities;

import jakarta.persistence.*;

import java.util.Random;

@Entity
public class EncryptionKey {

    @Id
    @GeneratedValue
    private Integer keyID;

    private String encryptionKey;

    @OneToOne(mappedBy = "encryptionKey", fetch = FetchType.LAZY)
    private Parent parent;

    public EncryptionKey() {
        this.encryptionKey = generateRandomKey();
    }

    public Integer getKeyID() {
        return keyID;
    }
    public void setKeyId() {
        this.encryptionKey = generateRandomKey();
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    private String generateRandomKey() {
        int keyLength = 26;
        Random random = new Random();
        StringBuilder key = new StringBuilder(keyLength);

        for (int i = 0; i < keyLength; i++) {
            key.append((char) (random.nextInt(94) + 33));
        }

        return key.toString();
    }
}

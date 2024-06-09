package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.EncryptionKey;

public class EncryptionKeyDto {

    private Integer keyID;
    private String encryptionKey;

    public EncryptionKeyDto() {
    }

    public EncryptionKeyDto(Integer keyID, String encryptionKey) {
        this.keyID = keyID;
        this.encryptionKey = encryptionKey;
    }

    public Integer getKeyID() {
        return keyID;
    }
    public void setKeyID(Integer key) {
        this.keyID = key;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }

    public static EncryptionKeyDto fromEntity(EncryptionKey encryptionKey) {
        return new EncryptionKeyDto(
            encryptionKey.getKeyID(),
            encryptionKey.getEncryptionKey()
        );
        }
}

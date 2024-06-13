package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.EncryptionKey;

public class EncryptionKeyDto {

    private Integer keyId;
    private String encryptionKey;

    public EncryptionKeyDto() {
    }

    public EncryptionKeyDto(Integer keyId, String encryptionKey) {
        this.keyId = keyId;
        this.encryptionKey = encryptionKey;
    }

    public Integer getKeyId() {
        return keyId;
    }
    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(String key) {
        this.encryptionKey = key;
    }

    public static EncryptionKeyDto fromEntity(EncryptionKey encryptionKey) {
        return new EncryptionKeyDto(
            encryptionKey.getKeyId(),
            encryptionKey.getEncryptionKey()
        );
        }
}

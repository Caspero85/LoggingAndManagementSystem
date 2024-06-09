package Project.VirtualBanking.Encryption.EncryptEntities;

import Project.VirtualBanking.Encryption.EncryptionMethods;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;

public class EncryptChild {

    public static void encryptChild(Child child, String encryptionKey) {
        child.setEmailAddress(EncryptionMethods.encryptData(child.getEmailAddress(), encryptionKey));
        child.setUsername(EncryptionMethods.encryptData(child.getUsername(), encryptionKey));
        child.setPassword(EncryptionMethods.encryptData(child.getPassword(), encryptionKey));
    }

    public static void decryptChild(ChildDto childDto, String encryptionKey) {
        childDto.setEmailAddress(EncryptionMethods.decryptData(childDto.getEmailAddress(), encryptionKey));
        childDto.setUsername(EncryptionMethods.decryptData(childDto.getUsername(), encryptionKey));
        childDto.setPassword(EncryptionMethods.decryptData(childDto.getPassword(), encryptionKey));
    }
}

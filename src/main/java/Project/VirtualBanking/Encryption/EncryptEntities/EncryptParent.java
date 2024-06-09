package Project.VirtualBanking.Encryption.EncryptEntities;

import Project.VirtualBanking.Encryption.EncryptionMethods;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.entities.Parent;

public class EncryptParent {

    public static void encryptParent(Parent parent, String encryptionKey) {
        parent.setEmailAddress(EncryptionMethods.encryptData(parent.getEmailAddress(), encryptionKey));
        parent.setPhoneNumber(EncryptionMethods.encryptData(parent.getPhoneNumber(), encryptionKey));
        parent.setPassword(EncryptionMethods.encryptData(parent.getPassword(), encryptionKey));
    }

    public static void decryptParent(ParentDto parentDto, String encryptionKey) {
        parentDto.setEmailAddress(EncryptionMethods.decryptData(parentDto.getEmailAddress(), encryptionKey));
        parentDto.setPhoneNumber(EncryptionMethods.decryptData(parentDto.getPhoneNumber(), encryptionKey));
        parentDto.setPassword(EncryptionMethods.decryptData(parentDto.getPassword(), encryptionKey));
    }
}

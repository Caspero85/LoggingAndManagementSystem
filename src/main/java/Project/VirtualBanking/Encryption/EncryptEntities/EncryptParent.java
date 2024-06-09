package Project.VirtualBanking.Encryption.EncryptEntities;

import Project.VirtualBanking.Encryption.EncrytpionMethods;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.entities.Parent;

public class EncryptParent {

    public static void encryptParent(Parent parent, String encryptionKey) {
        parent.setEmailAddress(EncrytpionMethods.encryptData(parent.getEmailAddress(), encryptionKey));
        parent.setPhoneNumber(EncrytpionMethods.encryptData(parent.getPhoneNumber(), encryptionKey));
        parent.setPassword(EncrytpionMethods.encryptData(parent.getPassword(), encryptionKey));
    }

    public static void decryptParent(ParentDto parentDto, String encryptionKey) {
        parentDto.setEmailAddress(EncrytpionMethods.decryptData(parentDto.getEmailAddress(), encryptionKey));
        parentDto.setPhoneNumber(EncrytpionMethods.decryptData(parentDto.getPhoneNumber(), encryptionKey));
        parentDto.setPassword(EncrytpionMethods.decryptData(parentDto.getPassword(), encryptionKey));
    }
}

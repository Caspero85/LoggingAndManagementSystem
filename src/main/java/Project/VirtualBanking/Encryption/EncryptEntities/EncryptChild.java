package Project.VirtualBanking.Encryption.EncryptEntities;

import Project.VirtualBanking.Encryption.EncrytpionMethods;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;

public class EncryptChild {

    public static void encryptChild(Child child, String encryptionKey) {
        child.setEmailAddress(EncrytpionMethods.encryptData(child.getEmailAddress(), encryptionKey));
        child.setSchoolEmailAddressID(EncrytpionMethods.encryptData(child.getSchoolEmailAddressID(), encryptionKey));
        child.setUsername(EncrytpionMethods.encryptData(child.getUsername(), encryptionKey));
        child.setPassword(EncrytpionMethods.encryptData(child.getPassword(), encryptionKey));
    }

    public static void decryptChild(ChildDto childDto, String encryptionKey) {
        childDto.setEmailAddress(EncrytpionMethods.decryptData(childDto.getEmailAddress(), encryptionKey));
        childDto.setSchoolEmailAddressID(EncrytpionMethods.decryptData(childDto.getSchoolEmailAddressID(), encryptionKey));
        childDto.setUsername(EncrytpionMethods.decryptData(childDto.getUsername(), encryptionKey));
        childDto.setPassword(EncrytpionMethods.decryptData(childDto.getPassword(), encryptionKey));
    }
}

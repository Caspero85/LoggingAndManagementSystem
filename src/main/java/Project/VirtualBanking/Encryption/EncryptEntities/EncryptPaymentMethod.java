package Project.VirtualBanking.Encryption.EncryptEntities;

import Project.VirtualBanking.Encryption.EncryptionMethods;
import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import Project.VirtualBanking.models.entities.PaymentMethod;

public class EncryptPaymentMethod {

    public static void encryptPaymentMethod(PaymentMethod paymentMethod, String encryptionKey) {
        paymentMethod.setCardNumber(EncryptionMethods.encryptData(paymentMethod.getCardNumber(), encryptionKey));
        paymentMethod.setCardHolderName(EncryptionMethods.encryptData(paymentMethod.getCardHolderName(), encryptionKey));
        paymentMethod.setCardHolderUsername(EncryptionMethods.encryptData(paymentMethod.getCardHolderUsername(), encryptionKey));
        paymentMethod.setExpirationDate(EncryptionMethods.encryptData(paymentMethod.getExpirationDate(), encryptionKey));
        paymentMethod.setCvv(EncryptionMethods.encryptData(paymentMethod.getCvv(), encryptionKey));
    }

    public static void decryptPaymentMethod(PaymentMethodDto paymentMethodDto, String encryptionKey) {
        paymentMethodDto.setCardNumber(EncryptionMethods.decryptData(paymentMethodDto.getCardNumber(), encryptionKey));
        paymentMethodDto.setCardHolderName(EncryptionMethods.decryptData(paymentMethodDto.getCardHolderName(), encryptionKey));
        paymentMethodDto.setCardHolderUsername(EncryptionMethods.decryptData(paymentMethodDto.getCardHolderUsername(), encryptionKey));
        paymentMethodDto.setExpirationDate(EncryptionMethods.decryptData(paymentMethodDto.getExpirationDate(), encryptionKey));
        paymentMethodDto.setCvv(EncryptionMethods.decryptData(paymentMethodDto.getCvv(), encryptionKey));
    }
}

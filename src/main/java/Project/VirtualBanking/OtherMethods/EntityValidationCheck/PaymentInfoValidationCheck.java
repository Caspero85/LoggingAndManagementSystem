package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardCvvValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardExpirationDateValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardHolderNameValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardNumberValidationCheck;
import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import Project.VirtualBanking.models.entities.Parent;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PaymentInfoValidationCheck {

    public static void saveCreditCardValidationCheck(PaymentInfoDto paymentInfoDto, Parent parent) {
        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }
        if (parent.getPaymentMethods().stream().anyMatch(paymentMethod -> paymentMethod.isActive())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodzic ma już aktywną metodę płatności");
        }

        CardNumberValidationCheck.cardNumberValidationCheck(paymentInfoDto.getCardNumber());
        CardHolderNameValidationCheck.cardHolderNameValidationCheck(paymentInfoDto.getCardHolderName());
        CardExpirationDateValidationCheck.cardExpirationDateValidationCheck(paymentInfoDto.getCardExpirationDate());
        CardCvvValidationCheck.cardCvvValidationCheck(paymentInfoDto.getCardCvv());
    }
}

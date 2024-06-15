package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.models.entities.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SubscriptionValidationCheck {

    public static void saveSubscriptionValidationCheck(
            Child child, PaymentInfo paymentInfo, SubscriptionType subscriptionType
    ) {
        Parent parent = child.getParent();
        Subscription subscription = child.getSubscriptions().stream()
                .filter(Subscription::isActive)
                .findFirst()
                .orElse(null);

        if (subscriptionType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany rodzaj subskrypcji nie istnieje");
        }
        if (!subscriptionType.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany rodzaj subskrypcji jest nieaktywny");
        }

        if (subscription != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dziecko posiada już aktywną subskrypcję");
        }

        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        if (!child.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto dziecka jest nieaktywne");
        }
        if (!child.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail dziecka nie został zweryfikowany");
        }

        if (paymentInfo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodzic nie posiada aktywnej płatności");
        }
    }

    public static void recursiveOnSubscriptionValidationCheck(
            Subscription subscription
    ) {
        Child child = subscription.getChild();
        Parent parent = child.getParent();
        SubscriptionType subscriptionType = subscription.getSubscriptionType();
        PaymentInfo paymentInfo = parent.getPaymentInfo().stream()
                .filter(PaymentInfo::isActive)
                .findFirst()
                .orElse(null);


        if (!subscription.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana subskrypcja jest nieaktywna");
        }

        if (subscriptionType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany rodzaj subskrypcji nie istnieje");
        }
        if (!subscriptionType.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany rodzaj subskrypcji jest nieaktywny");
        }

        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        if (!child.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto dziecka jest nieaktywne");
        }
        if (!child.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail dziecka nie został zweryfikowany");
        }

        if (paymentInfo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodzic nie posiada aktywnej płatności");
        }
    }
}

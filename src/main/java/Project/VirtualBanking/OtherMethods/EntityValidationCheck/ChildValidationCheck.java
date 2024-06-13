package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.EmailAddressValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.NameAndSurnameValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.PasswordValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.UsernameValidationCheck;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;
import Project.VirtualBanking.models.entities.Parent;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ChildValidationCheck {

    public static void saveChildValidationCheck(ChildDto childDto, List<Child> children, Parent parent) {
        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        for (Child childToCheck : children) {
            if (childToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już w użyciu");
            }
            if (childToCheck.getUsername().equals(childDto.getUsername())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana nazwa użytkownika jest już w użyciu");
            }
        }

        NameAndSurnameValidationCheck.nameValidationCheck(childDto.getName());
        NameAndSurnameValidationCheck.surnameValidationCheck(childDto.getSurname());
        EmailAddressValidationCheck.emailAddressValidationCheck(childDto.getEmailAddress());
        UsernameValidationCheck.usernameValidationCheck(childDto.getUsername());
        PasswordValidationCheck.passwordValidationCheck(childDto.getPassword());
    }

    public static void editChildValidationCheck(ChildDto childDto, List<Child> children, Child child, Parent parent) {
        if (!child.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto dziecka jest nieaktywne");
        }

        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        for (Child childToCheck : children) {
            if (!childToCheck.getChildId().equals(child.getChildId())) {
                if (childToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już w użyciu");
                }
                if (childToCheck.getUsername().equals(childDto.getUsername())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana nazwa użytkownika jest już w użyciu");
                }
            }
        }

        NameAndSurnameValidationCheck.nameValidationCheck(childDto.getName());
        NameAndSurnameValidationCheck.surnameValidationCheck(childDto.getSurname());
        EmailAddressValidationCheck.emailAddressValidationCheck(childDto.getEmailAddress());
        UsernameValidationCheck.usernameValidationCheck(childDto.getUsername());
        PasswordValidationCheck.passwordValidationCheck(childDto.getPassword());
    }
}

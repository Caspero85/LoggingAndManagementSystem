package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.EmailAddressValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.NameAndSurnameValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.PasswordValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.UsernameValidationCheck;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.entities.Child;
import Project.VirtualBanking.models.entities.Parent;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ChildValidationCheck {

    public static void saveChildValidationCheck(
            ChildDto childDto, List<Child> children, List<Parent> parents, Parent parent, ParentDto parentDto
    ) {
        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        for (Child childToCheck : children) {
            if (childToCheck.isActive()) {
                if (childToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już w użyciu");
                }
                if (childToCheck.getUsername().equals(childDto.getUsername())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana nazwa użytkownika jest już w użyciu");
                }
            }
        }
        for (Parent parentToCheck : parents) {
            if (parentToCheck.isActive()) {
                if (parentToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już zajęty");
                }
            }
        }

        NameAndSurnameValidationCheck.nameValidationCheck(childDto.getName());
        NameAndSurnameValidationCheck.surnameValidationCheck(childDto.getSurname());
        EmailAddressValidationCheck.emailAddressValidationCheck(childDto.getEmailAddress());
        UsernameValidationCheck.usernameValidationCheck(childDto.getUsername());
        PasswordValidationCheck.passwordValidationCheck(childDto.getPassword());
    }

    public static void editChildValidationCheck(
            ChildDto childDto, List<Child> children, List<Parent> parents, Child child, Parent parent, ParentDto parentDto
    ) {
        if (!child.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nie można edytować konta ponieważ konto jest nieaktywne");
        }
        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }

        for (Child childToCheck : children) {
            if (!childToCheck.getChildId().equals(child.getChildId()) && childToCheck.isActive()) {
                if (childToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już w użyciu");
                }
                if (childToCheck.getUsername().equals(childDto.getUsername())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana nazwa użytkownika jest już w użyciu");
                }
            }
        }

        for (Parent parentToCheck : parents) {
            if (parentToCheck.isActive()) {
                if (parentToCheck.getEmailAddress().equals(childDto.getEmailAddress())) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już zajęty");
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

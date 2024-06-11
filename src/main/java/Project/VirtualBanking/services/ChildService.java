package Project.VirtualBanking.services;
import Project.VirtualBanking.OtherMethods.EntityValidationCheck.ChildValidationCheck;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;
import Project.VirtualBanking.models.entities.EncryptionKey;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.repositories.ChildRepository;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import Project.VirtualBanking.repositories.ParentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChildService {
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final EncryptionKeyRepository encryptionKeyRepository;

    public ChildService(ChildRepository childRepository, ParentRepository parentRepository,
                        EncryptionKeyRepository encryptionKeyRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.encryptionKeyRepository = encryptionKeyRepository;
    }

    public ChildDto saveChild(ChildDto childDto, Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        List<Child> children = childRepository.findAll();

        ChildValidationCheck.saveChildValidationCheck(childDto, children, parent);

        return ChildDto.fromEntity(childRepository.save(Child.fromDto(
                childDto,
                parent,
                encryptionKeyRepository.save(new EncryptionKey())
        )));
    }

    public List<ChildDto> findAllChildren() {
        return childRepository.findAll().stream().map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList());
    }

    public List<ChildDto> findAllActiveChildren() {
        return childRepository.findAll().stream().filter(child -> child.isActive())
                .map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList());
    }

    public ChildDto findChildById(Integer childId) {
        return ChildDto.fromEntity(childRepository.findById(childId).orElseThrow());
    }

    public ChildDto editChildren(ChildDto childDto, Integer childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        List <Child> children = childRepository.findAll();
        Parent parent = child.getParent();

        ChildValidationCheck.editChildValidationCheck(childDto, children, child, parent);

        if (!child.getEmailAddress().equals(childDto.getEmailAddress())) {
            child.setEmailAddressVerified(false);
        }

        BeanUtils.copyProperties(
                childDto,
                child,
                "childId", "emailAddressVerified", "accountCreationDate", "active"
        );
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public ChildDto activateChildren(Integer childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        child.setActive(true);
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public ChildDto deactivateChildren(Integer childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        child.setActive(false);
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public ChildDto verifyEmailAddress(Integer childId) {
        Child child = childRepository.findById(childId).orElseThrow();
        child.setEmailAddressVerified(true);
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public void deleteChild(Integer childId) {
        childRepository.deleteById(childId);
    }
}

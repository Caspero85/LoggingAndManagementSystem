package Project.VirtualBanking.services;

import Project.VirtualBanking.OtherMethods.EntityValidationCheck.ParentValidationCheck;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.dtos.ParentWithChildrenDto;
import Project.VirtualBanking.models.dtos.ParentWithPaymentInfoDto;
import Project.VirtualBanking.models.entities.EncryptionKey;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import Project.VirtualBanking.repositories.ParentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParentService {
    private final ParentRepository parentRepository;
    private final EncryptionKeyRepository encryptionKeyRepository;

    public ParentService(ParentRepository parentRepository, EncryptionKeyRepository encryptionKeyRepository) {
        this.parentRepository = parentRepository;
        this.encryptionKeyRepository = encryptionKeyRepository;
    }

    public ParentDto saveParent(ParentDto parentDto){
        List<Parent> parents = parentRepository.findAll();

        ParentValidationCheck.saveParentValidationCheck(parentDto, parents);

        return ParentDto.fromEntity(parentRepository.save(Parent.fromDto(
                parentDto,
                encryptionKeyRepository.save(new EncryptionKey())
        )));
    }

    public List<ParentDto> findAllParents() {
        return parentRepository.findAll().stream().map(parent -> ParentDto.fromEntity(parent))
                .collect(Collectors.toList());
    }

    public List<ParentDto> findAllActiveParents() {
        return parentRepository.findAll().stream().filter(parent -> parent.isActive())
                .map(parent -> ParentDto.fromEntity(parent)).collect(Collectors.toList());
    }

    public ParentDto findParentById(Integer parentId) {
        return ParentDto.fromEntity(parentRepository.findById(parentId).orElseThrow());
    }

    public ParentDto editParent(ParentDto parentDto, Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        List<Parent> parents = parentRepository.findAll();

        ParentValidationCheck.editParentValidationCheck(parentDto, parents, parent);

        if (!parent.getEmailAddress().equals(parentDto.getEmailAddress())) {
            parent.setEmailAddressVerified(false);
        }

        BeanUtils.copyProperties(
                parentDto,
                parent,
                "parentId", "emailAddressVerified", "accountCreationDate", "active"
        );
        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public ParentDto verifyEmailAddress(Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.setEmailAddressVerified(true);
        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public ParentDto deactivateParent(Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.setActive(false);

        parent.getChildren().forEach(child -> {
                child.setActive(false);
                child.getSubscriptions().forEach(subscription -> {
                    subscription.setActive(false);
                    subscription.setRecursive(false);
                });
        });
        parent.getPaymentInfo().forEach(paymentInfo -> paymentInfo.setActive(false));

        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public void deleteParent(Integer parentId) {
        parentRepository.deleteById(parentId);
    }

    /**
     * Child related methods
     */

    public List<ParentWithChildrenDto> findAllParentsWithChildren() {
        return parentRepository.findAll().stream().map(parent -> ParentWithChildrenDto.fromEntity(parent))
                .collect(Collectors.toList());
    }

    public List<ParentWithChildrenDto> findAllActiveParentsWithActiveChildren() {
        List<ParentWithChildrenDto> parentsWithChildrenDto = parentRepository.findAll().stream()
                .filter(parent -> parent.isActive()).map(parent -> ParentWithChildrenDto.fromEntity(parent))
                .collect(Collectors.toList());

        parentsWithChildrenDto.forEach(
                parentWithChildrenDto -> {
                    parentWithChildrenDto.setChildrenDto(parentWithChildrenDto.getChildrenDto().stream()
                            .filter(childDto -> childDto.isActive()).collect(Collectors.toList()));
                }
        );
        return parentsWithChildrenDto;
    }

    public ParentWithChildrenDto findParentWithChildrenByParentId (Integer parentId) {
        return ParentWithChildrenDto.fromEntity(parentRepository.findById(parentId).orElseThrow());
    }

    public ParentWithChildrenDto findParentWithActiveChildrenByParentId (Integer parentId) {
        ParentWithChildrenDto parentWithChildrenDto = ParentWithChildrenDto.fromEntity(
                parentRepository.findById(parentId).orElseThrow()
        );

        parentWithChildrenDto.setChildrenDto(
                parentWithChildrenDto.getChildrenDto().stream().filter(childDto -> childDto.isActive()).collect(Collectors.toList())
        );
        return parentWithChildrenDto;
    }

    /**
     * PaymentInfo related methods
     */

    public List<ParentWithPaymentInfoDto> findAllParentsWithPaymentsInfo() {
        return parentRepository.findAll().stream()
                .map(parent -> ParentWithPaymentInfoDto.fromEntity(parent))
                .collect(Collectors.toList());
    }

    public List<ParentWithPaymentInfoDto> findAllActiveParentsWithActivePaymentsInfo() {
        List<ParentWithPaymentInfoDto> parentsWithPaymentsInfosDto = parentRepository.findAll().stream()
                .filter(parent -> parent.isActive())
                .map(parent -> ParentWithPaymentInfoDto.fromEntity(parent))
                .collect(Collectors.toList());

        parentsWithPaymentsInfosDto.forEach(
                parentWithChildrenDto -> {
                    parentWithChildrenDto.setPaymentsInfoDto(parentWithChildrenDto.getPaymentsInfoDto().stream()
                            .filter(paymentInfoDto -> paymentInfoDto.isActive()).collect(Collectors.toList()));
                }
        );
        return parentsWithPaymentsInfosDto;
    }

    public ParentWithPaymentInfoDto findParentWithPaymentsInfoByParentId(Integer parentId) {
        return ParentWithPaymentInfoDto.fromEntity(parentRepository.findById(parentId).orElseThrow());
    }

    public ParentWithPaymentInfoDto findParentWithActivePaymentsInfoByParentId(Integer parentId) {
        ParentWithPaymentInfoDto parentWithPaymentInfoDto = ParentWithPaymentInfoDto.fromEntity(
                parentRepository.findById(parentId).orElseThrow()
        );

        parentWithPaymentInfoDto.setPaymentsInfoDto(
                parentWithPaymentInfoDto.getPaymentsInfoDto().stream()
                        .filter(paymentInfoDto -> paymentInfoDto.isActive()).collect(Collectors.toList())
        );
        return parentWithPaymentInfoDto;
    }
}
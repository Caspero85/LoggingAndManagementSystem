package Project.VirtualBanking.services;

import Project.VirtualBanking.Encryption.EncryptEntities.EncryptChild;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;
import Project.VirtualBanking.models.entities.EncryptionKey;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.repositories.ChildRepository;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import Project.VirtualBanking.repositories.ParentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public ChildDto saveChild(ChildDto childDto, Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        EncryptionKey encryptionKey = encryptionKeyRepository.save(new EncryptionKey());
        Child child = childRepository.save(Child.fromDto(childDto, parent, encryptionKey));
        return ChildDto.fromEntity(child);
    }

    public List<ChildDto> findAllChildren(){
        List<ChildDto> children = childRepository.findAll().stream().map(child -> ChildDto.fromEntity(child))
                .collect(Collectors.toList());
        return children;
    }

    public List<ChildDto> findAllActiveChildren(){
        List<ChildDto> children = childRepository.findAll().stream().filter(child -> child.isActive())
                .map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList());
        return children;
    }

    public ChildDto findChildById(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        return ChildDto.fromEntity(child);
    }

    public ChildDto editChildren(ChildDto childDto, Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(childDto, child, "childID", "accountCreationDate", "active");
        EncryptChild.encryptChild(child, child.getEncryptionKey().getEncryptionKey());
        child = childRepository.save(child);
        return ChildDto.fromEntity(child);
    }

    public ChildDto activateChildren(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        child.setActive(true);
        child = childRepository.save(child);
        return ChildDto.fromEntity(child);
    }

    public ChildDto deactivateChildren(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        child.setActive(false);
        child = childRepository.save(child);
        return ChildDto.fromEntity(child);
    }

    public void deleteChild(Integer childId){
        childRepository.deleteById(childId);
    }
}

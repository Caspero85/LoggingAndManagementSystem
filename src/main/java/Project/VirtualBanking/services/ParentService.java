package Project.VirtualBanking.services;

import Project.VirtualBanking.Encryption.EncryptEntities.EncryptParent;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.dtos.ParentWithChildrenDto;
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
        return ParentDto.fromEntity(parentRepository.save(Parent.fromDto(
                parentDto,
                encryptionKeyRepository.save(new EncryptionKey()
                )
        )));
    }

    public List<ParentDto> findAllParents(){
        return parentRepository.findAll().stream().map(parent -> ParentDto.fromEntity(parent))
                .collect(Collectors.toList());
    }

    public List<ParentDto> findAllActiveParents(){
        return parentRepository.findAll().stream().filter(parent -> parent.isActive())
                .map(parent -> ParentDto.fromEntity(parent)).collect(Collectors.toList());
    }

    public ParentDto findParentById(Integer parentId){
        return ParentDto.fromEntity(parentRepository.findById(parentId).orElseThrow());
    }

    public List<ParentWithChildrenDto> findAllParentsWithChildren(){
        return parentRepository.findAll().stream()
                .map(parentWithChildren -> ParentWithChildrenDto.fromEntity(parentWithChildren))
                .collect(Collectors.toList());
    }

    public List<ParentWithChildrenDto> findAllActiveParentsWithActiveChildren(){
        return parentRepository.findAll().stream().filter(parent -> parent.isActive())
                .map(parentWithChildren -> ParentWithChildrenDto.fromEntity(parentWithChildren))
                .collect(Collectors.toList());
    }

    public ParentWithChildrenDto findParentWithChildrenByParentId (Integer parentId){
        return ParentWithChildrenDto.fromEntity(parentRepository.findById(parentId).orElseThrow());
    }

    public ParentWithChildrenDto findParentWithActiveChildrenByParentId (Integer parentId) {
        ParentWithChildrenDto parentWithChildrenDto = ParentWithChildrenDto.fromEntity(
                parentRepository.findById(parentId).orElseThrow()
        );
        parentWithChildrenDto.setChildren(
                (parentWithChildrenDto).getChildren().stream().filter(ChildDto::isActive).collect(Collectors.toList())
        );
        return parentWithChildrenDto;
    }

    public ParentDto editParent(Integer parentId, ParentDto parentDto){
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        BeanUtils.copyProperties(parentDto, parent, "parentId", "accountCreationDate", "active");
        EncryptParent.encryptParent(parent, parent.getEncryptionKey().getEncryptionKey());
        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public ParentDto activateParent(Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.setActive(true);
        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public ParentDto deactivateParent(Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.setActive(false);
        parent.getChildren().forEach(child -> child.setActive(false));
        return ParentDto.fromEntity(parentRepository.save(parent));
    }

    public void deleteParent(Integer parentId){
        parentRepository.deleteById(parentId);
    }
}
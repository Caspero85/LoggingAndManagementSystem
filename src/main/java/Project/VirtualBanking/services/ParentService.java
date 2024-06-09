package Project.VirtualBanking.services;

import Project.VirtualBanking.models.EncryptEntities.EncryptParent;
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
import java.util.NoSuchElementException;
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
        EncryptionKey encryptionKey = encryptionKeyRepository.save(new EncryptionKey());
        Parent parent = parentRepository.save(Parent.fromDto(parentDto, encryptionKey));
        return ParentDto.fromEntity(parent);
    }

    public List<ParentDto> findAllParents(){
        List<ParentDto> parents = parentRepository.findAll().stream().map(parent -> ParentDto.fromEntity(parent))
                .collect(Collectors.toList());
        return parents;
    }

    public List<ParentDto> findAllActiveParents(){
        List<ParentDto> parents =  parentRepository.findAll().stream().filter(parent -> parent.isActive())
                .map(parent -> ParentDto.fromEntity(parent)).collect(Collectors.toList());
        return parents;
    }

    public ParentDto findParentById(Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        return ParentDto.fromEntity(parent);
    }

    public List<ParentWithChildrenDto> findAllParentsWithChildren(){
        List<ParentWithChildrenDto> parentWithChildrenDtos = parentRepository.findAll().stream()
                .map(parentWithChildren -> ParentWithChildrenDto.fromEntity(parentWithChildren))
                .collect(Collectors.toList());
        return parentWithChildrenDtos;
    }

    public List<ParentWithChildrenDto> findAllActiveParentsWithActiveChildren(){
        List<ParentWithChildrenDto> parentWithChildrenDtos = parentRepository.findAll().stream()
                .filter(parent -> parent.isActive())
                .map(parentWithChildren -> ParentWithChildrenDto.fromEntity(parentWithChildren))
                .collect(Collectors.toList());
        return parentWithChildrenDtos;
    }

    public ParentWithChildrenDto findParentWithChildrenByParentId (Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        return ParentWithChildrenDto.fromEntity(parent);
    }

    public ParentWithChildrenDto findParentWithActiveChildrenByParentId (Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        ParentWithChildrenDto parentWithChildrenDto = ParentWithChildrenDto.fromEntity(parent);
        List<ChildDto> activeChildren = parentWithChildrenDto.getChildren().stream().filter(ChildDto::isActive)
                .collect(Collectors.toList());
        parentWithChildrenDto.setChildren(activeChildren);
        return parentWithChildrenDto;
    }

    public ParentDto editParent(Integer parentId, ParentDto parentDto){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(parentDto, parent, "parentId", "accountCreationDate", "active");
        EncryptParent.encryptParent(parent, parent.getEncryptionKey().getEncryptionKey());
        parentRepository.save(parent);
        return ParentDto.fromEntity(parent);
    }

    public ParentDto activateParent(Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        parent.setActive(true);
        parentRepository.save(parent);
        return ParentDto.fromEntity(parent);
    }

    public ParentDto deactivateParent(Integer parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        parent.setActive(false);
        parent.getChildren().forEach(child -> child.setActive(false));
        parentRepository.save(parent);
        return ParentDto.fromEntity(parent);
    }

    public void deleteParent(Integer parentId){
        parentRepository.deleteById(parentId);
    }
}
package Project.VirtualBanking.services;

import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.entities.Child;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.repositories.ChildRepository;
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

    public ChildService(ChildRepository childRepository, ParentRepository parentRepository) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
    }

    public ChildDto saveChild(Integer parentId, ChildDto childDto){
        Parent parent = parentRepository.findById(parentId).orElseThrow(NoSuchElementException::new);
        return ChildDto.fromEntity(childRepository.save(Child.fromDto(childDto, parent)));
    }

    public List<ChildDto> findAllChildren(){
        return childRepository.findAll().stream().map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList());
    }

    public List<ChildDto> findAllActiveChildren(){
        return childRepository.findAll().stream().filter(child -> child.isActive())
                .map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList());
    }

    public ChildDto findChildById(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        return ChildDto.fromEntity(child);
    }

    public ChildDto editChildren(Integer childId, ChildDto childDto){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        BeanUtils.copyProperties(childDto, child, "accountCreationDate", "active");
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public ChildDto activateChildren(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        child.setActive(true);
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public ChildDto deactivateChildren(Integer childId){
        Child child = childRepository.findById(childId).orElseThrow(NoSuchElementException::new);
        child.setActive(false);
        return ChildDto.fromEntity(childRepository.save(child));
    }

    public void deleteChild(Integer childId){
        childRepository.deleteById(childId);
    }
}

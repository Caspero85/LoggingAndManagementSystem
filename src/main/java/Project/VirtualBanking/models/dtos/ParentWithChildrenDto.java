package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Parent;

import java.util.List;
import java.util.stream.Collectors;

public class ParentWithChildrenDto {

    private ParentDto parentDto;
    private List<ChildDto> childrenDto;

    public ParentWithChildrenDto() {
    }

    public ParentWithChildrenDto(ParentDto parentDto, List<ChildDto> childrenDto) {
        this.parentDto = parentDto;
        this.childrenDto = childrenDto;
    }

    public ParentDto getParentDto() {
        return parentDto;
    }
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
    }

    public List<ChildDto> getChildrenDto() {
        return childrenDto;
    }
    public void setChildrenDto(List<ChildDto> childrenDto) {
        this.childrenDto = childrenDto;
    }

    public static ParentWithChildrenDto fromEntity(Parent parent){
        return new ParentWithChildrenDto(
                ParentDto.fromEntity(parent),
                parent.getChildren().stream().map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList())
        );
    }
}

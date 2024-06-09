package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Parent;

import java.util.List;
import java.util.stream.Collectors;

public class ParentWithChildrenDto {

    private ParentDto parentDto;
    private List<ChildDto> children;

    public ParentWithChildrenDto() {
    }

    public ParentWithChildrenDto(ParentDto parentDto, List<ChildDto> children) {
        this.parentDto = parentDto;
        this.children = children;
    }

    public ParentDto getParentDto() {
        return parentDto;
    }
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
    }

    public List<ChildDto> getChildren() {
        return children;
    }
    public void setChildren(List<ChildDto> children) {
        this.children = children;
    }

    public static ParentWithChildrenDto fromEntity(Parent parent){
        ParentWithChildrenDto parentWithChildren = new ParentWithChildrenDto(
                ParentDto.fromEntity(parent),
                parent.getChildren().stream().map(child -> ChildDto.fromEntity(child)).collect(Collectors.toList())
        );
        return parentWithChildren;
    }
}

package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.services.ChildService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping("parent/{parentId}/child")
    public ChildDto saveChild(@RequestBody ChildDto childDto, @PathVariable Integer parentId) {
        return childService.saveChild(childDto, parentId);
    }

    @GetMapping("/children")
    public List<ChildDto> findAllChildren() {
        return childService.findAllChildren();
    }

    @GetMapping("/children/active")
    public List<ChildDto> findAllActiveChildren() {
        return childService.findAllActiveChildren();
    }

    @GetMapping("/child/{childId}")
    public ChildDto findChildById(@PathVariable Integer childId) {
        return childService.findChildById(childId);
    }

    @PutMapping("/child/{childId}/edit")
    public ChildDto editChildren(@PathVariable Integer childId, @RequestBody ChildDto childDto) {
        return childService.editChildren(childDto, childId);
    }

    @PutMapping("/child/{childId}/activate")
    public ChildDto activateChildren(@PathVariable Integer childId) {
        return childService.activateChildren(childId);
    }

    @PutMapping("/child/{childId}/deactivate")
    public ChildDto deactivateChildren(@PathVariable Integer childId) {
        return childService.deactivateChildren(childId);
    }

    @DeleteMapping("/child/{childId}/delete")
    public void deleteChild(@PathVariable Integer childId) {
        childService.deleteChild(childId);
    }
}

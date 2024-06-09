package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.dtos.ParentWithChildrenDto;
import Project.VirtualBanking.models.dtos.ParentWithPaymentMethodsDto;
import Project.VirtualBanking.services.ParentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParentController {

    private final ParentService parentService;

    public ParentController(ParentService parentService){
        this.parentService = parentService;
    }

    @PostMapping("/parent")
    public ParentDto saveParent(@RequestBody ParentDto parentDto){
        return parentService.saveParent(parentDto);
    }

    @GetMapping("/parents")
    public List<ParentDto> findAllParents(){
        return parentService.findAllParents();
    }

    @GetMapping("/parents/active")
    public List<ParentDto> findAllActiveParents(){
        return parentService.findAllActiveParents();
    }

    @GetMapping("/parent/{parentId}")
    public ParentDto findParentById(@PathVariable Integer parentId){
        return parentService.findParentById(parentId);
    }

    @PutMapping("/parent/{parentId}/edit")
    public ParentDto editParent(@PathVariable Integer parentId, @RequestBody ParentDto parentDto){
        return parentService.editParent(parentId, parentDto);
    }

    @PutMapping("/parent/{parentId}/activate")
    public ParentDto activateParent(@PathVariable Integer parentId){
        return parentService.activateParent(parentId);
    }

    @PutMapping("/parent/{parentId}/deactivate")
    public ParentDto deactivateParent(@PathVariable Integer parentId){
        return parentService.deactivateParent(parentId);
    }

    @DeleteMapping("/parent/{parentId}/delete")
    public void deleteParent(@PathVariable Integer parentId){
        parentService.deleteParent(parentId);
    }

    /**
     * Child related endpoints
     */

    @GetMapping("/parents-with-children")
    public List<ParentWithChildrenDto> findAllParentsWithChildren() {
        return parentService.findAllParentsWithChildren();
    }

    @GetMapping("/parents-with-children/active")
    public List<ParentWithChildrenDto> findAllActiveParentsWithActiveChildren() {
        return parentService.findAllActiveParentsWithActiveChildren();
    }

    @GetMapping("/parent/{parentId}/children")
    public ParentWithChildrenDto findParentWithChildrenByParentId(@PathVariable Integer parentId){
        return parentService.findParentWithChildrenByParentId(parentId);
    }

    @GetMapping("/parent/{parentId}/children/active")
    public ParentWithChildrenDto findParentWithActiveChildrenByParentId(@PathVariable Integer parentId){
        return parentService.findParentWithActiveChildrenByParentId(parentId);
    }

    /**
     * PaymentMethod related endpoints
     */

    @GetMapping("/parents-with-payment-methods")
    public List<ParentWithPaymentMethodsDto> findAllParentsWithPaymentMethods() {
        return parentService.findAllParentsWithPaymentMethods();
    }

    @GetMapping("/parents-with-payment-methods/active")
    public List<ParentWithPaymentMethodsDto> findAllActiveParentsWithActivePaymentMethods() {
        return parentService.findAllActiveParentsWithActivePaymentMethods();
    }

    @GetMapping("/parent/{parentId}/payment-methods")
    public ParentWithPaymentMethodsDto findParentWithPaymentMethodsByParentId(@PathVariable Integer parentId) {
        return parentService.findParentWithPaymentMethodsByParentId(parentId);
    }

    @GetMapping("/parent/{parentId}/payment-methods/active")
    public ParentWithPaymentMethodsDto findParentWithActivePaymentMethodsByParentId(@PathVariable Integer parentId) {
        return parentService.findParentWithActivePaymentMethodsByParentId(parentId);
    }
}
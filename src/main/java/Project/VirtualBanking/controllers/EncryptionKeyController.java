package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.EncryptionKeyDto;
import Project.VirtualBanking.services.EncryptionKeyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EncryptionKeyController {

    private final EncryptionKeyService encryptionKeyService;

    public EncryptionKeyController(EncryptionKeyService encryptionKeyService) {
        this.encryptionKeyService = encryptionKeyService;
    }

    @GetMapping("/encryptionKeys")
    public List<EncryptionKeyDto> findAllEncryptionKeys() {
        return encryptionKeyService.findAllEncryptionKeys();
    }

}

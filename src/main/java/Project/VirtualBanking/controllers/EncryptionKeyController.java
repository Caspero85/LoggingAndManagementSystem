package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.EncryptionKeyDto;
import Project.VirtualBanking.services.EncryptionKeyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class EncryptionKeyController {

    private final EncryptionKeyService encryptionKeyService;

    public EncryptionKeyController(EncryptionKeyService encryptionKeyService) {
        this.encryptionKeyService = encryptionKeyService;
    }

    @GetMapping("/encryptionKeys")
    public ResponseEntity<?> findAllEncryptionKeys() {
        try {
            return ResponseEntity.ok(encryptionKeyService.findAllEncryptionKeys());
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }

    @GetMapping("/encryptionKey/{encryptionKeyId}")
    public ResponseEntity<?> findEncryptionKeyById(Integer encryptionKeyId) {
        try {
            return ResponseEntity.ok(encryptionKeyService.findEncryptionKeyById(encryptionKeyId));
        }
        catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getReason());
        }
    }
}



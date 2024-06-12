package Project.VirtualBanking.services;

import Project.VirtualBanking.models.dtos.EncryptionKeyDto;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EncryptionKeyService {

    private final EncryptionKeyRepository encryptionKeyRepository;

    public EncryptionKeyService(EncryptionKeyRepository encryptionKeyRepository) {
        this.encryptionKeyRepository = encryptionKeyRepository;
    }

    public List<EncryptionKeyDto> findAllEncryptionKeys() {
        return encryptionKeyRepository.findAll().stream()
                .map(encryptionKey -> EncryptionKeyDto.fromEntity(encryptionKey)).collect(Collectors.toList());
    }

    public EncryptionKeyDto findEncryptionKeyById(Integer encryptionKeyId) {
        return EncryptionKeyDto.fromEntity(encryptionKeyRepository.findById(encryptionKeyId).orElseThrow());
    }
}

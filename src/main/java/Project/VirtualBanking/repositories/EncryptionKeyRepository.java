package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.EncryptionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncryptionKeyRepository extends JpaRepository<EncryptionKey, Integer> {
}

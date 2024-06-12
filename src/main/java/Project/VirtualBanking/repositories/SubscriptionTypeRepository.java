package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.SubscriptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionTypeRepository extends JpaRepository<SubscriptionType, Integer> {
}

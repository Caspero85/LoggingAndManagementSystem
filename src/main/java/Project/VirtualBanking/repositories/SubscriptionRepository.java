package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {
}

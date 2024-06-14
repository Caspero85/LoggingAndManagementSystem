package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.SubscriptionPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPaymentRepository extends JpaRepository<SubscriptionPayment, Integer> {
}

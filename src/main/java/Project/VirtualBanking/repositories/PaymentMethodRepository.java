package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer>{
}

package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, Integer>{
}

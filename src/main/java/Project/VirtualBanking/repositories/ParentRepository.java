package Project.VirtualBanking.repositories;

import Project.VirtualBanking.models.entities.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Integer> {
    boolean existsByEmailAddress(String emailAddress);
}

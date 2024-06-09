package Project.VirtualBanking.repositories;


import Project.VirtualBanking.models.entities.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {
}
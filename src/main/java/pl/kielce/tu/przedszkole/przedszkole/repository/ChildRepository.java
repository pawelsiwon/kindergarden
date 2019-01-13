package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
}

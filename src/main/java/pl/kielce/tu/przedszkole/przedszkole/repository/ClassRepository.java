package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.Class;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
}

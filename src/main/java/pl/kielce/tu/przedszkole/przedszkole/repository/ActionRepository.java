package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
//Klasa, typ klucza głównego
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

}

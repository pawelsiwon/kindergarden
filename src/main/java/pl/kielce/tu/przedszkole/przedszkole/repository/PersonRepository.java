package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByLogin(String login);
    Optional<Person> findByLoginAndPassword(String login, String password);
}

package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;

import java.util.Date;
import java.util.List;

@Repository
public interface PresenceListRepository extends JpaRepository<PresenceListEntry, Long> {
    List<PresenceListEntry> findAllByChildClazz_Id(Long id);

    List<PresenceListEntry> findAllByChildClazz_IdAndPresenceDate(Long classId, Date presenceDate);
}

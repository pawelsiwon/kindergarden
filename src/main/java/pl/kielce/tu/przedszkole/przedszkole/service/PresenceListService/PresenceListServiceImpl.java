package pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;
import pl.kielce.tu.przedszkole.przedszkole.repository.PresenceListRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PresenceListServiceImpl implements PresenceListService {

    private final PresenceListRepository presenceListRepository;

    @Autowired
    public PresenceListServiceImpl(PresenceListRepository presenceListRepository) {
        this.presenceListRepository = presenceListRepository;
    }

    @Override
    public void addEntry(String username, PresenceListEntry presenceListEntry) {
        presenceListEntry.setDate(new Date());
        presenceListRepository.save(presenceListEntry);
    }

    @Override
    public void addEntries(String username, List<PresenceListEntry> presenceListEntries) {
        presenceListEntries.forEach(presenceListEntry -> presenceListEntry.setDate(new Date()));
        presenceListRepository.saveAll(presenceListEntries);
    }

    @Override
    public void editEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        Optional<PresenceListEntry> presenceListEntryOptional = presenceListRepository
                .findById(presenceListEntry.getId());
        if(!presenceListEntryOptional.isPresent()) {
            throw new Exception("This entry does not exist.");
        }

        PresenceListEntry presenceListEntryToEdit = presenceListEntryOptional.get();
        presenceListEntryToEdit.setPresence(presenceListEntry.getPresence());
        presenceListRepository.save(presenceListEntryToEdit);
    }

    @Override
    public void deleteEntry(String username, Long presenceListEntryId) throws Exception{
        Optional<PresenceListEntry> presenceListEntry = presenceListRepository.findById(presenceListEntryId);

        if(!presenceListEntry.isPresent()) {
            throw new Exception("This entry does not exist.");
        }

        presenceListRepository.delete(presenceListEntry.get());
    }

    @Override
    public PresenceListEntry getPresenceEntryById(String username, Long presenceListEntryId) {
        return presenceListRepository.findById(presenceListEntryId).orElse(null);
    }

    @Override
    public List<PresenceListEntry> getPresenceEntryByClassAndDate(String username, Long classId, Date date) {

        return presenceListRepository.findAllByChildClazz_IdAndDate(classId, date);
    }
}

package pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PresenceListTeacherProxy implements PresenceListService{
    private final Logger logger = Logger.getLogger(PresenceListTeacherProxy.class.getName());
    private final PresenceListServiceImpl presenceListServiceImpl;

    @Autowired
    public PresenceListTeacherProxy(PresenceListServiceImpl presenceListServiceImpl) {
        this.presenceListServiceImpl = presenceListServiceImpl;
    }

    @Override
    public void addEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        logger.info(username+" added some presence list entries.");
        presenceListServiceImpl.addEntry(username, presenceListEntry);
    }

    @Override
    public void addEntries(String username, List<PresenceListEntry> presenceListEntries) throws Exception {
        logger.info(username+" added some presence list entries.");
        presenceListServiceImpl.addEntries(username, presenceListEntries);
    }

    @Override
    public void editEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        logger.info(username+" edited some presence entry");
        presenceListServiceImpl.editEntry(username, presenceListEntry);
    }

    @Override
    public void deleteEntry(String username, Long presenceListEntryId) throws Exception {
        presenceListServiceImpl.deleteEntry(username, presenceListEntryId);
    }

    @Override
    public PresenceListEntry getPresenceEntryById(String username, Long presenceListEntryId) {
        return presenceListServiceImpl.getPresenceEntryById(username, presenceListEntryId);
    }

    @Override
    public List<PresenceListEntry> getPresenceEntryByClassAndDate(String username, Long classId, Date date) {
        return presenceListServiceImpl.getPresenceEntryByClassAndDate(username, classId, date);
    }
}

package pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;

import javax.naming.OperationNotSupportedException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PresenceListParentProxy implements PresenceListService {
    private final Logger logger = Logger.getLogger(PresenceListTeacherProxy.class.getName());
    private final PresenceListServiceImpl presenceListServiceImpl;

    @Autowired
    public PresenceListParentProxy(PresenceListServiceImpl presenceListServiceImpl) {
        this.presenceListServiceImpl = presenceListServiceImpl;
    }

    @Override
    public void addEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        throw new OperationNotSupportedException("Not possible through parent proxy.");
    }

    @Override
    public void addEntries(String username, List<PresenceListEntry> presenceListEntries) throws Exception {
        throw new OperationNotSupportedException("Not possible through parent proxy.");
    }

    @Override
    public void editEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        throw new OperationNotSupportedException("Not possible through parent proxy.");
    }

    @Override
    public void deleteEntry(String username, Long presenceListEntryId) throws Exception {
        throw new OperationNotSupportedException("Not possible through parent proxy.");
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

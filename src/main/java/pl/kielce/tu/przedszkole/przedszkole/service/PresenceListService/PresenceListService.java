package pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService;

import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;

import java.util.Date;
import java.util.List;

public interface PresenceListService {
    void addEntry(String username, PresenceListEntry presenceListEntry) throws Exception;

    void addEntries(String username, List<PresenceListEntry> presenceListEntries) throws Exception;

    void editEntry(String username, PresenceListEntry presenceListEntry) throws Exception;

    void deleteEntry(String username, Long presenceListEntryId) throws Exception;

    PresenceListEntry getPresenceEntryById(String username, Long presenceListEntryId);

    List<PresenceListEntry> getPresenceEntryByClassAndDate(String username, Long classId, Date date);
}

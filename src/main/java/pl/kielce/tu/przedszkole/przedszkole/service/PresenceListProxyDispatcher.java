package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;
import pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService.PresenceListParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService.PresenceListService;
import pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService.PresenceListServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.PresenceListService.PresenceListTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.Date;
import java.util.List;

@Service
public class PresenceListProxyDispatcher{

    private final PresenceListServiceImpl presenceListServiceImpl;
    private final PresenceListTeacherProxy presenceListTeacherProxy;
    private final PresenceListParentProxy presenceListParentProxy;
    private final PersonResolverUtils personResolverUtils;

    @Autowired
    public PresenceListProxyDispatcher(PresenceListServiceImpl presenceListServiceImpl, PresenceListTeacherProxy presenceListTeacherProxy, PresenceListParentProxy presenceListParentProxy, PersonResolverUtils personResolverUtils) {
        this.presenceListServiceImpl = presenceListServiceImpl;
        this.presenceListTeacherProxy = presenceListTeacherProxy;
        this.presenceListParentProxy = presenceListParentProxy;
        this.personResolverUtils = personResolverUtils;
    }

    private PresenceListService resolvePresenceListInterface(Person person) {
        if (person.getRole().equalsIgnoreCase("ADMIN")) return presenceListServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return presenceListTeacherProxy;
        else return presenceListParentProxy;
    }

    public void addEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .addEntry(username, presenceListEntry);
    }

    public void addEntries(String username, List<PresenceListEntry> presenceListEntries) throws Exception {
        resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .addEntries(username, presenceListEntries);
    }

    public void editEntry(String username, PresenceListEntry presenceListEntry) throws Exception {
        resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .editEntry(username, presenceListEntry);
    }

    public void deleteEntry(String username, Long presenceListEntryId) throws Exception {
        resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .deleteEntry(username, presenceListEntryId);
    }

    public PresenceListEntry getPresenceEntryById(String username, Long presenceListEntryId) {
        return resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .getPresenceEntryById(username, presenceListEntryId);
    }

    public List<PresenceListEntry> getPresenceEntryByClassAndDate(String username, Long classId, Date date) {
        return resolvePresenceListInterface(personResolverUtils.resolvePersonByLogin(username))
                .getPresenceEntryByClassAndDate(username, classId, date);
    }
}

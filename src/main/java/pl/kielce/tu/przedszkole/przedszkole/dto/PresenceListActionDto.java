package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.model.PresenceListEntry;

import java.util.Date;
import java.util.List;

@Data
public class PresenceListActionDto {
    private LoginData loginData;

    private List<PresenceListEntry> presenceListEntries;

    private PresenceListEntry presenceListEntry;

    private Long presenceListEntryId;

    private Long classId;

    private Date date;
}

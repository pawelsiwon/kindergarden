package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class PresenceListEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "presence_seq")
    private long id;

    @ManyToOne
    private Child child;

    @Temporal(TemporalType.DATE)
    private Date presenceDate;

    private enum Presence {
        ABSENT,
        PRESENT,
        LATE
    }

    @Enumerated(EnumType.STRING)
    private Presence presence;
}

package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "PRESENCE_LIST")
public class PresenceListEntry {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "person_seq")
    private long id;

    @ManyToOne
    private Child child;

    @Temporal(TemporalType.DATE)
    private Date date;

    private enum Presence {
        ABSENT,
        PRESENT,
        LATE
    }

    @Enumerated(EnumType.STRING)
    private Presence presence;
}

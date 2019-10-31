package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String text;

    private User author;

    private User executor;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpen;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClose;
}

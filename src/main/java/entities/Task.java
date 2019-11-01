package entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String text;

    private String report;

    private long rating;

    @OneToOne
    private User author;

    @OneToOne
    private User executor;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpen;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClose;
}

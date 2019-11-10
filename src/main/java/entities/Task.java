package entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    private String text;

    private String report;

    private long rating;

    private State state;

    public Task() {
    }

    public Task(String name, String text, String report, long rating, State state, User author, User executor, Date dateOpen, Date dateClose) {
        this.name = name;
        this.text = text;
        this.report = report;
        this.rating = rating;
        this.state = state;
        this.author = author;
        this.executor = executor;
        this.dateOpen = dateOpen;
        this.dateClose = dateClose;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public long getRating() {
        return rating;
    }

    public void setRating(long rating) {
        this.rating = rating;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("state", state)
                .append("author", author)
                .append("executor", executor)
                .append("dateOpen", dateOpen)
                .append("dateClose", dateClose)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        return new EqualsBuilder()
                .append(id, task.id)
                .append(rating, task.rating)
                .append(name, task.name)
                .append(text, task.text)
                .append(report, task.report)
                .append(state, task.state)
                .append(author, task.author)
                .append(executor, task.executor)
                .append(dateOpen, task.dateOpen)
                .append(dateClose, task.dateClose)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(text)
                .append(report)
                .append(rating)
                .append(state)
                .append(author)
                .append(executor)
                .append(dateOpen)
                .append(dateClose)
                .toHashCode();
    }
}

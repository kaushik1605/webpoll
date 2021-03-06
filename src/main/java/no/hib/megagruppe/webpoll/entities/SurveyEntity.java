package no.hib.megagruppe.webpoll.entities;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "survey")
@Table(schema = "webpoll", name = "survey")
public class SurveyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "date")
    private Timestamp datecreated;
    @Column(name = "deadline")
    private Timestamp deadline;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;
    @Column(name = "code")
    private String code;
    @Column(name = "active")
    private Boolean active;

    @OneToMany(targetEntity = QuestionEntity.class, cascade = CascadeType.ALL, mappedBy = "survey",
            fetch = FetchType.EAGER)
    private List<QuestionEntity> questions;

    @OneToMany(targetEntity = ResponseEntity.class, cascade = CascadeType.ALL, mappedBy = "survey",
            fetch = FetchType.EAGER)
    private List<ResponseEntity> responses;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getDateCreated() {
        return datecreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.datecreated = dateCreated;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
    }

    public List<ResponseEntity> getResponses() {
        return responses;
    }

    public void setResponses(List<ResponseEntity> responses) {
        this.responses = responses;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean getActive() {
    	boolean active = false;
    	if(deadline != null){
    		Date now = new Date();
            active = deadline.after(now);
    	}
    	return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}

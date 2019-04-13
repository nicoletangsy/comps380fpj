package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "polls")
public class Polls implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    @Column(insertable = true, updatable = false)
    private String question;
    
    @Column(insertable = true, updatable = false)
    private String option1;

    @Column(insertable = true, updatable = false)
    private String option2;
    
    @Column(insertable = true, updatable = false)
    private String option3;
    
    @Column(insertable = true, updatable = false)
    private String option4;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "poll",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollComment> pollCom = new ArrayList<>();
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "poll",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PollRespons> pollRes = new ArrayList<>();

    public Polls() {
    }
    
    public Polls(String question) {
        this.question= question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getOption1(){
        return option1;
    }
    
    public void setOption1(String option1){
        this.option1=option1;
    }
    public String getOption2(){
        return option2;
    }
    
    public void setOption2(String option2){
        this.option2=option2;
    }
    public String getOption3(){
        return option3;
    }
    
    public void setOption3(String option3){
        this.option3=option3;
    }
    public String getOption4(){
        return option4;
    }
    
    public void setOption4(String option4){
        this.option4=option4;
    }
    
    public void setPollCom(List<PollComment> pollCom){
        this.pollCom = pollCom;
    }
    
    public List<PollComment> getPollCom(){
        return pollCom;
    }
    
    public void setPollRes(List<PollRespons> pollRes){
        this.pollRes = pollRes;
    }
    
    public List<PollRespons> getPollRes(){
        return pollRes;
    }

    public void addPollCom(PollComment pollcom){
        this.pollCom.add(pollcom);
    }
    
    public void addPollRes(PollRespons pollres){
        this.pollRes.add(pollres);
    }

}
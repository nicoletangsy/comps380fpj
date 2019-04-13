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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "poll_comments")
public class PollComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Polls poll;
    
    @Column(name = "username")
    private String user;
    
    @Column(name = "content")
    private String comment;

    public PollComment(){
    }
    
    public PollComment(Polls poll,String user, String comment){
        this.poll = poll;
        this.user = user;
        this.comment = comment;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Polls getPoll() {
        return poll;
    }

    public void setPoll(Polls poll) {
        this.poll = poll;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}

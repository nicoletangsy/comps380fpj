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
@Table(name = "poll_responses")
public class PollRespons {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name="poll_id")
    private Polls poll;
    
    private String response;
    
    private String username;
    
    public PollRespons(){
    }
    
    public PollRespons(Polls poll,String response,String username){
        this.poll = poll;
        this.response = response;
        this.username = username;
    }
    
    public void setPoll(Polls poll){
        this.poll = poll;
    }
    
    public Polls getPoll(){
        return poll;
    }
    
    public void setResponse(String response){
        this.response = response;
    }
    
    public String getResponse(){
        return response;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUserName(){
        return username;
    }
}

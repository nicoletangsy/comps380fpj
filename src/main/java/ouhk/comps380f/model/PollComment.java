package ouhk.comps380f.model;

import java.util.Date;

public class PollComment {
    private int id;
    private String poll;
    private String user;
    private Date timpstamp = new Date();
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoll() {
        return poll;
    }

    public void setPoll(String poll) {
        this.poll = poll;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getTimpstamp() {
        return timpstamp;
    }

    public void setTimpstamp(Date timpstamp) {
        this.timpstamp = timpstamp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}

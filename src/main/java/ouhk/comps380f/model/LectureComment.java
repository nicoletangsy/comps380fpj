package ouhk.comps380f.model;

import java.util.Date;

public class LectureComment {
    private int id;
    private String lecture;
    private String user;
    private Date timpstamp = new Date();
    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
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

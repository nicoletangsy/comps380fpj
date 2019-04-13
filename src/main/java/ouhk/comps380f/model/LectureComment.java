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
@Table(name="lecture_comments")
public class LectureComment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(insertable = false, updatable = false)
    private String lecture_id;
    
    @ManyToOne
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;
    
    @Column(name="username")
    private String user;
    
    @Column(name="content")
    private String comment;
    
    public LectureComment(){
    }
    
    public LectureComment(Lecture lecture, String user, String comment){
        this.lecture = lecture;
        this.user = user;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
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
    
    public void setLectureId(String id){
        this.lecture_id = id;
    }
    
    public String getLectureId(){
        return lecture_id;
    }
    
}

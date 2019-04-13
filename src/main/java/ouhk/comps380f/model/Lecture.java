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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "lectures")
public class Lecture implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String title;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "lecture",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureComment> lectureCom = new ArrayList<>();

    
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "lecture",
            cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> lectureNotes = new ArrayList<>();

    public List<Attachment> getLectureNotes() {
        return lectureNotes;
    }

    public void setLectureNotes(List<Attachment> lectureNotes) {
        this.lectureNotes = lectureNotes;
    }
    
    public Lecture(){
    }
    
    public Lecture(String title){
        this.title = title;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setLectureCom(List<LectureComment> lectureCom){
        this.lectureCom = lectureCom;
    }
    
    public List<LectureComment> getLectureCom(){
        return lectureCom;
    }

    public void addLectureCom(LectureComment lectureCom){
        this.lectureCom.add(lectureCom);
    }
}

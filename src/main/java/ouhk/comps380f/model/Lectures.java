package ouhk.comps380f.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "lectures")
public class Lectures implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name="title")
    private String Title;
    
    @OneToMany(mappedBy = "lectures", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<String, LectureAttachments> lectureAttachments = new Hashtable<>();
    
    @OneToMany(mappedBy = "lectures", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL, orphanRemoval = true)
    private Map<String, TutorialAttachments> tutorialAttachments = new Hashtable<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }
    
    public LectureAttachments getLectureAttachment(String name) {
        return this.lectureAttachments.get(name);
    }

    public Collection<LectureAttachments> getLectureAttachments() {
        return this.lectureAttachments.values();
    }

    public void addLectureAttachment(LectureAttachments attachment) {
        this.lectureAttachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfLectureAttachments() {
        return this.lectureAttachments.size();
    }
    
    public void deleteLectureAttachment(LectureAttachments attachment) {
        attachment.setLecture(null);
        this.tutorialAttachments.remove(attachment);
    }
    
    public TutorialAttachments getTutorialAttachment(String name) {
        return this.tutorialAttachments.get(name);
    }

    public Collection<TutorialAttachments> getTutorialAttachments() {
        return this.tutorialAttachments.values();
    }

    public void addTutorialAttachment(TutorialAttachments attachment) {
        this.tutorialAttachments.put(attachment.getName(), attachment);
    }

    public int getNumberOfTutorialAttachments() {
        return this.tutorialAttachments.size();
    }
    
    public void deleteTutorialAttachment(TutorialAttachments attachment) {
        attachment.setLecture(null);
        this.tutorialAttachments.remove(attachment);
    }
}

package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.LectureNotFound;
import ouhk.comps380f.model.Lectures;

public interface LecturesService {

    public long createLecture(String title, List<MultipartFile> lectureAttachments, 
            List<MultipartFile> tutorialAttachments) throws IOException;

    public List<Lectures> getLectures();

    public Lectures getLecture(int id);

    public void updateLecture(int id, String title, List<MultipartFile> lectureAttachments, 
            List<MultipartFile> tutorialAttachments)
            throws IOException, LectureNotFound;

    public void delete(int id) throws LectureNotFound;

    public void deleteLectureAttachment(int lectureId, String name)
            throws AttachmentNotFound;
    
    public void deleteTutorialAttachment(int lectureId, String name)
            throws AttachmentNotFound;
}
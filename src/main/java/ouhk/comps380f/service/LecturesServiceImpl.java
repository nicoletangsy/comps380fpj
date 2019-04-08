package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.dao.LectureAttachmentsRepository;
import ouhk.comps380f.dao.LecturesRepository;
import ouhk.comps380f.dao.TutorialAttachmentsRepository;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.LectureNotFound;
import ouhk.comps380f.model.LectureAttachments;
import ouhk.comps380f.model.Lectures;
import ouhk.comps380f.model.TutorialAttachments;

@Service
public class LecturesServiceImpl implements LecturesService {

    @Resource
    private LecturesRepository lecturesRepo;

    @Resource
    private LectureAttachmentsRepository lectureAttachmentRepo;
    
    @Resource
    private TutorialAttachmentsRepository tutorialAttachmentRepo;

    @Override
    @Transactional
    public List<Lectures> getLectures() {
        return lecturesRepo.findAll();
    }

    @Override
    @Transactional
    public Lectures getLecture(int id) {
        return lecturesRepo.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = LectureNotFound.class)
    public void delete(int id) throws LectureNotFound {
        Lectures deletedLecture = lecturesRepo.findOne(id);
        if (deletedLecture == null) {
            throw new LectureNotFound();
        }
        lecturesRepo.delete(deletedLecture);
    }

    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteLectureAttachment(int lectureId, String name) throws AttachmentNotFound {
        Lectures lecture = lecturesRepo.findOne(lectureId);
        for (LectureAttachments attachment : lecture.getLectureAttachments()) {
            if (attachment.getName().equals(name)) {
                lecture.deleteLectureAttachment(attachment);
                lecturesRepo.save(lecture);
                return;
            }
        }
        throw new AttachmentNotFound();
    }
    
    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteTutorialAttachment(int lectureId, String name) throws AttachmentNotFound {
        Lectures lecture = lecturesRepo.findOne(lectureId);
        for (TutorialAttachments attachment : lecture.getTutorialAttachments()) {
            if (attachment.getName().equals(name)) {
                lecture.deleteTutorialAttachment(attachment);
                lecturesRepo.save(lecture);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

    @Override
    @Transactional
    public long createLecture(String title, List<MultipartFile> lectureAttachments, 
            List<MultipartFile> tutorialAttachments) throws IOException {
        Lectures lecture = new Lectures();
        lecture.setTitle(title);
        
        for (MultipartFile filePart : lectureAttachments) {
            LectureAttachments attachment = new LectureAttachments();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLecture(lecture);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                lecture.getLectureAttachments().add(attachment);
            }
        }
        
        for (MultipartFile filePart : tutorialAttachments) {
            TutorialAttachments attachment = new TutorialAttachments();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLecture(lecture);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                lecture.getTutorialAttachments().add(attachment);
            }
        }
        
        Lectures savedLecture = lecturesRepo.save(lecture);
        return savedLecture.getId();
    }
    

    @Override
    @Transactional(rollbackFor = LectureNotFound.class)
    public void updateLecture(int id, String title, List<MultipartFile> lectureAttachments, 
            List<MultipartFile> tutorialAttachments)
            throws IOException, LectureNotFound {
        Lectures updatedLecture = lecturesRepo.findOne(id);
        if (updatedLecture == null) {
            throw new LectureNotFound();
        }

        updatedLecture.setTitle(title);

        for (MultipartFile filePart : lectureAttachments) {
            LectureAttachments attachment = new LectureAttachments();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLecture(updatedLecture);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedLecture.getLectureAttachments().add(attachment);
            }
        }
        
        for (MultipartFile filePart : tutorialAttachments) {
            TutorialAttachments attachment = new TutorialAttachments();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            attachment.setLecture(updatedLecture);
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null
                    && attachment.getContents().length > 0) {
                updatedLecture.getTutorialAttachments().add(attachment);
            }
        }
        
        lecturesRepo.save(updatedLecture);
    }
}
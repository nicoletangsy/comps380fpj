package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Lecture;

public interface AttachmentRepository extends JpaRepository<Attachment, Integer> {
    List<Attachment> readAttachmentByLectureOrderById(Lecture lecture);
}
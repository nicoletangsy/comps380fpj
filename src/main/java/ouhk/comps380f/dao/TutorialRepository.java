package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.TutorialAttachment;
import ouhk.comps380f.model.Lecture;

public interface TutorialRepository extends JpaRepository<TutorialAttachment, Integer> {
    List<TutorialAttachment> readTutorialAttachmentByLectureOrderById(Lecture lecture);
}

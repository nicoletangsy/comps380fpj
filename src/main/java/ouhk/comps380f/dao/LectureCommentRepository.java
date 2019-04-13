package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Lecture;
import ouhk.comps380f.model.LectureComment;

public interface LectureCommentRepository extends JpaRepository<LectureComment, Integer> {
    
    List<LectureComment> readLectureCommentByLectureOrderById(Lecture lecture);
}
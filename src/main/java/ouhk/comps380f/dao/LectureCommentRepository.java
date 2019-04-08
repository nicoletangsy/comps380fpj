package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.LectureComment;

public interface LectureCommentRepository extends JpaRepository<LectureComment, Integer> {
}

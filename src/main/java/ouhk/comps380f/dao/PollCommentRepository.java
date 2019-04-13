package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.PollComment;
import ouhk.comps380f.model.Polls;

public interface PollCommentRepository extends JpaRepository<PollComment, Integer> {
    
    List<PollComment> readPollCommentByPollOrderById(Polls poll);
}
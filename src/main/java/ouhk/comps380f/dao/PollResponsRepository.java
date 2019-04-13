package ouhk.comps380f.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Polls;
import ouhk.comps380f.model.PollRespons;

public interface PollResponsRepository extends JpaRepository<PollRespons, Integer> {
    
    List<PollRespons> readPollResponsByPollOrderById(Polls poll);
    List<PollRespons> readPollResponsByUsernameAndPollOrderById(String username , Polls poll);
    List<PollRespons> readPollResponsByPollAndResponse(Polls poll,String response);
    List<PollRespons> readPollResponsByUsernameOrderById(String username);
    long countPollResponsByResponseAndPoll(String response,Polls poll);
    
}

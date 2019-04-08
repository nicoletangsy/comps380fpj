package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.PollResponse;

public interface PollResponseRepository extends JpaRepository<PollResponse, Integer> {
}

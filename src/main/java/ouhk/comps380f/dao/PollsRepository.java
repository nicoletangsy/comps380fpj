package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Polls;

public interface PollsRepository extends JpaRepository<Polls, Integer> {
}
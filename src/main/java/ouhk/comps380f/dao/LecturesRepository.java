package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Lectures;

public interface LecturesRepository extends JpaRepository<Lectures, Integer> {
}

package dao;

import model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICandidateDao extends JpaRepository<Candidate, Long> {
}

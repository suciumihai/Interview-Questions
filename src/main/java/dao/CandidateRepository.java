package dao;

import model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    //@Query("SELECT c from Candidate c JOIN c.candidateHelperClasses h WHERE h.name = LOWER(:tagName)")
    //List<Candidate> retrievedByTag(@Param("tagName") String tagName);
}

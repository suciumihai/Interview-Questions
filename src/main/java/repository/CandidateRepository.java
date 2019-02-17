package repository;

import model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    //@Query("SELECT c from Candidate c JOIN c.candidateHelperClasses h WHERE h.name = LOWER(:tagName)")
    //List<Candidate> retrievedByTag(@Param("tagName") String tagName);
}

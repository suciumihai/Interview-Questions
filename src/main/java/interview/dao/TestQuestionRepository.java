package interview.dao;

import interview.model.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestQuestionRepository extends JpaRepository<TestQuestion, Long>, TestQuestionRepositoryCustom {
}

package interview.dao;

import interview.model.Category;
import interview.model.TestQuestion;

import java.util.Set;

public interface TestQuestionRepositoryCustom {

    public Set<TestQuestion> findQuestByCategDiffi(String category, String difficulty);
    public TestQuestion getByName(String name);
}

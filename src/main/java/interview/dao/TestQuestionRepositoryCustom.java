package interview.dao;

import interview.Enums.DifficultyLevel;
import interview.model.Category;
import interview.model.TestQuestion;

import java.util.Set;

public interface TestQuestionRepositoryCustom {

    public Set<TestQuestion> findQuestByCategDiffi(String category, DifficultyLevel difficulty);
    public TestQuestion getByName(String name);
}

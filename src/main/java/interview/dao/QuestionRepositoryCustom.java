package interview.dao;

import interview.model.Category;
import interview.model.Question;

import java.util.List;
import java.util.Set;

public interface QuestionRepositoryCustom {


    public Set<Question> findQuestByCategDiffi(Category category, String difficulty);
}

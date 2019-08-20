package interview.dao;

import interview.model.TestQuestion;

public interface TestQuestionRepositoryCustom {

    public TestQuestion getByName(String name);
}

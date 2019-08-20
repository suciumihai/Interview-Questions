package interview.dao;

import interview.model.Test;
import interview.model.TestQuestion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TestQuestionRepositoryImpl implements TestQuestionRepositoryCustom {

    @Autowired
    TestQuestionRepository testQuestionRepository;

    @Override
    public TestQuestion getByName(String name) {

        List<TestQuestion> testQuestions = new ArrayList<>();
        testQuestions = testQuestionRepository.findAll();
        for (TestQuestion testQuestion : testQuestions) {
            if (testQuestion.getName().equals(name))
                return testQuestion;
        }
        return null;
    }
}

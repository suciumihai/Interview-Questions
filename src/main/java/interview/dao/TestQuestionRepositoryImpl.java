package interview.dao;

import interview.model.Category;
import interview.model.Test;
import interview.model.TestQuestion;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestQuestionRepositoryImpl implements TestQuestionRepositoryCustom {

    @Autowired
    TestQuestionRepository testQuestionRepository;

    private Set<TestQuestion> res = new HashSet<>();
    private List<TestQuestion> all = new ArrayList<>();

    // nu prea mai e necesara, doar pt staticInit
    @Override
    public Set<TestQuestion> findQuestByCategDiffi(String category, String difficulty) {

        all = testQuestionRepository.findAll();

        Set<TestQuestion> allNoDupli = new HashSet<>(all);

        for (TestQuestion question : allNoDupli) {
            if (question.getCategory().getName().equals(category) && question.getDifficulty().equals(difficulty))
                res.add(question);
        }

        return res;
    }

    // nu prea mai e necesara, doar pt staticIn
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

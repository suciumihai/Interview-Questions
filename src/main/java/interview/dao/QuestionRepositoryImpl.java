package interview.dao;

import interview.model.Category;
import interview.model.Question;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionRepositoryImpl implements QuestionRepositoryCustom {

    @Autowired
    QuestionRepository questionRepository;

    private Set<Question> res = new HashSet<>();
    private List<Question> all = new ArrayList<>();

    @Override
    public Set<Question> findQuestByCategDiffi(Category category, String difficulty) {

        all = questionRepository.findAll();

        Set<Question> allNoDupli = new HashSet<>(all);

        for (Question question : allNoDupli) {
            if (question.getCategory().equals(category) && question.getDifficulty().equals(difficulty))
                res.add(question);
        }

        return res;
    }
}

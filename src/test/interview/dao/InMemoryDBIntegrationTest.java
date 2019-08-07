package interview.dao;

import interview.config.JpaConfig;
import interview.model.Candidate;
import interview.model.Category;
import interview.model.Question;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class})
@Transactional
public class InMemoryDBIntegrationTest {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    private static final Long ID = 1L;
    private static final String NAME = "john";

    @Test
    public void givenCandidate_whenSave_thenGetOk() {
        Candidate candidate1 = new Candidate();
        candidate1.setName(NAME);
        candidate1.setId(ID);
        //aici cu save l-am pus pe hibernate sa persiste
        candidateRepository.save(candidate1);

        Candidate candidate2 = candidateRepository.getOne(ID);

        assertEquals("name incorrect", NAME, candidate2.getName());
    }

    @Test
    public void getCatNameByQuestId(){
        Category java = new Category();
        java.setName("Java");
        categoryRepository.save(java);

        Question question1 = new Question();
        question1.setId(ID);
        question1.setName("q1");
        question1.setDifficulty("Easy");
        question1.setContent("este java OOP?");
        question1.setCategory(java);
        questionRepository.save(question1);

        assertEquals("java", questionRepository.findById(Long.valueOf(ID)).get().getCategory().getName());
    }

}
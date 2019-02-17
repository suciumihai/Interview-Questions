package dao;

import config.JpaConfig;
import model.Candidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { JpaConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class InMemoryDBIntegrationTest {

    @Resource
    private CandidateRepository candidateRepository;

    private static final Long ID = 1L;
    private static final String NAME = "john";

    @Test
    public void givenCandidate_whenSave_thenGetOk() {
        Candidate candidate1 = new Candidate();
        candidate1.setName(NAME);
        candidateRepository.save(candidate1);

        Candidate candidate2 = candidateRepository.getOne(ID);

        assertEquals("name incorrect", NAME, candidate2.getName());
    }

}
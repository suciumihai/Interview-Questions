package interview;

import interview.dao.*;
import interview.model.Candidate;
import interview.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StartUpInit {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryTemplateRepository categoryTemplateRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private TemplateRepository templateRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    public void init() {

        Candidate candidate1 = new Candidate();
        candidate1.setName("Ion Gheorghe");
        candidate1.setId(1L);
        candidate1.setEmail("ion.gheorghe@ceva.com");
        candidateRepository.save(candidate1);

        Candidate candidate2 = new Candidate();
        candidate2.setName("Alex dan");
        candidate2.setId(2L);
        candidate2.setEmail("alex.dan@ceva.com");
        candidateRepository.save(candidate2);

        User user1 = new User();
        user1.setId(1L);
        user1.setEmail("hr@ceva.com");
        user1.setRole("HR");
        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setEmail("questionAdmin@ceva.com");
        user2.setRole("questionAdmin");
        userRepository.save(user2);

    }
}

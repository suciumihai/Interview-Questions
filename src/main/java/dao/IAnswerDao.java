package dao;

import model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerDao extends JpaRepository<Answer, Long> {
}

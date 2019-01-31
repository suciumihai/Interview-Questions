package dao;

import model.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITemplateDao extends JpaRepository<Template, Long> {
}

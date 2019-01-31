package dao;

import model.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITestDao extends JpaRepository<Test, Long> {
}

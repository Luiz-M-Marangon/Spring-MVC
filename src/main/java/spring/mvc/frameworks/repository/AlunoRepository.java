package spring.mvc.frameworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.frameworks.model.Alunos;

public interface AlunoRepository extends JpaRepository<Alunos, Long> {
}

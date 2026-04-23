package spring.mvc.frameworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.frameworks.model.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}

package spring.mvc.frameworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.mvc.frameworks.model.Cursos;

public interface CursoRepository extends JpaRepository<Cursos, Long> {
}

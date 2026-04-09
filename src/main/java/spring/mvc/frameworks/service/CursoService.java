package spring.mvc.frameworks.service;
import spring.mvc.frameworks.model.Cursos;
import spring.mvc.frameworks.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Cursos> listarTodos(){
        return cursoRepository.findAll();
    }

    public Optional<Cursos> buscarPorId(Long id){
        return cursoRepository.findById(id);
    }

    public Cursos salvar(Cursos cursos){
        return cursoRepository.save(cursos);
    }

    public void deletar(Long id){
        cursoRepository.deleteById(id);
    }

    public Cursos atualizar(Long id, Cursos novoCurso){
        return cursoRepository.findById(id).map(cursoExistente ->{
            cursoExistente.setNome(novoCurso.getNome());
            return cursoRepository.save(cursoExistente);
        }).orElseThrow(() -> new RuntimeException("Curso não encontrado"));
    };
}
package spring.mvc.frameworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.frameworks.model.Alunos;
import spring.mvc.frameworks.model.Cursos;
import spring.mvc.frameworks.model.Projeto;
import spring.mvc.frameworks.repository.AlunoRepository;
import spring.mvc.frameworks.repository.CursoRepository;
import spring.mvc.frameworks.repository.ProjetoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Alunos> listarTodos(){
        return alunoRepository.findAll();
    }

    public Optional<Alunos> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    public Alunos salvar(Alunos alunos){
        if (alunos.getCursos() != null && alunos.getCursos().getId() != null){
            Cursos curso = cursoRepository.findById(alunos.getCursos().getId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado: " + alunos.getCursos().getId()));
            alunos.setCursos(curso);
        } else{
            alunos.setCursos(null);
        }

        if (alunos.getProjetos() != null && !alunos.getProjetos().isEmpty()){
            List<Projeto> projetosCompletos = alunos.getProjetos().stream()
                    .map(j -> projetoRepository.findById(j.getId())
                                .orElseThrow(() -> new RuntimeException("Projeto não econtrado: " + j.getId())))
                    .collect(Collectors.toList());
            alunos.setProjetos(projetosCompletos);
        }else {
            alunos.setProjetos(null);
        }

        return alunoRepository.save(alunos);
    }

    public void deletar(Long id){
        alunoRepository.deleteById(id);
    }

    public Alunos atualizar(Long id, Alunos novoAluno){
        return alunoRepository.findById(id).map(alunoExistente ->{
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setEmail(novoAluno.getEmail());

            if (novoAluno.getCursos() != null && novoAluno.getCursos().getId() != null){
                Cursos curso = cursoRepository.findById(novoAluno.getCursos().getId())
                        .orElseThrow(() -> new RuntimeException("Curso não encontrado " + novoAluno.getCursos().getId()));
                alunoExistente.setCursos(curso);
            }else {
                alunoExistente.setCursos(null);
            }

            if (novoAluno.getProjetos() != null && !novoAluno.getProjetos().isEmpty()) {
                List<Projeto> projetos = novoAluno.getProjetos().stream()
                        .map(j -> projetoRepository.findById(j.getId())
                                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + j.getId())))
                        .collect(Collectors.toList());
                alunoExistente.setProjetos(projetos);
            }else {
                alunoExistente.setProjetos(null);
            }

            return alunoRepository.save(alunoExistente);
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
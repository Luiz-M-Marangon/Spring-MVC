package spring.mvc.frameworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.mvc.frameworks.model.Alunos;
import spring.mvc.frameworks.repository.AlunoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public List<Alunos> listarTodos(){
        return alunoRepository.findAll();
    }

    public Optional<Alunos> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    public Alunos salvar(Alunos alunos){
        return alunoRepository.save(alunos);
    }

    public void deletar(Long id){
        alunoRepository.deleteById(id);
    }

    public Alunos atualizar(Long id, Alunos novoAluno){
        return alunoRepository.findById(id).map(alunoExistente ->{
            alunoExistente.setNome(novoAluno.getNome());
            alunoExistente.setEmail(novoAluno.getEmail());
            return alunoRepository.save(alunoExistente);
        }).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }
}
package spring.mvc.frameworks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spring.mvc.frameworks.model.Alunos;
import spring.mvc.frameworks.service.AlunoService;
import spring.mvc.frameworks.service.CursoService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public String listarAlunos(Model model){
        model.addAttribute("alunos", alunoService.listarTodos());
        return "alunos/lista";
    }

    @GetMapping("/novo")
    public String novoAluno(Model model){
        model.addAttribute("aluno", new Alunos());
        model.addAttribute("cursos", cursoService.listarTodos());
        return "alunos/form";
    }

    @PostMapping("/salvar")
    public String salvarAluno(@ModelAttribute Alunos alunos){
        if (alunos.getId() != null){
            alunoService.atualizar(alunos.getId(), alunos);
        } else{
            alunoService.salvar(alunos);
        }
        return "redirect:/alunos";
    }

    @GetMapping("/editar/{id}")
    public String editarAluno(@PathVariable Long id, Model model){
        Alunos alunos = alunoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Aluno inválido: " + id));
        model.addAttribute("aluno", alunos);
        model.addAttribute("cursos", cursoService.listarTodos());
        return "alunos/form";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAluno(@PathVariable Long id){
        alunoService.deletar(id);
        return "redirect:/alunos";
    }
}

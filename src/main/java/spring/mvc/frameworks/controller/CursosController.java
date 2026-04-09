package spring.mvc.frameworks.controller;

import spring.mvc.frameworks.model.Cursos;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.mvc.frameworks.service.CursoService;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/cursos")
public class CursosController {

   @Autowired
    private CursoService cursoService;

   @GetMapping
    public String listarCursos(Model model){
       model.addAttribute("cursos", cursoService.listarTodos());
       return "/cursos/lista";
   }

   @GetMapping("/nova")
    public String novoCurso(Model model){
       model.addAttribute("curso", new Cursos());
       return "cursos/form";
   }

   @PostMapping("/salvar")
    public String salvarCurso(@ModelAttribute Cursos cursos){
       if (cursos.getId() != null){
           cursoService.atualizar(cursos.getId(), cursos);
       } else{
           cursoService.salvar(cursos);
       }
       return "redirect:/cursos";
   }

   @GetMapping("/editar/{id}")
   public String editarCurso(@PathVariable Long id, Model model){
       Cursos cursos = cursoService.buscarPorId(id)
               .orElseThrow(() -> new IllegalArgumentException("Curso inválido: " + id));
       model.addAttribute("curso", cursos);
       return "cursos/form";
   }

   @GetMapping("/excluir/{id}")
    public String excluirCurso(@PathVariable Long id){
       cursoService.deletar(id);
       return "redirect:/cursos";
   }
}


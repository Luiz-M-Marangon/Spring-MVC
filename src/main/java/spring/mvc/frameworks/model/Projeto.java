package spring.mvc.frameworks.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;

    @ManyToMany(mappedBy = "projetos")
    private List<Alunos> alunos;

    @Override
    public String toString(){
        return "Projeto{id=" + id + ", título='" + titulo + ", descrição='" + descricao + "'}";
    }
}

package spring.mvc.frameworks.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private int CargaHoraria;

    @OneToMany(mappedBy = "alunos", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alunos> alunos;
}

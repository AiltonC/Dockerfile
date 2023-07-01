package proyecto.ponti.CONLAB.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcurso")
    private Integer id;
    private  String nomcurso;
    private  String horas;
    @OneToOne
    @JoinColumn(name = "iddocente")
    private Docente iddocente;
}
package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idalumno")
    private Integer id;
    private String codigo;
    private String nomalumno;
    private String appaterno;
    private String apmaterno;
    private String celular;
    private String correo;
    @OneToOne
    @JoinColumn(name = "idcarrera")
    private Carrera idcarrera;
    @OneToOne
    @JoinColumn(name = "idciclo")
    private Ciclo idciclo;
    @OneToOne
    @JoinColumn(name = "idcurso")
    private Curso idcurso;
    @OneToOne
    @JoinColumn(name = "idequipo")
    private Equipo idequipo;
    @OneToOne
    @JoinColumn(name = "idgestudio")
    private Gestudio idgestudio;
}

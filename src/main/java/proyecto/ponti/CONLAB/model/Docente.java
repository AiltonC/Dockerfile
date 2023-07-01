package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddocente")
    private Integer id;
    private String nomdocente;
    private String appaterno;
    private String apmaterno;
    private String dni;
    private String celular;
    private String correo;

}

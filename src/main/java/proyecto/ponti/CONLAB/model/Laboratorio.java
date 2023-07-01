package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Laboratorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idlaboratorio")
    private Integer id;

    private String nomlab;
    @OneToOne
    @JoinColumn(name = "idequipo")
    private Equipo idequipo;

    @OneToOne
    @JoinColumn(name = "idcomp")
    private Componente idcomp;



}

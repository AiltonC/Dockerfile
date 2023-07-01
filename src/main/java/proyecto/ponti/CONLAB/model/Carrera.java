package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idcarrera")
    private Integer id;
    private String nomcarrera;
    @OneToOne
    @JoinColumn(name = "idgestudio")
    private Gestudio idgestudio;
}

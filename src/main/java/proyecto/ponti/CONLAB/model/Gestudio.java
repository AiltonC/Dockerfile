package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Gestudio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idgestudio")
    private Integer id;
    private String nomgestudio;

}

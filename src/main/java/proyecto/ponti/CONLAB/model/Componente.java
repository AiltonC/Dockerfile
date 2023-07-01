package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Componente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcomponente")
    private Integer id;
    private String descrip;
    private String codigo;
    private String marca;
    private String capacidad;

}

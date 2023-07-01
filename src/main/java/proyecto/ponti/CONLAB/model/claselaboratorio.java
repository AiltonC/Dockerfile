package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity

public class claselaboratorio {
 @Id
 @GeneratedValue(strategy= GenerationType.IDENTITY)
 @Column(name="idclaselaboratorio")
 private Integer id;
 private Date horaentrada;
 private Date horasalida;
 private Date fecha;
 @OneToOne
 @JoinColumn(name = "idlab")
 private Laboratorio idlaboratorio;

}


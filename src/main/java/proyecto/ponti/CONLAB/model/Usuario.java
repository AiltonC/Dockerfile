package proyecto.ponti.CONLAB.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idusuario")
    private Integer id;
    private String nombre_usuario;
    private String ap_pat;
    private String ap_mat;
    @OneToOne
    @JoinColumn(name = "idrol")
    private Rol idrol;
    private String password;
    private String correo;
}

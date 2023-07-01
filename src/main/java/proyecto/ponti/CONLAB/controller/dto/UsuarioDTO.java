package proyecto.ponti.CONLAB.controller.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import proyecto.ponti.CONLAB.model.Rol;
@Data
public class UsuarioDTO {

    @NotBlank
    @Size(min = 3, max = 25)
    private String nombre_usuario;
    @NotBlank
    private String ap_pat;
    @NotBlank
    private String ap_mat;
    @NotNull
    @OneToOne
    @JoinColumn(name = "idrol")
    private Rol idrol;
    @NotBlank
    private String password;
    @NotBlank
    private String correo;
}


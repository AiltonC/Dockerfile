package proyecto.ponti.CONLAB.controller.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



import java.util.Date;
@Data
public class ComponenteDto {
    @NotBlank
    @Size(min = 3, max = 25)
    private String descrip;

    @NotBlank
    private String codigo;

    @NotBlank
    private String marca;

    @NotBlank
    private String capacidad;

}

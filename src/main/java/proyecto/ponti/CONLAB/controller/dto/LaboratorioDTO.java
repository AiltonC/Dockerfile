package proyecto.ponti.CONLAB.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LaboratorioDTO {

    @NotBlank
    @Size(min=3, max = 25)
    private String nomlab;


}

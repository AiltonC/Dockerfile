package proyecto.ponti.CONLAB.controller.dto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import proyecto.ponti.CONLAB.model.*;


import java.util.Date;

@Data
public class RolDTO {

    @NotBlank
    @Size(min = 3, max = 25)
    private String cargo;
}

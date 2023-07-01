package proyecto.ponti.CONLAB.controller.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import proyecto.ponti.CONLAB.model.*;


import java.sql.Time;
import java.util.Date;
@Data
public class ClaselaboratorioDto {
    @NotBlank
    @Size(min = 3, max = 25)
    private Time horaentrada;

    @NotBlank
    private Time horasalida;

    @NotBlank
    private Date fecha;

    @NotNull
    @OneToOne
    @JoinColumn(name = "idlab")
    private Laboratorio idlaboratorio;
}


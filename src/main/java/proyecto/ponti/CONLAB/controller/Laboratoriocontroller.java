package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.LaboratorioDTO;

import proyecto.ponti.CONLAB.model.Laboratorio;



import proyecto.ponti.CONLAB.repository.LaboratorioRepository;


@RestController
@RequestMapping("/api/admin/laboratorio")
public class Laboratoriocontroller {
    private final LaboratorioRepository laboratoriorepository;

    private Pageable pageable;

    public Laboratoriocontroller(LaboratorioRepository laboratoriorepository) {
        this.laboratoriorepository = laboratoriorepository;
    }

    @GetMapping("")
    Page<Laboratorio> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return laboratoriorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Laboratorio obtener(@PathVariable Integer id){
        return laboratoriorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Laboratorio crear(@RequestBody @Validated LaboratorioDTO laboratorioDTO){
        Laboratorio laboratorio=new ModelMapper().map(laboratorioDTO,Laboratorio.class);
        return laboratoriorepository.save(laboratorio);
    }

    @PutMapping("/{id}")
    Laboratorio actualizar(@PathVariable Integer id, @RequestBody LaboratorioDTO laboratorioDTO){
       Laboratorio laboratorio=laboratoriorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(laboratorioDTO, laboratorioDTO);
        return laboratoriorepository.save(laboratorio);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
       Laboratorio laboratorio=laboratoriorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        laboratoriorepository.delete(laboratorio);
    }
}
package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.CicloDTO;
import proyecto.ponti.CONLAB.controller.dto.CursoDTO;
import proyecto.ponti.CONLAB.model.Ciclo;
import proyecto.ponti.CONLAB.model.Curso;
import proyecto.ponti.CONLAB.repository.Ciclorepository;
import proyecto.ponti.CONLAB.repository.Cursorepository;
@RestController
@RequestMapping("/api/admin/ciclo")
public class Ciclocontroller {
    private final Ciclorepository ciclorepository;

    private Pageable pageable;

    public Ciclocontroller(Ciclorepository ciclorepository) {
        this.ciclorepository = ciclorepository;
    }

    @GetMapping("")
    Page<Ciclo> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return ciclorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Ciclo obtener(@PathVariable Integer id){
        return ciclorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Ciclo crear(@RequestBody @Validated CicloDTO cicloDTO){
        Ciclo ciclo=new ModelMapper().map(cicloDTO,Ciclo.class);
        return ciclorepository.save(ciclo);


    /*Alumno crear (@RequestBody Alumno alumno){
        return alumnorepository.save(alumno);

     */
    }

    @PutMapping("/{id}")
    Ciclo actualizar(@PathVariable Integer id, @RequestBody CicloDTO cicloDTO){
        Ciclo ciclo=ciclorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(cicloDTO, ciclo);
        return ciclorepository.save(ciclo);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Ciclo ciclo=ciclorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        ciclorepository.delete(ciclo);
    }
}

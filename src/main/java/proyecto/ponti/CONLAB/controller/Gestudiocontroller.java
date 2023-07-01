package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.CarreraDTO;
import proyecto.ponti.CONLAB.controller.dto.CicloDTO;
import proyecto.ponti.CONLAB.controller.dto.GestudioDTO;
import proyecto.ponti.CONLAB.model.Carrera;
import proyecto.ponti.CONLAB.model.Ciclo;
import proyecto.ponti.CONLAB.model.Gestudio;
import proyecto.ponti.CONLAB.repository.Carrerarepository;

import proyecto.ponti.CONLAB.repository.Gestudiorepository;


@RestController
@RequestMapping("/api/admin/gestudio")
public class Gestudiocontroller {
    private final Gestudiorepository gestudiorepository;

    private Pageable pageable;

    public Gestudiocontroller(Gestudiorepository gestudiorepository) {
        this.gestudiorepository = gestudiorepository;
    }

    @GetMapping("")
    Page<Gestudio> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return gestudiorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Gestudio obtener(@PathVariable Integer id){
        return gestudiorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Gestudio crear(@RequestBody @Validated GestudioDTO gestudioDTO){
        Gestudio gestudio=new ModelMapper().map(gestudioDTO,Gestudio.class);
        return gestudiorepository.save(gestudio);


    /*Alumno crear (@RequestBody Alumno alumno){
        return alumnorepository.save(alumno);

     */
    }

    @PutMapping("/{id}")
    Gestudio actualizar(@PathVariable Integer id, @RequestBody GestudioDTO gestudioDTO){
        Gestudio gestudio=gestudiorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(gestudioDTO, gestudio);
        return gestudiorepository.save(gestudio);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Gestudio gestudio=gestudiorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        gestudiorepository.delete(gestudio);
    }
}

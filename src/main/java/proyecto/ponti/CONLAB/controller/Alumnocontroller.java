package proyecto.ponti.CONLAB.controller;


import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.AlumnoDTO;
import proyecto.ponti.CONLAB.model.Alumno;
import proyecto.ponti.CONLAB.repository.Alumnorepository;

@RestController
@RequestMapping("/api/admin/alumno")
public class Alumnocontroller {

    private final Alumnorepository alumnorepository;
    private Pageable pageable;


    public Alumnocontroller(Alumnorepository alumnorepository){
        this.alumnorepository = alumnorepository;
    }

    @GetMapping("")
    Page<Alumno> index(@PageableDefault(sort = "codigo",size=15)Pageable pageable){
        return alumnorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Alumno obtener(@PathVariable Integer id){
        return alumnorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Alumno crear(@RequestBody @Validated AlumnoDTO alumnoDTO){
        Alumno alumno=new ModelMapper().map(alumnoDTO,Alumno.class);
        return alumnorepository.save(alumno);


    /*Alumno crear (@RequestBody Alumno alumno){
        return alumnorepository.save(alumno);

     */
    }

    @PutMapping("/{id}")
    Alumno actualizar(@PathVariable Integer id, @RequestBody AlumnoDTO alumnoDTO){
        Alumno alumno=alumnorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(alumnoDTO, alumno);
        return alumnorepository.save(alumno);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Alumno alumno=alumnorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        alumnorepository.delete(alumno);
    }
}
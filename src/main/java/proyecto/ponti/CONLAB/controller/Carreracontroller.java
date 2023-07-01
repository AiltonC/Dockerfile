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
import proyecto.ponti.CONLAB.controller.dto.CarreraDTO;
import proyecto.ponti.CONLAB.model.Alumno;
import proyecto.ponti.CONLAB.model.Carrera;
import proyecto.ponti.CONLAB.repository.Alumnorepository;
import proyecto.ponti.CONLAB.repository.Carrerarepository;

@RestController
@RequestMapping("/api/admin/carrera")
public class Carreracontroller {

    private final Carrerarepository carrerarepository;


    public Carreracontroller(Carrerarepository carrerarepository){
        this.carrerarepository = carrerarepository;
    }

    @GetMapping("")
    Page<Carrera> index(@PageableDefault(sort = "nomcarrera",size=5)Pageable pageable){
        return carrerarepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Carrera obtener(@PathVariable Integer id){
        return carrerarepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reg")
    Carrera crear(@RequestBody @Validated CarreraDTO carreraDTO){
        Carrera carrera=new ModelMapper().map(carreraDTO,Carrera.class);
        return carrerarepository.save(carrera);
    }
    @PutMapping("/{id}")
    Carrera actualizar(@PathVariable Integer id, @RequestBody Carrera carreraDTO){
        Carrera carrera=carrerarepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(carreraDTO, carrera);
        return carrerarepository.save(carrera);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Carrera carrera=carrerarepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        carrerarepository.delete(carrera);
    }
}

package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.EquipoDTO;
import proyecto.ponti.CONLAB.model.Equipo;
import proyecto.ponti.CONLAB.repository.EquipoRepository;

@RestController
@RequestMapping("/api/admin/equipo")
public class Equipocontroller {
    private final EquipoRepository equiporepository;

    private Pageable pageable;

    public Equipocontroller(EquipoRepository equiporepository) {
        this.equiporepository = equiporepository;
    }

    @GetMapping("")
    Page<Equipo> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return equiporepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Equipo obtener(@PathVariable Integer id){
        return equiporepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Equipo crear(@RequestBody @Validated EquipoDTO equipoDTO){
        Equipo equipo=new ModelMapper().map(equipoDTO,Equipo.class);
        return equiporepository.save(equipo);


    /*Alumno crear (@RequestBody Alumno alumno){
        return alumnorepository.save(alumno);

     */
    }

    @PutMapping("/{id}")
    Equipo actualizar(@PathVariable Integer id, @RequestBody EquipoDTO equipoDTO){
        Equipo equipo=equiporepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(equipoDTO, equipo);
        return equiporepository.save(equipo);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Equipo equipo=equiporepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        equiporepository.delete(equipo);
    }

}

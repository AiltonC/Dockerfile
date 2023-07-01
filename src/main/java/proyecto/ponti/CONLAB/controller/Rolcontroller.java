package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import proyecto.ponti.CONLAB.controller.dto.RolDTO;

import proyecto.ponti.CONLAB.model.Rol;

import proyecto.ponti.CONLAB.repository.Rolrepository;

@RestController
@RequestMapping("/api/admin/rol")
public class Rolcontroller {
    private final Rolrepository rolrepository;

    private Pageable pageable;

    public Rolcontroller(Rolrepository rolrepository) {
        this.rolrepository = rolrepository;
    }

    @GetMapping("")
    Page<Rol> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return rolrepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Rol obtener(@PathVariable Integer id){
        return rolrepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Rol crear(@RequestBody @Validated RolDTO rolDTO){
        Rol rol=new ModelMapper().map(rolDTO,Rol.class);
        return rolrepository.save(rol);


/*Alumno crear (@RequestBody Alumno alumno){
return alumnorepository.save(alumno);

*/
    }

    @PutMapping("/{id}")
    Rol actualizar(@PathVariable Integer id, @RequestBody RolDTO rolDTO){
        Rol rol=rolrepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(rolDTO, rol);
        return rolrepository.save(rol);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Rol rol=rolrepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        rolrepository.delete(rol);
    }

}

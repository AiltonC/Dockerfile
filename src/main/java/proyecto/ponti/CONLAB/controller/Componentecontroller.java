package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.ComponenteDto;
import proyecto.ponti.CONLAB.model.Componente;
import proyecto.ponti.CONLAB.repository.Componenterepository;

@RestController
@RequestMapping("/api/admin/componente")
public class Componentecontroller {
    private final Componenterepository componenterepository;

    public Componentecontroller(Componenterepository componenterepository) {
        this.componenterepository = componenterepository;
    }

    @GetMapping("")
    Page<Componente> index(@PageableDefault(sort = "codigo",size=5) Pageable pageable){
        return componenterepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Componente obtener(@PathVariable Integer id){
        return componenterepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reg")
    Componente crear(@RequestBody @Validated ComponenteDto componenteDto){
        Componente componente=new ModelMapper().map(componenteDto,Componente.class);
        return componenterepository.save(componente);
    }
    @PutMapping("/{id}")
    Componente actualizar(@PathVariable Integer id, @RequestBody ComponenteDto componenteDto){
        Componente componente=componenterepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(componenteDto, componente);
        return componenterepository.save(componente);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Componente componente=componenterepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        componenterepository.delete(componente);
    }
}

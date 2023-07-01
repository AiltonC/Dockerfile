package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.CursoDTO;
import proyecto.ponti.CONLAB.model.Curso;
import proyecto.ponti.CONLAB.repository.Cursorepository;

@RestController
@RequestMapping("/api/admin/curso")
public class Cursocontroller {

    private final Cursorepository cursorepository;

    public Cursocontroller(Cursorepository cursorepository) {
        this.cursorepository = cursorepository;
    }
    @GetMapping("")
    Page<Curso> index(@PageableDefault(sort = "codigo",size=5) Pageable pageable){
        return cursorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Curso obtener(@PathVariable Integer id){
        return cursorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reg")
    Curso crear(@RequestBody @Validated CursoDTO cursoDTO){
        Curso curso=new ModelMapper().map(cursoDTO,Curso.class);
        return cursorepository.save(curso);
    }
    @PutMapping("/{id}")
    Curso actualizar(@PathVariable Integer id, @RequestBody CursoDTO cursoDTO){
        Curso curso=cursorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(cursoDTO, curso);
        return cursorepository.save(curso);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Curso curso=cursorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        cursorepository.delete(curso);
    }
}

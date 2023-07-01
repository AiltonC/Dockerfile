package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import proyecto.ponti.CONLAB.controller.dto.DocenteDTO;
import proyecto.ponti.CONLAB.model.Docente;
import proyecto.ponti.CONLAB.repository.Docenterepository;

@RestController
@RequestMapping("/api/admin/docente")
public class Docentecontroller {

    private final Docenterepository docenterepository;

    public Docentecontroller(Docenterepository docenterepository) {
        this.docenterepository = docenterepository;
    }

    @GetMapping("")
    Page<Docente> index(@PageableDefault(sort = "codigo",size=5) Pageable pageable){
        return docenterepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Docente obtener(@PathVariable Integer id){
        return docenterepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reg")
    Docente crear(@RequestBody @Validated DocenteDTO docenteDTO){
        Docente curso=new ModelMapper().map(docenteDTO,Docente.class);
        return docenterepository.save(curso);
    }
    @PutMapping("/{id}")
    Docente actualizar(@PathVariable Integer id, @RequestBody DocenteDTO docenteDTO){
        Docente docente=docenterepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(docenteDTO, docente);
        return docenterepository.save(docente);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Docente docente=docenterepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        docenterepository.delete(docente);
    }
}

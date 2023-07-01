package proyecto.ponti.CONLAB.controller;

import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import proyecto.ponti.CONLAB.controller.dto.UsuarioDTO;

import proyecto.ponti.CONLAB.model.Usuario;

import proyecto.ponti.CONLAB.repository.Usuariorepository;

@RestController
@RequestMapping("/api/admin/usuario")
public class Usuariocontroller {
    private final Usuariorepository usuariorepository;

    private Pageable pageable;

    public Usuariocontroller(Usuariorepository usuariorepository) {
        this.usuariorepository = usuariorepository;
    }

    @GetMapping("")
    Page<Usuario> index(@PageableDefault(sort = "id",size=15)Pageable pageable){
        return usuariorepository.findAll(pageable);
    }

    @GetMapping("/{id}")
    Usuario obtener(@PathVariable Integer id){
        return usuariorepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    Usuario crear(@RequestBody @Validated UsuarioDTO usuarioDTO){
        Usuario usuario=new ModelMapper().map(usuarioDTO,Usuario.class);
        return usuariorepository.save(usuario);


    /*Alumno crear (@RequestBody Alumno alumno){
        return alumnorepository.save(alumno);

     */
    }

    @PutMapping("/{id}")
    Usuario actualizar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario=usuariorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        new ModelMapper().map(usuarioDTO, usuario);
        return usuariorepository.save(usuario);


    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void eliminar(@PathVariable Integer id){
        Usuario usuario=usuariorepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);
        usuariorepository.delete(usuario);
    }
}

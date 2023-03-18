
package com.portfolio.jv.Controller;

import com.portfolio.jv.Dto.dtoExperiencia;
import com.portfolio.jv.Entity.Experiencia;
import com.portfolio.jv.Security.Controller.Mensaje;
import com.portfolio.jv.Service.SExperiencia;
import io.micrometer.common.util.StringUtils;
import java.util.List;
import net.bytebuddy.agent.VirtualMachine.ForHotSpot.Connection.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("explab")
@CrossOrigin(origins = "http://localhost:4200")
public class CExperiencia {
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = Experiencia.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = sExperiencia.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(sExperiencia.existsByNombreE(dtoexp.getNombreE()))
        return new ResponseEntity(new Mensaje("Esa experiencia existe"), HttpStatus.BAD_REQUEST);   
        
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescriptionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(bew mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
    //validamos si existe el id
    if(!sExperiencia.existsById(id))
        return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
    
    if(sExperiencia.existsByNombreE(dtoexp.getNombreE()) && sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
        return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
    
    if(StringUtils.isBlank(dtoexp.getNombreE()))
        return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
    Experiencia experiencia = sExperiencia.getOne(id).get();
    experiencia.setNombreE(dtoexp.getNombreE());
    experiencia.setDescriptionE((dtoexp.getDescriptionE()));
    
    sExperiencia.save(experiencia);
    return new ResponseEntity(new Mensaje("Experiencia actuelizada"), HttpStatus.OK);   
  }
    
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Validamos si existe
        if(!sExperiencia.existsById(id))
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        sExperiencia.delete(id);
        
        return Response Entity(new Mesnaje("Experiencia eliminada"), HttpStatus.OK);    
    }
}
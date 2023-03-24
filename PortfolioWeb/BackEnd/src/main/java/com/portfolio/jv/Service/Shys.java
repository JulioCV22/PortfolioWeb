
package com.portfolio.jv.Service;

import com.portfolio.jv.Entity.hys;
import com.portfolio.jv.Security.Repository.Rhys;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class Shys {
    @Autowired
    Rhys rhys;
    
    public List<hys> list(){
        return rhys.findAll();
    }
    
    public Optional<hys> getOne(int id){
        return rhys.findById(id);
    }
    
    public Optional<hys>getByNombre (String nombre) {
        return rhys.findByNombre(nombre);
    }
    
    public void save(hys skill){
        rhys.save(skill);
    }
    
    public void delete(int id){
        rhys.deleteById(id);
    }
    
    public boolean exixtsById(int id){
        return rhys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhys.existsByNombre(nombre);
    }

    public boolean existsById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

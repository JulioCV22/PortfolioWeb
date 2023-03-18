package com.portfolio.jv.Interface;

import com.portfolio.jv.Entity.Persona;
import java.util.List;



public interface IPersonaService {
    //traer una persona
    public List<Persona> getPersona();
    
    //guardar un objeto de tipo persona
    public void savePersona (Persona persona);
    
    //eliminar usuario lo buscamos por ID
    public void deletePersona (Long id);
    
    //buscar persona por ID
    public Persona findPersona(Long id);
}

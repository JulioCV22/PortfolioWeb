
package com.portfolio.jv.Security.Entity;

import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;



@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    private Strings nombre;
    @NotNull
    @Column(unique = true)
    private Strings nombreUsuario;
    @NotNull
    private Strings email;
    @NotNull
    private Strings password;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "usuario_rol", joinColumns = @JoinColumn (name ="usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();
    
    //Constructores

  
    public Usuario(Strings nombre, Strings nombreUsuario, Strings email, Strings password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }

    
    //Getter Y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Strings getNombre() {
        return nombre;
    }

    public void setNombre(Strings nombre) {
        this.nombre = nombre;
    }

    public Strings getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(Strings nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Strings getEmail() {
        return email;
    }

    public void setEmail(Strings email) {
        this.email = email;
    }

    public Strings getPassword() {
        return password;
    }

    public void setPassword(Strings password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    
}





package com.therapp.spring.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SolicitudOrganizacion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String email;
    private String cif;
    private String direccion;
    private String telefono;
    private String web;
    
    @OneToOne
    @JoinColumn(name = "usuario_id") // Relaciona con Usuario
    @JsonBackReference
    private Usuario usuario;

    public Usuario getUsuario(){
        return usuario;
    }
}

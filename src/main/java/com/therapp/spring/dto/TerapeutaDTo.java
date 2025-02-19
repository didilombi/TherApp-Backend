package com.therapp.spring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TerapeutaDTo extends UsuarioDTO {
    private String especialidad;
    
}

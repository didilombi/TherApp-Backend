package com.therapp.spring.modelo;

import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date createdDate;

    @OneToOne
    private Usuario usuario;

    public ConfirmationToken(Usuario usuario) {
        this.usuario = usuario;
        this.createdDate = new Date();
        this.token = UUID.randomUUID().toString();
    }
}

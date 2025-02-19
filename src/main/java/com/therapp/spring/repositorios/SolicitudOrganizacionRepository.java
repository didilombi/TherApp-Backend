package com.therapp.spring.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.therapp.spring.modelo.SolicitudOrganizacion;

public interface SolicitudOrganizacionRepository extends JpaRepository<SolicitudOrganizacion, Long>{
	SolicitudOrganizacion findByCif(String cif);
}

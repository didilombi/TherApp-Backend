package com.therapp.spring.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.therapp.spring.modelo.SolicitudOrganizacion;
import com.therapp.spring.repositorios.SolicitudOrganizacionRepository;

@Service
public class SolicitudOrganizacionService {
	@Autowired
	SolicitudOrganizacionRepository solicitudOrganizacionRepository;
	
	public SolicitudOrganizacion findByCif(String cif) {
		return solicitudOrganizacionRepository.findByCif(cif);
	}
	
	public void save(SolicitudOrganizacion solicitud) {
		solicitudOrganizacionRepository.save(solicitud);
	}

	public List<SolicitudOrganizacion> findAll(){
		return solicitudOrganizacionRepository.findAll();
	}

	public void delete(SolicitudOrganizacion solicitud){
		solicitudOrganizacionRepository.delete(solicitud);
	}
}

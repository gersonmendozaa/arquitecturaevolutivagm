package com.eat.cuentas.infrastructure;

import org.springframework.stereotype.Repository;

import com.eat.cuentas.domain.puertos.OficinaAtencionRepository;

@Repository
public class H2OficinaAtencionRepository implements OficinaAtencionRepository{

	@Override
	public String obtenerOficinaAtencionPorUbicacion(String ubicacion) {
		String oficina="oficina de atencion";
		return oficina;
	}

}

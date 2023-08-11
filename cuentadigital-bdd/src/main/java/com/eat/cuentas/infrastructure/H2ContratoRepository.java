package com.eat.cuentas.infrastructure;

import org.springframework.stereotype.Repository;

import com.eat.cuentas.domain.puertos.ContratoRepository;

@Repository
public class H2ContratoRepository implements ContratoRepository{

	@Override
	public boolean tieneContrato(String nombreCliente) {
		// TODO Implementar la lógica de acceso a datos.
		return false;
	}

}

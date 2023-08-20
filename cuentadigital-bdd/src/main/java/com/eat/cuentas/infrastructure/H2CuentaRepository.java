package com.eat.cuentas.infrastructure;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.puertos.CuentaRepository;

@Repository
public class H2CuentaRepository implements CuentaRepository{

	@Override
	public void guardarBancoCentral(Cuenta cuenta) {
		// TODO Auto-generated method stub
		// TODO Implementar la lógica de acceso a datos.

	}

	@Override
	public void guardarCoreBancario(Cuenta cuenta) {
		// TODO Auto-generated method stub
		// TODO Implementar la lógica de acceso a datos.

	}

	@Override
	public Cuenta consultarCuenta(String nombreCliente) {
		// TODO Auto-generated method stub
		// TODO Implementar la lógica de acceso a datos.

		return new Cuenta("Mauricio", new BigDecimal(7654));
	}

	@Override
	public String obtenerOficinaAtencion(String ubicacion) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'obtenerOficinaAtencion'");
	}

	@Override
	public boolean verificoDatosUbicacionSonValidos(String ubicacion) {
		boolean datosValidos = true;
		return datosValidos;
	}

	@Override
	public String obtenerOficinaAtencionPorUbicacion(String ubicacion) {
		String oficina="oficina de atencion";
		return oficina;
	}

	@Override
	public void asociarOficinaAtencionCercana(String nombreCliente, String oficinaAtencion) {
		// TODO Auto-generated method stub
	}

}

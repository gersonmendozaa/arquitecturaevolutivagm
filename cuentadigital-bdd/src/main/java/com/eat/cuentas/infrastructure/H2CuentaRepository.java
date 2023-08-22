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
	public void asociarOficinaAtencionCercana(String nombreCliente, String oficinaAtencion) {
		// TODO Auto-generated method stub
	}

}

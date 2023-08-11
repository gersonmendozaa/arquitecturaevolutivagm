package com.eat.cuentas.domain.puertos;

import com.eat.cuentas.domain.entidades.Cuenta;
 
public interface CuentaRepository {
	
	public void guardarBancoCentral(Cuenta cuenta);
	
	public void guardarCoreBancario(Cuenta cuenta);
	
	public Cuenta consultarCuenta(String nombreCliente);

}

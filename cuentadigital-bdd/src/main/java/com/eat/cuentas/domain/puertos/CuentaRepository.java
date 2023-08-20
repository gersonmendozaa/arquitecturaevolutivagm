package com.eat.cuentas.domain.puertos;

import com.eat.cuentas.domain.entidades.Cuenta;
 
public interface CuentaRepository {
	
	public void guardarBancoCentral(Cuenta cuenta);
	
	public void guardarCoreBancario(Cuenta cuenta);
	
	public Cuenta consultarCuenta(String nombreCliente);

	public String obtenerOficinaAtencion(String ubicacion);

	public boolean verificoDatosUbicacionSonValidos(String ubicacion);

	public String obtenerOficinaAtencionPorUbicacion(String ubicacion);

    public void asociarOficinaAtencionCercana(String nombreCliente, String oficinaAtencion);

}

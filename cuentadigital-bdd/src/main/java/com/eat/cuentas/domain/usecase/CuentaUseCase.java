package com.eat.cuentas.domain.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.puertos.ContratoRepository;
import com.eat.cuentas.domain.puertos.CuentaRepository;
import com.eat.cuentas.domain.puertos.ValidacionBiometricaRepository;

@Service
public class CuentaUseCase {
	
	@Autowired(required = false)
	private CuentaRepository cuentaRepository;
	@Autowired(required = false)
	private ValidacionBiometricaRepository validacionBiometricaRepository;
	@Autowired(required = false)
	private ContratoRepository contratoRepository;
		
	public void setCuentaRepository(CuentaRepository cuentaRepository) {
		this.cuentaRepository = cuentaRepository;
	}
	
	public void setValidacionBiometricaRepository(ValidacionBiometricaRepository validacionBiometricaRepository) {
		this.validacionBiometricaRepository = validacionBiometricaRepository;
	}
	
	public void setContratoRepository(ContratoRepository contratoRepository) {
		this.contratoRepository = contratoRepository;
	}

	public Cuenta crearCuenta(String nombreCliente) throws Exception {
		Cuenta cuenta= cuentaRepository.consultarCuenta(nombreCliente);
		boolean tieneValidacionBiometrica=validacionBiometricaRepository.tieneValidacionBiometrica(nombreCliente);
		boolean tieneContratoFirmado=contratoRepository.tieneContrato(nombreCliente);
		if(cuenta==null && tieneContratoFirmado && tieneValidacionBiometrica) {
			cuenta=Cuenta.crear(nombreCliente);
			cuentaRepository.guardarBancoCentral(cuenta);
			cuentaRepository.guardarCoreBancario(cuenta);
			return cuenta;
		}else {
			throw new Exception("Ya existe la cuenta o no cumple con los requisitos");

		}
	}

}

package com.eat.cuentas.domain.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.exceptions.BusinessException;
import com.eat.cuentas.domain.puertos.ContratoRepository;
import com.eat.cuentas.domain.puertos.CuentaRepository;
import com.eat.cuentas.domain.puertos.OficinaAtencionRepository;
import com.eat.cuentas.domain.puertos.ValidacionBiometricaRepository;

@Service
public class CuentaUseCase {
	
	@Autowired(required = false)
	private CuentaRepository cuentaRepository;
	@Autowired(required = false)
	private OficinaAtencionRepository oficinaAtencionRepository;
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
	public void setOficinaAtencionRepository(OficinaAtencionRepository oficinaAtencionRepository){
		this.oficinaAtencionRepository= oficinaAtencionRepository;
	}

	public Cuenta crearCuenta(String nombreCliente)  {
		Cuenta cuenta= cuentaRepository.consultarCuenta(nombreCliente);
		boolean tieneContratoFirmado=contratoRepository.tieneContrato(nombreCliente);
		if(!tieneContratoFirmado) {
			throw new BusinessException("No tiene el contrato firmado");

		}else if(cuenta!=null) {
			throw new BusinessException("La cuenta ya existe");

		}else{
			cuenta=Cuenta.crear(nombreCliente);
			cuentaRepository.guardarCoreBancario(cuenta);
			cuentaRepository.guardarBancoCentral(cuenta);
			return cuenta;
		}
	}

	public void asociarOficinaAtencionACuenta(String nombreCliente, String ubicacion){
		Cuenta cuenta= cuentaRepository.consultarCuenta(nombreCliente);
		String oficina=oficinaAtencionRepository.obtenerOficinaAtencionPorUbicacion(ubicacion);
		if(cuenta==null) {
			throw new BusinessException("No tiene cuenta");

		}else if(oficina==null) {
			throw new BusinessException("No se obtuvo oficina cercana");

		}else{
			cuentaRepository.asociarOficinaAtencionCercana(nombreCliente, oficina);
		}
	}

}

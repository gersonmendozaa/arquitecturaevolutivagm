package com.eat.cuentas.domain.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.exceptions.BusinessException;
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

	public void identificarOficinaAtencion(String nombreCliente, String ubicacion){
		Cuenta cuenta= cuentaRepository.consultarCuenta(nombreCliente);
		boolean datosValidos=cuentaRepository.verificoDatosUbicacionSonValidos(ubicacion);
		String oficina=cuentaRepository.obtenerOficinaAtencionPorUbicacion(ubicacion);
		if(cuenta==null) {
			throw new BusinessException("No tiene cuenta");

		}else if(!datosValidos) {
			throw new BusinessException("Los datos de ubicación no son validos");

		}else if(oficina==null) {
			throw new BusinessException("No se obtuvo oficina cercana");

		}else{
			cuentaRepository.asociarOficinaAtencionCercana(nombreCliente, oficina);
		}
	}

}

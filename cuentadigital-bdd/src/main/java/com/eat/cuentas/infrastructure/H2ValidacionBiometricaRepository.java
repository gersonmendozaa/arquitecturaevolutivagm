package com.eat.cuentas.infrastructure;

import org.springframework.stereotype.Repository;

import com.eat.cuentas.domain.puertos.ValidacionBiometricaRepository;

@Repository
public class H2ValidacionBiometricaRepository implements ValidacionBiometricaRepository{

	@Override
	public boolean tieneValidacionBiometrica(String nombre) {
		// TODO Auto-generated method stub
		return false;
	}

}

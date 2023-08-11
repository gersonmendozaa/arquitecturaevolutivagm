package com.eat.cuentas.application.dtos;

import java.math.BigDecimal;

public class CreacionCuentaResponseDTO {

	private String nombre;
	private BigDecimal saldo;

	public CreacionCuentaResponseDTO(String nombre, BigDecimal saldo) {
		this.nombre = nombre;
		this.saldo = saldo;
	}

	public String getNombre() {
		return nombre;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	
	
}
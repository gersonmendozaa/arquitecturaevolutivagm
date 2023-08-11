package com.eat.cuentas.domain.entidades;

import java.math.BigDecimal;

public class Cuenta {

	private String nombreCliente;
	private BigDecimal saldo;

	public static Cuenta crear(String nombreCliente) {
		return new Cuenta(nombreCliente, new BigDecimal(0));
	}

	public Cuenta(String nombreCliente, BigDecimal saldo) {
		this.nombreCliente = nombreCliente;
		this.saldo = saldo;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


}

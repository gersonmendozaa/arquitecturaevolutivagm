package com.eat.cuentas.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eat.cuentas.application.dtos.CreacionCuentaRequestDTO;
import com.eat.cuentas.application.dtos.CreacionCuentaResponseDTO;
import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.usecase.CuentaUseCase;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

	@Autowired
	private CuentaUseCase cuentaService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CreacionCuentaResponseDTO creacionCuenta(@RequestBody CreacionCuentaRequestDTO cuentaDTO) {
		try {
			Cuenta cuenta=cuentaService.crearCuenta(cuentaDTO.getNombre());
			return new CreacionCuentaResponseDTO(cuenta.getNombreCliente(),cuenta.getSaldo());	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//TODO: Colocar response de error.
			return null;
		}
	}

}

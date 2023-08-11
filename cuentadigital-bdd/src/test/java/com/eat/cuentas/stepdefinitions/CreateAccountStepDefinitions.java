package com.eat.cuentas.stepdefinitions;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.puertos.ContratoRepository;
import com.eat.cuentas.domain.puertos.CuentaRepository;
import com.eat.cuentas.domain.puertos.ValidacionBiometricaRepository;
import com.eat.cuentas.domain.usecase.CuentaUseCase;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ContextConfiguration(classes = {CuentaUseCase.class})
public class CreateAccountStepDefinitions {

	@Autowired
	private CuentaUseCase cuentaService;
	@Mock
	private CuentaRepository cuentaRepository;
	private Cuenta cuenta;
	@Mock
	private ValidacionBiometricaRepository validacionBiometrica; 
	@Mock
	private ContratoRepository contratoRepository;
	
	@Before
	public void setUp() {
		cuentaService.setCuentaRepository(cuentaRepository);
		cuentaService.setContratoRepository(contratoRepository);
		cuentaService.setValidacionBiometricaRepository(validacionBiometrica);
	}
	
	@Given("{string} completó el proceso de validación biométrica")
	public void completó_el_proceso_de_validación_biométrica(String nombreCliente) {
	    when(validacionBiometrica.tieneValidacionBiometrica(nombreCliente)).thenReturn(true);
	}
	@Given("{string} firmó el contrato")
	public void firmó_el_contrato(String nombreCliente) {
	    when(contratoRepository.tieneContrato(nombreCliente)).thenReturn(true);
	}
	@Given("{string} No tiene cuenta creada previamente en el banco.")
	public void no_tiene_cuenta_creada_previamente_en_el_banco(String nombreCliente) {
	    when(cuentaRepository.consultarCuenta(nombreCliente)).thenReturn(null);
	}
	@When("{string} intenta crear la cuenta.")
	public void intenta_crear_la_cuenta(String nombreCliente) throws Exception {
		cuenta = cuentaService.crearCuenta(nombreCliente);
		assertNotNull(cuenta);
		//Verifica que se haya llamado el método
		verify(cuentaRepository,times(1)).guardarBancoCentral(cuenta);
		verify(cuentaRepository,times(1)).guardarCoreBancario(cuenta);
	}
	
	@Then("Se crea exitosamente la cuenta en el core bancario para {string} con saldo {int}.")
	public void se_crea_exitosamente_la_cuenta_en_el_core_bancario_para_con_saldo(String string, Integer int1) {
		//Verifica que se haya creado la cuenta
		assertEquals(new BigDecimal(0), cuenta.getSaldo());
				
		//Verifica que se llame el método del repository con la cuenta
		ArgumentCaptor<Cuenta> cuentaCaptor = ArgumentCaptor.forClass(Cuenta.class);
		verify(cuentaRepository).guardarCoreBancario(cuentaCaptor.capture());
	}
	
	@Then("Se crea la cuenta en el banco central para {string}.")
	public void se_crea_la_cuenta_en_el_banco_central_para(String string) {
		
		//Verifica que se llame el método del repository con la cuenta
        ArgumentCaptor<Cuenta> cuentaCaptor = ArgumentCaptor.forClass(Cuenta.class);
        verify(cuentaRepository).guardarBancoCentral(cuentaCaptor.capture());
	}

	




	
	
}

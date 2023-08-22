package com.eat.cuentas.stepdefinitions;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.eat.cuentas.domain.entidades.Cuenta;
import com.eat.cuentas.domain.exceptions.BusinessException;
import com.eat.cuentas.domain.puertos.ContratoRepository;
import com.eat.cuentas.domain.puertos.CuentaRepository;
import com.eat.cuentas.domain.puertos.OficinaAtencionRepository;
import com.eat.cuentas.domain.puertos.ValidacionBiometricaRepository;
import com.eat.cuentas.domain.usecase.CuentaUseCase;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@ContextConfiguration(classes = { CuentaUseCase.class })
public class CreateAccountStepDefinitions {

	@Autowired
	private CuentaUseCase cuentaService;
	@Mock
	private CuentaRepository cuentaRepository;
	@Mock
	private OficinaAtencionRepository oficinaAtencionRepository;
	private Cuenta cuenta;
	@Mock
	private ValidacionBiometricaRepository validacionBiometrica;
	@Mock
	private ContratoRepository contratoRepository;

	private String errorMessage;

	@Before
	public void setUp() {
		cuentaService.setCuentaRepository(cuentaRepository);
		cuentaService.setContratoRepository(contratoRepository);
		cuentaService.setValidacionBiometricaRepository(validacionBiometrica);
		cuentaService.setOficinaAtencionRepository(oficinaAtencionRepository);
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
	public void intenta_crear_la_cuenta(String nombreCliente) {
		try {
			cuenta = cuentaService.crearCuenta(nombreCliente);
		} catch (BusinessException e) {
			cuenta = null;
			errorMessage = e.getMessage();
		}
	}

	@Then("Se crea exitosamente la cuenta en el core bancario para {string} con saldo {int}.")
	public void se_crea_exitosamente_la_cuenta_en_el_core_bancario_para_con_saldo(String nombreCLiente, Integer saldo) {
		// Verifica que se haya llamado el método
		ArgumentCaptor<Cuenta> cuentaCaptor = ArgumentCaptor.forClass(Cuenta.class);
		verify(cuentaRepository, times(1)).guardarCoreBancario(cuentaCaptor.capture());
		assertEquals(new BigDecimal(saldo), cuentaCaptor.getValue().getSaldo());
		assertEquals(nombreCLiente, cuentaCaptor.getValue().getNombreCliente());
	}

	@Then("Se crea la cuenta en el banco central para {string}.")
	public void se_crea_la_cuenta_en_el_banco_central_para(String nombreCLiente) {
		// Verifica que se llame el método del repository con la cuenta
		ArgumentCaptor<Cuenta> cuentaCaptor = ArgumentCaptor.forClass(Cuenta.class);
		verify(cuentaRepository).guardarBancoCentral(cuentaCaptor.capture());
		assertEquals(nombreCLiente, cuentaCaptor.getValue().getNombreCliente());
	}

	@Given("{string} completó el proceso de creación de cuenta")
	public void completó_el_proceso_de_creación_de_cuenta(String nombreCliente) {
		Cuenta newCuenta = new Cuenta("Maria", new BigDecimal(9.1));
		when(cuentaRepository.consultarCuenta(nombreCliente)).thenReturn(newCuenta);
	}

	@Given("se obtiene la oficina de atencion cercana a {string}")
	public void se_obtiene_la_oficina_de_atencion_cercana_a(String ubicacion) {
		String oficina="oficina de atencion";
		when(oficinaAtencionRepository.obtenerOficinaAtencionPorUbicacion(ubicacion)).thenReturn(oficina);
	}

	@When("{string} intenta asociar una oficina de atencion cercana a su {string}")
	public void intenta_asociar_una_oficina_de_atencion_cercana_a_su(String nombreCliente, String ubicacion) {
		cuentaService.asociarOficinaAtencionACuenta(nombreCliente, ubicacion);
	}

	@Then("Se asocia exitosamente la {string} mas cercana para {string}.")
	public void se_asocia_exitosamente_la_mas_cercana_para(String oficinaAtencion, String nombreCliente) {
		String oficinaMock="oficina de atencion";
		String clienteMock="Maria";
		verify(cuentaRepository).asociarOficinaAtencionCercana(clienteMock,oficinaMock);
		assertEquals(oficinaAtencion, oficinaMock);
		assertEquals(nombreCliente, clienteMock);
	}
}

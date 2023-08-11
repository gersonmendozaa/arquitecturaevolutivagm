Feature: Crear cuenta digital
Como cliente
Quiero crear una cuenta digital
Para hacer uso de los servicios del banco.

 
  Scenario: Crear una cuenta digital que pasó validación biométrica y tiene contrato firmado.
    Given "Alexander" completó el proceso de validación biométrica 
    And "Alexander" firmó el contrato
    And "Alexander" No tiene cuenta creada previamente en el banco.
    When "Alexander" intenta crear la cuenta.
    Then Se crea exitosamente la cuenta en el core bancario para "Alexander" con saldo 0.
    And Se crea la cuenta en el banco central para "Alexander".

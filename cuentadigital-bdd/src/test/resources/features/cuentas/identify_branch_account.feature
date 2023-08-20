Feature: Identificar sucursal de atención al cliente
Como cliente
Quiero que el sistema me identifique la sucursal de atención al cliente mas cercana a partir de mi ubicación
Para hacer uso de los servicios del banco.

 
  Scenario: Asociar una oficina de atención a una cuenta ya creada
    Given "Maria" completó el proceso de creación de cuenta 
    And los datos de "ubicacion" son validos
    And se obtiene la oficina de atencion cercana a "ubicacion"
    When "Maria" intenta identificar una oficina de atencíon cercana a su "ubicacion"
    Then Se asocia exitosamente la "oficina de atencion" mas cercana para "Maria".
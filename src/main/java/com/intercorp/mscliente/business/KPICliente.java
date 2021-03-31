package com.intercorp.mscliente.business;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "KPICliente", description = "KPI del cliente")
public class KPICliente {

    @Schema(name = "promedioEdad", description = "Promedio de la edad de los clientes", example = "42")
    @JsonProperty("promedio-edad")
    private double promedioEdad;

    @Schema(name = "desviacionEstandar", description = "Desviacion estandar de la edad de los clientes", example = "2")
    @JsonProperty("desviacion-estandar")
    private double desviacionEstandar;

}

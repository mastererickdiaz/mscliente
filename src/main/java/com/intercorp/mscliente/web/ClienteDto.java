package com.intercorp.mscliente.web;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

    @NotBlank
    @Size(max = 255)
    private String nombre;

    @NotBlank
    @Size(max = 255)
    private String apellido;

    @NotNull
    @Min(0)
    @Max(100)
    private Integer edad;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("fecha-nacimiento")
    private Date fechaNacimiento;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("fecha-muerte")
    private Date fechaMuerte;
}

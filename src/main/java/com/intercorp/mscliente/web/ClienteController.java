package com.intercorp.mscliente.web;

import javax.validation.Valid;

import com.intercorp.mscliente.business.IClienteService;
import com.intercorp.mscliente.business.KPICliente;
import com.intercorp.mscliente.config.ApplicationProperties;
import com.intercorp.mscliente.model.entity.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@OpenAPIDefinition(info = @Info(title = "Microservicio Cliente", description = "Servicio para crear, listar y calcular los clientes", version = "1.0.0", contact = @Contact(name = "Erick Diaz", email = "erickdiazc@outlook.es")))
public class ClienteController {

        @Autowired
        private ApplicationProperties applicationProperties;

        @Autowired
        private IClienteService clienteService;

        @PostMapping(path = "/creacliente")
        @Operation(method = "POST", summary = "Crea un cliente")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "La peticion se proceso correctamente", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Cliente.class)) }),
                        @ApiResponse(responseCode = "400", description = "Datos incorrectos", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "No se encontro el recurso solicitado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Error al intentar actualizar el recurso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "503", description = "El servicio no se encuentra disponible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
        public void crearCliente(@Valid @RequestBody ClienteDto cliente) {
                clienteService.crearCliente(cliente);
        }

        @GetMapping(path = "/clientes")
        @Operation(method = "GET", summary = "Lista de clientes", description = "Lista de clientes mas fecha probable de muerte")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "La peticion se proceso correctamente", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = Cliente.class))) }),
                        @ApiResponse(responseCode = "400", description = "Datos incorrectos", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "No se encontro el recurso solicitado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Error al intentar actualizar el recurso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "503", description = "El servicio no se encuentra disponible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
        public Iterable<Cliente> listClientes() {
                // log.info(applicationProperties.getPrefix());
                return clienteService.listClientes();
        }

        @GetMapping(path = "/kpi-clientes")
        @Operation(method = "GET", summary = "Indicador clave de rendimiento", description = "Muestra el promedio, desviacion estandar")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "La peticion se proceso correctamente", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = KPICliente.class)) }),
                        @ApiResponse(responseCode = "400", description = "Datos incorrectos", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "404", description = "No se encontro el recurso solicitado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "500", description = "Error al intentar actualizar el recurso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                        @ApiResponse(responseCode = "503", description = "El servicio no se encuentra disponible", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
        public KPICliente kpiClientes() {
                return clienteService.kpiClientes();
        }
}

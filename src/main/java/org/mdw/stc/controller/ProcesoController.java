package org.mdw.stc.controller;

import java.text.ParseException;

import org.mdw.stc.entity.exception.TSException;
import org.mdw.stc.entity.log.LogEntity;
import org.mdw.stc.entity.response.RPEntity;
import org.mdw.stc.entity.response.TSEntity;
import org.mdw.stc.entity.sap.SapEntity;
import org.mdw.stc.service.LogService;
import org.mdw.stc.service.ProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("${app.property.env}/STC")
public class ProcesoController {

	@Autowired
	ProcesoService proServi;
	@Autowired
	LogService logService;

	@ApiOperation(value = "Descarga masiva de transacciones recepción al granel.")
	@PostMapping(value = { "consultaRecepcionGranel",
			"consultaRecepcionGranel/" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<TSEntity> descargaRecepcionGranel(@RequestBody SapEntity sapEntity,
			@RequestAttribute("log") LogEntity log) throws TSException, ParseException, Exception {
		RPEntity et = this.proServi.descargaRecepcionGranel(sapEntity);
		log.setEndPoint("consultaRecepcionGranel");
		log.setMethod("consultaRecepcionGranel");
		log.setInput(sapEntity);
		log.setOutput(et.ts);
		this.logService.logEndpoint(log);
		return ResponseEntity.status(et.codeError).body(et.ts);
	}

	@ApiOperation(value = "Descarga masiva de transacciones recepción de cilindros.")
	@PostMapping(value = { "consultaRecepcionCilindros",
			"consultaRecepcionCilindros/" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<TSEntity> descargaRecepcionCilindros(@RequestBody SapEntity sapEntity,
			@RequestAttribute("log") LogEntity log) throws TSException, ParseException, Exception {
		RPEntity et = this.proServi.descargaRecepcionCilindros(sapEntity);
		log.setEndPoint("consultaRecepcionCilindros");
		log.setMethod("consultaRecepcionCilindros");
		log.setInput(sapEntity);
		log.setOutput(et.ts);
		this.logService.logEndpoint(log);
		return ResponseEntity.status(et.codeError).body(et.ts);
	}

	@ApiOperation(value = "Descarga masiva de transacciones despacho planta.")
	@PostMapping(value = { "consultaDespachoPlanta",
			"consultaDespachoPlanta/" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<TSEntity> descargaDespachoPlanta(@RequestBody SapEntity sapEntity,
			@RequestAttribute("log") LogEntity log) throws TSException, ParseException, Exception {
		RPEntity et = this.proServi.descargaDespachoPlanta(sapEntity);
		log.setEndPoint("consultaDespachoPlanta");
		log.setMethod("consultaDespachoPlanta");
		log.setInput(sapEntity);
		log.setOutput(et.ts);
		this.logService.logEndpoint(log);
		return ResponseEntity.status(et.codeError).body(et.ts);
	}

	@ApiOperation(value = "Descarga masiva de transacciones de ventas.")
	@PostMapping(value = { "consultaVentas",
			"consultaVentas/" }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json;charset=UTF-8")
	public ResponseEntity<TSEntity> descargaVentas(@RequestBody SapEntity sapEntity,
			@RequestAttribute("log") LogEntity log) throws TSException, ParseException, Exception {
		RPEntity et = this.proServi.descargaVentas(sapEntity);
		log.setEndPoint("consultaVentas");
		log.setMethod("consultaVentas");
		log.setInput(sapEntity);
		log.setOutput(et.ts);
		this.logService.logEndpoint(log);
		return ResponseEntity.status(et.codeError).body(et.ts);
	}

//	falta metodo consultar parche

}

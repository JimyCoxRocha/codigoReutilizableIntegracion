package org.mdw.stc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.mdw.stc.entity.exception.TSException;
import org.mdw.stc.entity.mongo.MIDModuloEntity;
import org.mdw.stc.entity.response.RPEntity;
import org.mdw.stc.entity.sap.ClsRespuesta;
import org.mdw.stc.entity.sap.Parametro;
import org.mdw.stc.entity.sap.SapCommunicationEntity;
import org.mdw.stc.entity.sap.SapEntity;
import org.mdw.stc.repository.sap.SapAuthentication;
import org.mdw.stc.repository.sap.SapConnection;
import org.mdw.stc.service.sap.SapMethodsService;
import org.mdw.stc.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.sap.conn.jco.JCoDestination;

import controller.ReaderController;
import controller.UserSapController;
import entities.UserSapEntity;
import entities.UserSapFileException;

@Service
public class ProcesoService {

	@Autowired
	SapAuthentication sapAuth;

	public RPEntity descargaRecepcionGranel(SapEntity sapEntity) {
		try {
			
			//Puedo eliminar la entidad "MIDModuloEntity mod" dado que solo
			//se encarga de las consultas y traer información del mongo. 
			//Además, valida que los datos del servicio le corresponda.
			//Todo eso ya hago con el .JAR
			UserSapEntity user = UserSapController.getUserSap(sapEntity.Usuario.Codigo);
			Map<String, String> fieldsServices = new HashMap<>();
			fieldsServices.put("nameMethod", "consultaRecepcionGranel");
			fieldsServices.put("status", "A");
			UserSapController.getServiceByFields(fieldsServices, user);
			
			
			//service = this.servicioRepo.findByNombreMetodoAndEstado(method, "A");
			
			
			
//			MIDModuloEntity mod = this.sapAuth.validate("consultaRecepcionGranel", sapEntity);
//			SapCommunicationEntity sapCommunicationEntity = new Gson().fromJson(mod.getConexionSAP(),
//					SapCommunicationEntity.class);
			
			
			SapConnection sapConn = new SapConnection(user.getComunication());
			JCoDestination conn = sapConn.connection();

			ArrayList<String> tables_output = new ArrayList<>();
			tables_output.add("STC");
			tables_output.add("REPORTADO");
			tables_output.add("CONTRAPARTE");
			tables_output.add("PRODUCTO");

			ArrayList<Parametro> listParameter = new ArrayList<>();
			listParameter.add(new Parametro("RETURN", "TU"));

			SapMethodsService sapMethod = new SapMethodsService(conn);
			ClsRespuesta r = sapMethod.create("ZMID_RFC_RECEPCION_GRANEL").addParameterList(sapEntity.Parametro)
					.runCommand(tables_output, listParameter);

			return RPEntity.done(Helpers.refactJCoTable(r));
		}catch(UserSapFileException e) {
			return RPEntity.fail(e.getMessage(), e.getCode().getCode());
		} catch (TSException e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), e.getCode().getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), null);
		}
	}

	public RPEntity descargaRecepcionCilindros(SapEntity sapEntity) {
		try {
			MIDModuloEntity mod = this.sapAuth.validate("consultaRecepcionCilindros", sapEntity);
			SapCommunicationEntity sapCommunicationEntity = new Gson().fromJson(mod.getConexionSAP(),
					SapCommunicationEntity.class);
			SapConnection sapConn = new SapConnection(sapCommunicationEntity);
			JCoDestination conn = sapConn.connection();

			ArrayList<String> tables_output = new ArrayList<>();
			tables_output.add("STC");
			tables_output.add("REPORTADO");
			tables_output.add("CONTRAPARTE");
			tables_output.add("PRODUCTO");

			ArrayList<Parametro> listParameter = new ArrayList<>();
			listParameter.add(new Parametro("RETURN", "TU"));

			SapMethodsService sapMethod = new SapMethodsService(conn);
			ClsRespuesta r = sapMethod.create("ZMID_RFC_CILINDROS").addParameterList(sapEntity.Parametro)
					.runCommand(tables_output, listParameter);

			return RPEntity.done(Helpers.refactJCoTable(r));
		} catch (TSException e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), e.getCode().getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), null);
		}
	}

	public RPEntity descargaDespachoPlanta(SapEntity sapEntity) {
		try {
			MIDModuloEntity mod = this.sapAuth.validate("consultaDespachoPlanta", sapEntity);
			SapCommunicationEntity sapCommunicationEntity = new Gson().fromJson(mod.getConexionSAP(),
					SapCommunicationEntity.class);
			SapConnection sapConn = new SapConnection(sapCommunicationEntity);
			JCoDestination conn = sapConn.connection();

			ArrayList<String> tables_output = new ArrayList<>();
			tables_output.add("STC");
			tables_output.add("REPORTADO");
			tables_output.add("CONTRAPARTE");
			tables_output.add("PRODUCTO");

			ArrayList<Parametro> listParameter = new ArrayList<>();
			listParameter.add(new Parametro("RETURN", "TU"));

			SapMethodsService sapMethod = new SapMethodsService(conn);
			ClsRespuesta r = sapMethod.create("ZMID_RFC_DESPACHO_PLANTA").addParameterList(sapEntity.Parametro)
					.runCommand(tables_output, listParameter);

			return RPEntity.done(Helpers.refactJCoTable(r));
		} catch (TSException e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), e.getCode().getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), null);
		}
	}

	public RPEntity descargaVentas(SapEntity sapEntity) {
		try {
			MIDModuloEntity mod = this.sapAuth.validate("consultaVentas", sapEntity);
			SapCommunicationEntity sapCommunicationEntity = new Gson().fromJson(mod.getConexionSAP(),
					SapCommunicationEntity.class);
			SapConnection sapConn = new SapConnection(sapCommunicationEntity);
			JCoDestination conn = sapConn.connection();

			ArrayList<String> tables_output = new ArrayList<>();
			tables_output.add("STC");
			tables_output.add("REPORTADO");
			tables_output.add("PRODUCTO");

			ArrayList<Parametro> listParameter = new ArrayList<>();
			listParameter.add(new Parametro("RETURN", "TU"));

			SapMethodsService sapMethod = new SapMethodsService(conn);
			ClsRespuesta r = sapMethod.create("ZMID_RFC_VENTAS").addParameterList(sapEntity.Parametro)
					.runCommand(tables_output, listParameter);

			return RPEntity.done(Helpers.refactJCoTable(r));
		} catch (TSException e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), e.getCode().getCode());
		} catch (Exception e) {
			// TODO: handle exception
			return RPEntity.fail(e.getMessage(), null);
		}
	}
}

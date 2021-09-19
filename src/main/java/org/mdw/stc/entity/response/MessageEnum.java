package org.mdw.stc.entity.response;

public enum MessageEnum {

	ERROR_SAP_NULO("Error: La instancia de conexión a SAP es nulo, por favor reintente nuevamente."),
	ERROR_ADD_PARAMS("Error: No se pudo establecer parametros para el rfc, por favor intente nuevamente."),
	ERROR_RUN_RFC("Error al consultar rfc, por favor revise nuevamente."),
	ERROR_CLOSE_SAP("Error: No se pudo cerrar conexión, instancia null, por favor intente nuevamente."),
	ERROR_CONN_SAP("Error: No se pudo conectar con sap, por favor intente nuevamente."),
	ERROR_RUN("Error: No se puede ejecutar el rfc, porque no se encontro instanciado, por favor intente nuevamente");

	private String message;

	MessageEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}

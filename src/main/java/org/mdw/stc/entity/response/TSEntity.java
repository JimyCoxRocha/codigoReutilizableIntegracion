package org.mdw.stc.entity.response;

import java.io.Serializable;

import org.mdw.stc.util.Helpers;

import com.google.gson.JsonObject;

public class TSEntity implements Serializable {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public boolean exiteError;
	public boolean existenRegistros;
	public String CodigoMensaje;
	public String Mensaje;
	public String Data;
	public String Output;
	public int numRegistros;

	public static TSEntity ts() {
		return new TSEntity();
	}

	public static TSEntity done(String data, String output) {
		TSEntity a = new TSEntity();
		a.exiteError = false;
		a.existenRegistros = data.toString().trim().isEmpty() ? false : true;
		a.Data = data;
		a.Output = output;
		a.Mensaje = "";
		return a;
	}

	public static TSEntity done(Object data) {
		TSEntity a = new TSEntity();
		if (data != null && !data.toString().trim().isEmpty()) {
			a.Data = ((JsonObject) Helpers.toJson(data)).toString();
			a.existenRegistros = true;
		}

		return a;
	}

	public static TSEntity fail(String message) {
		TSEntity a = new TSEntity();
		a.exiteError = true;
		a.Mensaje = message;
		return a;
	}

	public TSEntity done(TSEntity et) {
		if (et.Data != null) {
			et.Data = et.Data.toString().trim().isEmpty() ? null : et.Data;
			et.existenRegistros = et.Data.toString().trim().isEmpty() ? false : true;
		}
		return et;
	}
}

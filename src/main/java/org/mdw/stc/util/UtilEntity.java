package org.mdw.stc.util;

import org.mdw.stc.entity.sap.Parametro;

public class UtilEntity {

	public static Parametro set(String Nombre) {
		Parametro p = new Parametro(Nombre, "ST");
		return p;
	}

	public static Parametro set(String Nombre, String TipoDato) {
		Parametro p = new Parametro(Nombre, TipoDato, "");
		return p;
	}

	public static Parametro set(String Nombre, String TipoDato, String Valor) {
		Parametro p = new Parametro(Nombre, TipoDato, Valor);
		return p;
	}

}

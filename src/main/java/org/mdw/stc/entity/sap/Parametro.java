package org.mdw.stc.entity.sap;

import java.io.Serializable;

public class Parametro implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String Nombre = "";
	public String Valor = "";
	public String TipoDato = "";
	
	public Parametro() {}
	
	public Parametro(String Nombre, String TipoDato) {
		this.Nombre = Nombre;
		this.TipoDato = TipoDato;
	}
	
	public Parametro(String Nombre, String TipoDato, String Valor) {
		this.Nombre = Nombre;
		this.TipoDato = TipoDato;
		this.Valor = Valor;
	}	
}

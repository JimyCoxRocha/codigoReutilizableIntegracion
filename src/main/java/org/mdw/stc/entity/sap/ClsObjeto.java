package org.mdw.stc.entity.sap;

import java.io.Serializable;

public class ClsObjeto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String Codigo = "";	  
	public String Valor = "";
	
	public String getCodigo() {
		return Codigo;
	}
	
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	
	public String getValor() {
		return Valor;
	}
	
	public void setValor(String valor) {
		Valor = valor;
	}
	
}

package org.mdw.stc.entity.sap;

import java.io.Serializable;
import java.util.ArrayList;

public class SapEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Usuario Usuario;
	public ArrayList<Parametro> Parametro;
	public ArrayList<ParametroTabla> ParametroTabla;
	public String sToken = "";

}

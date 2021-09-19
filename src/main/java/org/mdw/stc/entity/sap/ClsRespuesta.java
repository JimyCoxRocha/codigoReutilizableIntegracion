package org.mdw.stc.entity.sap;

import java.io.Serializable;
import java.util.ArrayList;

import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class ClsRespuesta implements Serializable{

	private static final long serialVersionUID = 1L;
	  
	public ArrayList<JCoTable> LstTablas = new ArrayList<>();
	  
	public ArrayList<Parametro> ParametroOut = new ArrayList<>();
	  
	public ArrayList<JCoStructure> LstEstructures = new ArrayList<>();
	
}

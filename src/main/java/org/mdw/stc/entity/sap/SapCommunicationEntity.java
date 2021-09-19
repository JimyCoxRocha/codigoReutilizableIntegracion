package org.mdw.stc.entity.sap;

import java.io.Serializable;

public class SapCommunicationEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Usuario;
	private String Password;
	private String JCO_SYSNR;
	private String JCO_CLIENT;
	private String Servidor;
	private String DESTINATION_NAME1;
	
	public String getUsuario() {
		return Usuario;
	}
	
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	
	public String getPassword() {
		return Password;
	}
	
	public void setPassword(String password) {
		Password = password;
	}
	
	public String getJCO_SYSNR() {
		return JCO_SYSNR;
	}
	
	public void setJCO_SYSNR(String jCO_SYSNR) {
		JCO_SYSNR = jCO_SYSNR;
	}
	
	public String getJCO_CLIENT() {
		return JCO_CLIENT;
	}
	
	public void setJCO_CLIENT(String jCO_CLIENT) {
		JCO_CLIENT = jCO_CLIENT;
	}
	
	public String getServidor() {
		return Servidor;
	}
	
	public void setServidor(String servidor) {
		Servidor = servidor;
	}
	
	public String getDESTINATION_NAME1() {
		return DESTINATION_NAME1;
	}
	
	public void setDESTINATION_NAME1(String dESTINATION_NAME1) {
		DESTINATION_NAME1 = dESTINATION_NAME1;
	}
}

package org.mdw.stc.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MID_Modulo")
public class MIDModuloEntity {

	@Id
	String id = "";
	String codigo = "";
	String conexionSAP = "";
	Boolean debug = false;
	String descripcion = "";
	String estado = "";
	Boolean generaToken = false;
	String sigla = "";
	String tiempoToken = "";		
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getConexionSAP() {
		return conexionSAP;
	}
	public void setConexionSAP(String conexionSAP) {
		this.conexionSAP = conexionSAP;
	}
	public Boolean getDebug() {
		return debug;
	}
	public void setDebug(Boolean debug) {
		this.debug = debug;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Boolean getGeneraToken() {
		return generaToken;
	}
	public void setGeneraToken(Boolean generaToken) {
		this.generaToken = generaToken;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getTiempoToken() {
		return tiempoToken;
	}
	public void setTiempoToken(String tiempoToken) {
		this.tiempoToken = tiempoToken;
	}
}

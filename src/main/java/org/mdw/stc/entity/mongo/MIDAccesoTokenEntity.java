package org.mdw.stc.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MID_AccesoToken")
public class MIDAccesoTokenEntity {

	@Id
	String id = "";
	String usuario = "";
	String token = "";
	String fechagenera = "";
	String fechacaduca = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFechagenera() {
		return fechagenera;
	}

	public void setFechagenera(String fechagenera) {
		this.fechagenera = fechagenera;
	}

	public String getFechacaduca() {
		return fechacaduca;
	}

	public void setFechacaduca(String fechacaduca) {
		this.fechacaduca = fechacaduca;
	}

}

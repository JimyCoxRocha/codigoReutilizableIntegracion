package org.mdw.stc.entity.mongo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@Document("MID_LogErrorV2")
public class MIDLogErrorEntity {

	@Id
	private String id;
	private Date fecha;
	private String usuario;
	private Map<String, Object> data;
	private String metodo;

	@JsonAnySetter
	public void add(String key, Object value) {
		if (null == data) {
			data = new HashMap<>();
		}
		data.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, Object> get() {
		return data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}

}

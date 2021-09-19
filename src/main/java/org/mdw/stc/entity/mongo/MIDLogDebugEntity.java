package org.mdw.stc.entity.mongo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@Document("MID_LogDebugV2")
public class MIDLogDebugEntity {

	@Id
	String id;
	String metodo;
	Date fecha;
	String usuario;
	Map<String, Object> input;
	Map<String, Object> output;

	@JsonAnySetter
	public void add(String key, Object value) {
		if (null == input) {
			input = new HashMap<>();
		}
		input.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, Object> get() {
		return input;
	}

	@JsonAnySetter
	public void addOutput(String key, Object value) {
		if (null == output) {
			output = new HashMap<>();
		}
		output.put(key, value);
	}

	@JsonAnyGetter
	public Map<String, Object> getOutput() {
		return output;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMetodo() {
		return metodo;
	}

	public void setMetodo(String metodo) {
		this.metodo = metodo;
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

}

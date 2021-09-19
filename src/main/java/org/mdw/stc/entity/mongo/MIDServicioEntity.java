package org.mdw.stc.entity.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("MID_Servicio")
public class MIDServicioEntity {

	@Id
	String id;
	String codigo;
	String descripcion;
	String estado;
	String metodourl;
	String nombreMetodo;
	String proyectourl;
	Boolean requiereToken = false;
	String serviciourl;
	String tipo;
	
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
	public String getMetodourl() {
		return metodourl;
	}
	public void setMetodourl(String metodourl) {
		this.metodourl = metodourl;
	}
	public String getNombreMetodo() {
		return nombreMetodo;
	}
	public void setNombreMetodo(String nombreMetodo) {
		this.nombreMetodo = nombreMetodo;
	}
	public String getProyectourl() {
		return proyectourl;
	}
	public void setProyectourl(String proyectourl) {
		this.proyectourl = proyectourl;
	}
	public Boolean getRequiereToken() {
		return requiereToken;
	}
	public void setRequiereToken(Boolean requiereToken) {
		this.requiereToken = requiereToken;
	}
	public String getServiciourl() {
		return serviciourl;
	}
	public void setServiciourl(String serviciourl) {
		this.serviciourl = serviciourl;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
		
}

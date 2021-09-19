package org.mdw.stc.entity.mongo;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class DashboardEntity {

	@Id
	String id;
	String metodo;
	Long total;
	Date fecha;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
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

}

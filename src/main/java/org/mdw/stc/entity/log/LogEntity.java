package org.mdw.stc.entity.log;

import org.mdw.stc.entity.response.TSEntity;
import org.mdw.stc.entity.sap.SapEntity;

public class LogEntity {
	private String _id;
	private String _rev;
	private String dateInput;
	private String proyectName;
	private String pathController;
	private String endPoint;
	private String method;
	private SapEntity input;
	private TSEntity output;
	private String dateOutput;
	private String ipApplicationClient;

	public String getId() {
		return _id;
	}

	public void setId(String id) {
		this._id = id;
	}

	public String get_rev() {
		return _rev;
	}

	public void set_rev(String _rev) {
		this._rev = _rev;
	}

	public String getDateInput() {
		return dateInput;
	}

	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}

	public String getProyectName() {
		return proyectName;
	}

	public void setProyectName(String proyectName) {
		this.proyectName = proyectName;
	}

	public String getPathController() {
		return pathController;
	}

	public void setPathController(String pathController) {
		this.pathController = pathController;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public SapEntity getInput() {
		return input;
	}

	public void setInput(SapEntity input) {
		this.input = input;
	}

	public TSEntity getOutput() {
		return output;
	}

	public void setOutput(TSEntity output) {
		this.output = output;
	}

	public String getDateOutput() {
		return dateOutput;
	}

	public void setDateOutput(String dateOutput) {
		this.dateOutput = dateOutput;
	}

	public String getIpApplicationClient() {
		return ipApplicationClient;
	}

	public void setIpApplicationClient(String ipApplicationClient) {
		this.ipApplicationClient = ipApplicationClient;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

//	public LogEntity(String dateInput, String proyectName, String pathController, String endPoint, String method,
//			SapEntity input, TSEntity output, String dateOutput, String ipApplicationClient) {
//		this.dateInput = dateInput;
//		this.proyectName = proyectName;
//		this.pathController = pathController;
//		this.endPoint = endPoint;
//		this.method = method;
//		this.input = input;
//		this.output = output;
//		this.dateOutput = dateOutput;
//		this.ipApplicationClient = ipApplicationClient;
//	}

	public LogEntity() {
	};
}
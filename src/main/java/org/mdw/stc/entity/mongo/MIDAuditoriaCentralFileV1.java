package org.mdw.stc.entity.mongo;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

@Document("MID_Auditoria_CentralFile")
public class MIDAuditoriaCentralFileV1 {

	@Id
	String id="";
	String CTC="";
    String BLDAT_ORIGIN ="";
    String BLDAT ="";
    String RCDAT_ORIGIN ="";
    String RCDAT ="";
    String XBLNR ="";
    String EBELN ="";
    String WRBTR ="";
    String SGTXT ="";
    String WAERS ="";
    String ZBD1T ="";
    String HORA_LLAMADA ="";
    String FECHA_LLAMADA ="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBLDAT_ORIGIN() {
		return BLDAT_ORIGIN;
	}
	public void setBLDAT_ORIGIN(String bLDAT_ORIGIN) {
		BLDAT_ORIGIN = bLDAT_ORIGIN;
	}
	public String getBLDAT() {
		return BLDAT;
	}
	public void setBLDAT(String bLDAT) {
		BLDAT = bLDAT;
	}
	public String getRCDAT_ORIGIN() {
		return RCDAT_ORIGIN;
	}
	public void setRCDAT_ORIGIN(String rCDAT_ORIGIN) {
		RCDAT_ORIGIN = rCDAT_ORIGIN;
	}
	public String getRCDAT() {
		return RCDAT;
	}
	public void setRCDAT(String rCDAT) {
		RCDAT = rCDAT;
	}
	public String getXBLNR() {
		return XBLNR;
	}
	public void setXBLNR(String xBLNR) {
		XBLNR = xBLNR;
	}
	public String getEBELN() {
		return EBELN;
	}
	public void setEBELN(String eBELN) {
		EBELN = eBELN;
	}
	public String getWRBTR() {
		return WRBTR;
	}
	public void setWRBTR(String wRBTR) {
		WRBTR = wRBTR;
	}
	public String getSGTXT() {
		return SGTXT;
	}
	public void setSGTXT(String sGTXT) {
		SGTXT = sGTXT;
	}
	public String getWAERS() {
		return WAERS;
	}
	public void setWAERS(String wAERS) {
		WAERS = wAERS;
	}
	public String getZBD1T() {
		return ZBD1T;
	}
	public void setZBD1T(String zBD1T) {
		ZBD1T = zBD1T;
	}
	public String getHORA_LLAMADA() {
		return HORA_LLAMADA;
	}
	public void setHORA_LLAMADA(String hORA_LLAMADA) {
		HORA_LLAMADA = hORA_LLAMADA;
	}
	public String getFECHA_LLAMADA() {
		return FECHA_LLAMADA;
	}
	public void setFECHA_LLAMADA(String fECHA_LLAMADA) {
		FECHA_LLAMADA = fECHA_LLAMADA;
	}
	public String getCTC() {
		return CTC;
	}
	public void setCTC(String cTC) {
		CTC = cTC;
	}
    
    
}

package org.mdw.stc.repository.sap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.mdw.stc.entity.exception.CodeEnum;
import org.mdw.stc.entity.exception.TSException;
import org.mdw.stc.entity.response.MessageEnum;
import org.mdw.stc.entity.sap.SapCommunicationEntity;
import org.mdw.stc.util.Conf;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;

public class SapConnection {

	private Properties prop;
	JCoDestination conn;
	ComunicationEntity sapEntity;

	public SapConnection(ComunicationEntity sapEntity) {
		this.prop = new Properties();
		this.conn = null;
		this.sapEntity = sapEntity;
	}

	public JCoDestination connection() throws TSException {
		try {
			prop.setProperty("jco.client.ashost", this.sapEntity.getServer());
			prop.setProperty("jco.client.sysnr", this.sapEntity.getJcoSysnr());
			prop.setProperty("jco.client.client", this.sapEntity.getJcoClient());
			prop.setProperty("jco.client.user", this.sapEntity.getUser());
			prop.setProperty("jco.client.passwd", this.sapEntity.getPassword());
			prop.setProperty("jco.client.lang", "es");
			System.setProperty("jco.destinations.dir", Conf.path_main);
			String fileName = String.valueOf(System.getProperty("jco.destinations.dir")) + "/"
					+ this.sapEntity.getDESTINATION_NAME1() + ".jcoDestination";
			File destCfg = new File(fileName);
			try {
				FileOutputStream fos = new FileOutputStream(destCfg, false);
				try {
					prop.store(fos, "for tests only !");
					this.conn = JCoDestinationManager.getDestination(this.sapEntity.getDestinationName());
				} catch (IOException e1) {
					throw new TSException(e1.getMessage(), CodeEnum.NOT_FOUND);
				}
				try {
					fos.close();
				} catch (IOException e) {
					throw new TSException(e.getMessage(), CodeEnum.NOT_FOUND);
				}
			} catch (FileNotFoundException e) {
				throw new TSException(e.getMessage(), CodeEnum.NOT_FOUND);
			}

			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_CONN_SAP.getMessage(), CodeEnum.GATEWAY_TIMEOUT);

			this.conn.ping();

			return this.conn;
		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public void close() throws TSException {
		try {
			if (conn == null)
				throw new TSException(MessageEnum.ERROR_CLOSE_SAP.getMessage(), CodeEnum.FORBIDDEN);

			conn = null;
		} catch (TSException e) {
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

}

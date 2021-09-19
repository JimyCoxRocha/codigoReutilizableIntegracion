package org.mdw.stc.service;

import org.mdw.stc.entity.log.LogEntity;
import org.mdw.stc.entity.response.TSEntity;
import org.mdw.stc.util.Helpers;
import org.mdw.stc.util.UtilDate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@Service
public class LogService {

	@Value("${couch.url.dominio}")
	String URL_COUCH;

	public void logEndpoint(LogEntity log) {
		ClientResponse response = null;
		try {
			String fOutput = UtilDate.getDateHour();
			log.setDateOutput(fOutput);
			TSEntity dataLog = Helpers.dataLog(log.getOutput());
			log.setOutput(dataLog);

			JsonObject json = new Gson().toJsonTree(log).getAsJsonObject();

			Client client = Client.create();
			WebResource webResource = client.resource(URL_COUCH + "process" + log.getProyectName().toLowerCase());
			response = webResource.type("application/json").post(ClientResponse.class, json.toString());
			if (response.getStatus() != 200) {
				System.out
						.println("Failed : HTTP error code: " + response.getStatus() + " " + response.getStatusInfo());
			}

//			System.out.println(response.getEntity(String.class));
		} catch (Exception e) {
			System.out.println("Failed : error Exception: " + e.getMessage() + " HTTP status: " + response.getStatus()
					+ response.getStatusInfo());
		}
	}
}

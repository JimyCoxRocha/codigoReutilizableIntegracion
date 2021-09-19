package org.mdw.stc.service.sap;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mdw.stc.entity.exception.CodeEnum;
import org.mdw.stc.entity.exception.TSException;
import org.mdw.stc.entity.response.MessageEnum;
import org.mdw.stc.entity.sap.ClsRespuesta;
import org.mdw.stc.entity.sap.Parametro;
import org.mdw.stc.entity.sap.ParametroTabla;
import org.mdw.stc.util.Helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class SapMethodsService {

	private JCoFunction function;
	private JCoDestination conn;

	public SapMethodsService(JCoDestination conn) {
		this.conn = conn;
		this.function = null;
	}

	public SapMethodsService create(String nameRFC) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			this.function = this.conn.getRepository().getFunction(nameRFC);

			return this;
		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public SapMethodsService add(String key, Object data) throws TSException {
		try {
			try {
				if (this.conn == null)
					throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

				if (this.function == null)
					throw new TSException(MessageEnum.ERROR_ADD_PARAMS.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

				switch (data.getClass().toString()) {
				case "class com.google.gson.JsonObject":
				case "class com.google.gson.JsonArray":
					this.function.getImportParameterList().setValue(key, (JsonElement) data);
					break;
				case "class java.lang.Integer":
					this.function.getImportParameterList().setValue(key, Integer.parseInt(data.toString()));
					break;
				case "class java.lang.Double":
					this.function.getImportParameterList().setValue(key, Double.parseDouble(data.toString()));
					break;
				case "class java.util.HashMap":
				case "class java.util.ArrayList":
					this.function.getImportParameterList().setValue(key, Helpers.toJson(data));
					break;
				case "class java.lang.String":
					this.function.getImportParameterList().setValue(key, data.toString());
					break;
				case "class java.math.BigDecimal":
					Double d = Double.parseDouble(data.toString());
					this.function.getImportParameterList().setValue(key, BigDecimal.valueOf(d));
					break;
				case "class java.util.Date":
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date date = format.parse(data.toString());
					this.function.getImportParameterList().setValue(key, date);
					break;
				default:
					this.function.getImportParameterList().setValue(key, data.toString());
					break;
				}

				return this;
			} catch (ParseException e) {
				// TODO: handle exception
				throw new TSException(e.getMessage(), null);
			} catch (TSException e) {
				// TODO: handle exception
				throw new TSException(e.getMessage(), e.getCode());
			}
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public JCoTable runQuery(String nameTable) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			JCoTable table = null;
			JCoContext.begin(this.conn);
			this.function.execute(this.conn);
			table = this.function.getTableParameterList().getTable(nameTable);
			JCoContext.end(this.conn);

			return table;
		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public JCoFunction runSimpleQueryFunction() throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			JCoContext.begin(this.conn);
			this.function.execute(this.conn);
			JCoContext.end(this.conn);

			return this.function;
		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public void runSimpleQuery() throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			JCoContext.begin(this.conn);
			this.function.execute(this.conn);
			JCoContext.end(this.conn);

		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public ArrayList<JCoTable> runQueryArray(List<String> tables) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			ArrayList<JCoTable> listTables = new ArrayList<>();
			JCoTable table = null;
			JCoContext.begin(this.conn);
			this.function.execute(this.conn);

			for (String parametro : tables) {
				table = this.function.getTableParameterList().getTable(parametro);
				listTables.add(table);
			}

			JCoContext.end(this.conn);

			return listTables;
		} catch (JCoException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	private ClsRespuesta getValue(Parametro parametro, ClsRespuesta respuesta) throws TSException {
		JCoStructure structure = null;
		JCoTable table = null;
		String str;

		switch ((str = parametro.TipoDato).hashCode()) {
		case 2114:
			if (!str.equals("BD"))
				break;
			parametro.Valor = this.function.getExportParameterList().getBigDecimal(parametro.Nombre).toString();
			break;
		case 2173:
			if (!str.equals("DA"))
				break;
			parametro.Valor = this.function.getExportParameterList().getDate(parametro.Nombre).toString();
			break;
		case 2187:
			if (!str.equals("DO"))
				break;
			parametro.Valor = Double.toString(this.function.getExportParameterList().getDouble(parametro.Nombre));
			break;
		case 2341:
			if (!str.equals("IN"))
				break;
			parametro.Valor = Integer.toString(this.function.getExportParameterList().getInt(parametro.Nombre));
			break;
		case 2657:
			if (!str.equals("ST"))
				break;
			parametro.Valor = this.function.getExportParameterList().getString(parametro.Nombre);
			break;
		case 2669:
			if (!str.equals("TA"))
				break;
			table = this.function.getExportParameterList().getTable(parametro.Nombre);
			parametro.Valor = "";
			respuesta.LstTablas.add(table);
			break;
		case 2689:
			if (!str.equals("TU"))
				break;
			structure = this.function.getExportParameterList().getStructure(parametro.Nombre);
			parametro.Valor = "";
			respuesta.LstEstructures.add(structure);
			break;
		}

		respuesta.ParametroOut.add(parametro);
		return respuesta;
	}

	public ClsRespuesta runQueryOutput(String nameTable, ArrayList<Parametro> parametroOut) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			ClsRespuesta respuesta = new ClsRespuesta();
			JCoTable table = null;
			Parametro nuevo = null;

			JCoContext.begin(this.conn);
			this.function.execute(this.conn);
			table = this.function.getTableParameterList().getTable(nameTable);
			respuesta.LstTablas.add(table);

			for (Parametro parametro : parametroOut) {
				nuevo = new Parametro();
				if (parametro == null)
					continue;
				nuevo.Nombre = parametro.Nombre;
				nuevo.TipoDato = parametro.TipoDato;
				respuesta = this.getValue(nuevo, respuesta);
			}

			JCoContext.end(this.conn);
			return respuesta;
		} catch (JCoException e) {
			// TODO: handle exception
			if (this.conn != null)
				try {
					JCoContext.end(this.conn);
				} catch (JCoException e1) {
					// TODO Auto-generated catch block
					throw new TSException(e1.getMessage(), null);
				}
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public ClsRespuesta runCommand(ArrayList<String> tables, ArrayList<Parametro> parametroOut) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			ClsRespuesta respuesta = new ClsRespuesta();
			JCoTable table = null;
			Parametro nuevo = null;
			JCoContext.begin(this.conn);
			this.function.execute(this.conn);

			for (String _table : tables) {
				table = this.function.getTableParameterList().getTable(_table);
				respuesta.LstTablas.add(table);
			}

			for (Parametro parametro : parametroOut) {
				nuevo = new Parametro();

				if (parametro == null)
					continue;
				nuevo.Nombre = parametro.Nombre;
				nuevo.TipoDato = parametro.TipoDato;

				respuesta = getValue(nuevo, respuesta);
			}

			JCoContext.end(this.conn);

			if (respuesta == null)
				throw new TSException(MessageEnum.ERROR_RUN_RFC.getMessage(), CodeEnum.BAD_REQUEST);

			return respuesta;
		} catch (JCoException e) {
			// TODO: handle exception
			if (this.conn != null)
				try {
					JCoContext.end(this.conn);
				} catch (JCoException e1) {
					// TODO Auto-generated catch block
					throw new TSException(e1.getMessage(), null);
				}
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public ClsRespuesta runCommandStructure(ArrayList<String> structure, ArrayList<Parametro> outputParameter)
			throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_RUN.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			ClsRespuesta _respuesta = new ClsRespuesta();
			JCoStructure _structureT = null;
			Parametro _nuevo = null;

			JCoContext.begin(this.conn);
			this.function.execute(this.conn);

			for (String ltable : structure) {
				_structureT = this.function.getTableParameterList().getStructure(ltable);
				_respuesta.LstEstructures.add(_structureT);
			}

			for (Parametro parametro : outputParameter) {
				if (parametro == null)
					continue;

				_nuevo = new Parametro();
				_nuevo.Nombre = parametro.Nombre;
				_nuevo.TipoDato = parametro.TipoDato;
				_respuesta = this.getValue(_nuevo, _respuesta);
			}

			JCoContext.end(this.conn);
			return _respuesta;
		} catch (JCoException e) {
			// TODO: handle exception
			if (this.conn != null)
				try {
					JCoContext.end(this.conn);
				} catch (JCoException e1) {
					// TODO Auto-generated catch block
					throw new TSException(e1.getMessage(), null);
				}
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public SapMethodsService addTableParameter(ArrayList<String> nameTables, ArrayList<ParametroTabla> parametros)
			throws TSException {
		Integer i = 0;
		for (ParametroTabla parametroTabla : parametros) {
			this.addTableParameter(nameTables.get(i), parametroTabla);
			i++;
		}

		return this;
	}

	public SapMethodsService addTableParameter(String nameTable, ArrayList<ParametroTabla> parametros)
			throws TSException {
		for (ParametroTabla parametroTabla : parametros) {
			this.addTableParameter(nameTable, parametroTabla);
		}

		return this;
	}

	public SapMethodsService addTableParameter(String nameTable, ParametroTabla parametro) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_ADD_PARAMS.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			DateFormat format = new SimpleDateFormat("yyyyMMdd");

			JCoTable ORDER_PARTNERS = this.function.getTableParameterList().getTable(nameTable);
			ORDER_PARTNERS.clear();

			Gson gson = new Gson();
			JsonArray arr = gson.fromJson(parametro.Modelo, JsonArray.class);
			JsonArray row = gson.fromJson(parametro.Fila, JsonArray.class);

			for (JsonElement fil : row) {
				JsonObject fila = (JsonObject) fil;
				ORDER_PARTNERS.appendRow();

				for (JsonElement mod : arr) {
					JsonObject modelo = (JsonObject) mod;

					String columna = modelo.get("name").getAsString();
					String tipoDato = modelo.get("tipo").getAsString();
					String str1;

					switch ((str1 = tipoDato).hashCode()) {
					case 2114:
						if (!str1.equals("BD"))
							break;
						ORDER_PARTNERS.setValue(columna, new BigDecimal(fila.get(columna).getAsString()));
						break;
					case 2173:
						if (!str1.equals("DA"))
							break;
						ORDER_PARTNERS.setValue(columna, format.parse(fila.get(columna).getAsString()));
						break;
					case 2187:
						if (!str1.equals("DO"))
							break;
						ORDER_PARTNERS.setValue(columna, Double.parseDouble(fila.get(columna).getAsString()));
						break;
					case 2246:
						if (!str1.equals("FL"))
							break;
						ORDER_PARTNERS.setValue(columna, Float.parseFloat(fila.get(columna).getAsString()));
						break;
					case 2341:
						if (!str1.equals("IN"))
							break;
						ORDER_PARTNERS.setValue(columna, Integer.parseInt(fila.get(columna).getAsString()));
						break;
					case 2657:
						if (!str1.equals("ST"))
							break;
						ORDER_PARTNERS.setValue(columna, fila.get(columna).getAsString());
						break;
					}
				}
			}

			return this;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public SapMethodsService addParameterList(ArrayList<Parametro> listParameter) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_ADD_PARAMS.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			DateFormat format = new SimpleDateFormat("yyyyMMdd");

			for (Parametro parametro : listParameter) {
				if (parametro == null)
					continue;
				String str;
				switch ((str = parametro.TipoDato).hashCode()) {
				case 2114:
					if (!str.equals("BD"))
						continue;
					this.add(parametro.Nombre, new BigDecimal(parametro.Valor));
				case 2173:
					if (!str.equals("DA"))
						continue;
					this.add(parametro.Nombre, format.parse(parametro.Valor));
				case 2187:
					if (!str.equals("DO"))
						continue;
					this.add(parametro.Nombre, Double.valueOf(Double.parseDouble(parametro.Valor)));
				case 2341:
					if (!str.equals("IN"))
						continue;
					this.add(parametro.Nombre, Integer.parseInt(parametro.Valor));
				case 2657:
					if (!str.equals("ST"))
						continue;
					this.add(parametro.Nombre, parametro.Valor);
				}
			}

			return this;
		} catch (NumberFormatException | ParseException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	public SapMethodsService addStructureParameter(String nameTable, ParametroTabla parameter) throws TSException {
		try {
			if (this.conn == null)
				throw new TSException(MessageEnum.ERROR_SAP_NULO.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			if (this.function == null)
				throw new TSException(MessageEnum.ERROR_ADD_PARAMS.getMessage(), CodeEnum.SERVICE_UNAVAILABLE);

			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			JCoStructure ORDER_PARTNERS = this.function.getImportParameterList().getStructure(nameTable);
			ORDER_PARTNERS.clear();

			Gson gson = new Gson();
			JsonArray arr = gson.fromJson(parameter.Modelo, JsonArray.class);
			JsonArray row = gson.fromJson(parameter.Fila, JsonArray.class);

			for (JsonElement fil : row) {
				JsonObject fila = (JsonObject) fil;

				for (JsonElement mod : arr) {
					JsonObject modelo = (JsonObject) mod;

					String columna = modelo.get("name").getAsString();
					String tipoDato = modelo.get("tipo").getAsString();
					String str1;

					switch ((str1 = tipoDato).hashCode()) {
					case 2114:
						if (!str1.equals("BD"))
							break;
						ORDER_PARTNERS.setValue(columna, new BigDecimal(fila.get(columna).getAsString()));
						break;
					case 2173:
						if (!str1.equals("DA"))
							break;
						ORDER_PARTNERS.setValue(columna, format.parse(fila.get(columna).getAsString()));
						break;
					case 2187:
						if (!str1.equals("DO"))
							break;
						ORDER_PARTNERS.setValue(columna, Double.parseDouble(fila.get(columna).getAsString()));
						break;
					case 2246:
						if (!str1.equals("FL"))
							break;
						ORDER_PARTNERS.setValue(columna, Float.parseFloat(fila.get(columna).getAsString()));
						break;
					case 2341:
						if (!str1.equals("IN"))
							break;
						ORDER_PARTNERS.setValue(columna, Integer.parseInt(fila.get(columna).getAsString()));
						break;
					case 2657:
						if (!str1.equals("ST"))
							break;
						ORDER_PARTNERS.setValue(columna, fila.get(columna).getAsString());
						break;
					}
				}
			}

			return this;
		} catch (ParseException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

}

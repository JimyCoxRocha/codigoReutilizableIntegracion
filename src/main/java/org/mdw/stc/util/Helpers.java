package org.mdw.stc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mdw.stc.entity.mongo.Dashboard2Entity;
import org.mdw.stc.entity.response.TSEntity;
import org.mdw.stc.entity.sap.ClsRespuesta;
import org.mdw.stc.entity.sap.Parametro;
import org.mdw.stc.entity.sap.ParametroTabla;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

public class Helpers {

	@SuppressWarnings("deprecation")
	public static JsonElement toJson(String dataRaw) {
		dataRaw = new JsonParser().parse(dataRaw).toString();
		Gson googleJson = new Gson();
		return googleJson.fromJson(dataRaw, JsonElement.class);
	}

	public static JsonElement toJson(Object data) {
		Gson gsonBuilder = new GsonBuilder().create();
		Gson googleJson = new Gson();
		return googleJson.fromJson(gsonBuilder.toJson(data), JsonElement.class);
	}

	public static Object getValue(String key, JsonObject data, String type) {
		Object r = null;
		try {
			switch (type) {
			case "I":
				if (!data.has(key))
					r = 0;
				else if (data.get(key).isJsonNull())
					r = 0;
				else if (data.get(key).getAsString().trim().length() == 0)
					r = 0;
				else
					r = data.get(key).getAsInt();
				break;
			case "D":
				if (!data.has(key))
					r = 0.0;
				else if (data.get(key).isJsonNull())
					r = 0.0;
				else if (data.get(key).getAsString().trim().length() == 0)
					r = 0.0;
				else
					r = data.get(key).getAsDouble();
				break;
			case "B":
				if (!data.has(key))
					r = false;
				else if (data.get(key).isJsonNull())
					r = false;
				else if (data.get(key).getAsString().trim().length() == 0)
					r = false;
				else {
					if (isInteger(data.get(key).getAsString()))
						r = data.get(key).getAsInt() == 1 ? true : false;
					else
						r = Boolean.valueOf(data.get(key).getAsString());
				}
				break;
			default:
				if (!data.has(key) || data.get(key).isJsonNull())
					r = "";
				else
					r = data.get(key).getAsString();
				break;
			}

			return r;
		} catch (Exception e) {
			// TODO: handle exception
			return r;
		}
	}

	public static boolean isInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException ex) {
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public static JsonArray castToJsonArray(ArrayList<HashMap<String, Object>> data) {
		Gson gson = new Gson();
		String r = gson.toJson(data);
		return new JsonParser().parse(r).getAsJsonArray();
	}

	@SuppressWarnings("deprecation")
	public static JsonArray castToJsonArray(Object data) {
		Gson gson = new Gson();
		String r = gson.toJson(data);
		return new JsonParser().parse(r).getAsJsonArray();
	}

	public static ArrayList<Parametro> toArrayList(String data) {
		try {
			Gson gson = new Gson();
			JsonArray params = gson.fromJson(data, JsonArray.class);
			ArrayList<Parametro> parametros = new ArrayList<Parametro>();

			for (JsonElement ele : params) {
				JsonObject ob = (JsonObject) ele;
				Parametro ob_parametro = new Parametro();
				ob_parametro.Nombre = (String) Helpers.getValue("Nombre", ob, "S");
				ob_parametro.TipoDato = (String) Helpers.getValue("TipoDato", ob, "S");
				ob_parametro.Valor = (String) Helpers.getValue("Valor", ob, "S");
				parametros.add(ob_parametro);
			}

//			Type userListType = new TypeToken<ArrayList<Parametro>>() {
//			}.getType();

//			return gson.fromJson(data, userListType);
			return parametros;
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Parametro>();
		}
	}

	public static ArrayList<ParametroTabla> toArrayList(JsonArray parametro_tabla) {
		ArrayList<ParametroTabla> list = null;
		try {
			list = new ArrayList<ParametroTabla>();

			for (JsonElement item : parametro_tabla) {
				JsonObject p = (JsonObject) item;
				ParametroTabla pt = new ParametroTabla();
				pt.Fila = p.has("Fila") && !p.get("Fila").isJsonNull() ? p.get("Fila").getAsString() : "[]";
				pt.Modelo = p.has("Modelo") && !p.get("Modelo").isJsonNull() ? p.get("Modelo").getAsString() : "[]";

				list.add(pt);
			}

		} catch (Exception e) {
			// TODO: handle exception
			list = new ArrayList<ParametroTabla>();
		}
		return list;
	}

	public static ArrayList<Parametro> toArrayList2(JsonArray parametro_tabla) {
		ArrayList<Parametro> list = null;
		try {
			list = new ArrayList<Parametro>();

			for (JsonElement item : parametro_tabla) {
				JsonObject p = (JsonObject) item;
				Parametro pt = new Parametro();
				pt.TipoDato = p.get("TipoDato").getAsString();
				pt.Nombre = p.get("Nombre").getAsString();

				list.add(pt);
			}

		} catch (Exception e) {
			// TODO: handle exception
			list = new ArrayList<Parametro>();
		}
		return list;
	}

	public static TSEntity refactJCoTable(JCoTable _table) throws Exception {
		TSEntity et = new TSEntity();
		JsonObject jo = new JsonObject();
		JsonArray jaCols = new JsonArray();
		JsonArray jaModel = new JsonArray();
		JsonArray ja = new JsonArray();

		try {

			for (int i = 0; i < _table.getNumRows(); i++) {
				JsonObject o = new JsonObject();
				_table.setRow(i);

				JCoFieldIterator iter = _table.getFieldIterator();

				while (iter.hasNextField()) {
					JCoField f = iter.nextField();
					if (i == 0) {
						JsonObject joModel = new JsonObject();

						jaCols.add(f.getName().toLowerCase());
						joModel.addProperty("name", f.getName().toLowerCase());
						jaModel.add(joModel);

					}
					if (_table.getValue(f.getName()) == null) {
						o.addProperty(f.getName().toLowerCase(), "");
					} else {
						o.addProperty(f.getName().toLowerCase(), _table.getValue(f.getName()).toString());
					}
				}
				ja.add(o);

			}

			jo.add("model", jaModel);
			jo.add("rows", ja);
			jo.add("cols", jaCols);

			et.Data = jo.toString();
			et.numRegistros = ja.size();
			et.existenRegistros = et.numRegistros != 0;

			return et;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	public static TSEntity refactJCoTable(ClsRespuesta cls) throws Exception {
		try {
			TSEntity et = new TSEntity();
			JsonArray tables1 = new JsonArray();
			JsonArray tables2 = new JsonArray();
			int i = 0;
			et.numRegistros = 0;

			for (JCoTable table : cls.LstTablas) {
				if (table == null)
					continue;

				if (table.getNumRows() == 0)
					continue;

				if (i == 0) {
					et.numRegistros = table.getNumRows();
					TSEntity ett = refactJCoTable(table);
					if (ett.existenRegistros)
						tables1.add(toJson(ett.Data));
				} else if (i != 0) {
					TSEntity ett = refactJCoTable(table);
					if (ett.existenRegistros)
						tables2.add(toJson(ett.Data));
				}

				i++;
			}

			tables1.add(tables2);

			if (et.numRegistros == 0)
				et.existenRegistros = false;
			else
				et.existenRegistros = true;
			et.Output = new Gson().toJson(cls.ParametroOut);
			et.Data = tables1.toString().equals("[[]]") ? null : tables1.toString();

			return et;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}

	public static TSEntity refactJCoStructure(JCoStructure structure) throws Exception {
		JsonObject jo = new JsonObject();
		try {
			TSEntity et = new TSEntity();
			JsonArray ja = new JsonArray();
			JsonArray jaCols = new JsonArray();
			JsonArray jaModel = new JsonArray();

			for (int i = 0; i < structure.getRecordMetaData().getRecordLength(); i++) {

				JCoFieldIterator iter = structure.getFieldIterator();

				while (iter.hasNextField()) {
					JCoField f = iter.nextField();
					if (i == 0) {
						JsonObject joModel = new JsonObject();
						jaCols.add(f.getName().toLowerCase());
						joModel.addProperty("name", f.getName().toLowerCase());
						jaModel.add(joModel);
					}
				}
			}

			JsonObject o = new JsonObject();
			JCoFieldIterator iter = structure.getFieldIterator();

			while (iter.hasNextField()) {
				JCoField f = iter.nextField();

				if (structure.getValue(f.getName()).toString() == null) {
					o.addProperty(f.getName().toLowerCase(), "");
				} else {
					o.addProperty(f.getName().toLowerCase(), structure.getValue(f.getName()).toString());
				}
			}

			ja.add(o);
			jo.add("rows", ja);
			jo.add("cols", jaCols);
			jo.add("model", jaModel);

			et.Data = jo.toString();
			et.numRegistros = ja.size();

			return et;
		} catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e.getMessage());
		}
	}
//
//	public static TSEntity refactJCoArray(ArrayList<JCoTable> tables) {
//		try {
//			JsonObject res = new JsonObject();
//			String[] keys = new String[] { "data", "output" };
//
//			for (int i = 0; i < tables.size(); i++) {
//				String k = keys[i <= keys.length ? i : keys.length];
//
//				if (i == 0)
//					res.addProperty("registros", tables.get(i).getNumRows());
//
//				res.add(k, new JsonObject());
//
//				if (tables.get(i).getNumRows() > 0) {
//					TSEntity ett = refactJCoTable(tables.get(i));
//					if (ett.error || ett.error_sap)
//						throw new Exception(ett.error_sap ? ett.message_sap : ett.message);
//					JsonObject r = (JsonObject) toJson(ett.data);
//					res.add(k, r.getAsJsonObject("data"));
//				}
//			}
//
//			return TSEntity.done(res);
//		} catch (Exception e) {
//			// TODO: handle exception
//			return TSEntity.fail(e.getMessage());
//		}
//	}
//
//	public static ArrayList<HashMap<String, Object>> toArrayList2(String data) {
//		return new Gson().fromJson(data, new TypeToken<ArrayList<HashMap<String, Object>>>() {
//		}.getType());
//	}
//
//	public static TSEntity refactMultipleTable(ClsRespuesta cls) {
//		try {
//			Integer i = 0;
//			JsonObject response = new JsonObject();
//			JsonArray d = new JsonArray();
//			JsonArray ditem = new JsonArray();
//			TSEntity et = new TSEntity();
//
//			for (JCoTable table : cls.LstTablas) {
//				if (table == null)
//					continue;
//
//				if (table.getNumRows() == 0)
//					continue;
//
//				switch (i) {
//				case 0:
//					et = refactJCoTable(table);
//					if (et.error)
//						throw new Exception(et.message);
//					response = (JsonObject) Helpers.toJson(et.data);
//					break;
//
//				default:
//					et = refactJCoTable(table);
//					if (et.error)
//						throw new Exception(et.message);
//					JsonObject rd = (JsonObject) Helpers.toJson(et.data);
//					ditem.add(rd);
//
//					break;
//				}
//				i++;
//			}
//
//			response.add("tables", ditem);
//			return TSEntity.done(response);
//		} catch (Exception e) {
//			// TODO: handle exception
//			return TSEntity.fail(e.getMessage());
//		}
//	}

	public static JsonObject refactVisor(List<Dashboard2Entity> l) {
		JsonObject r = new JsonObject();
		try {
			for (Dashboard2Entity en : l) {
				r.addProperty(en.get_id(), en.getCount());

			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
		}

		return r;
	}

	public static TSEntity dataLog(TSEntity t) {
		TSEntity dataLog = new TSEntity();
		dataLog.exiteError = t.exiteError;
		dataLog.existenRegistros = t.existenRegistros;
		if (t.CodigoMensaje == null)
			dataLog.CodigoMensaje = "null";
		else
			dataLog.CodigoMensaje = t.CodigoMensaje;

		if (t.Mensaje == null)
			dataLog.Mensaje = "null";
		else
			dataLog.Mensaje = t.Mensaje;

		if (t.Data == null)
			dataLog.Data = "null";
		else
			dataLog.Data = t.Data;

		if (t.Output == null)
			dataLog.Output = "null";
		else
			dataLog.Output = t.Output;

		dataLog.numRegistros = t.numRegistros;
		return dataLog;
	}
}

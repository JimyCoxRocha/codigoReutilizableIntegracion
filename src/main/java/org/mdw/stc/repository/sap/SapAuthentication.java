package org.mdw.stc.repository.sap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mdw.stc.entity.exception.CodeEnum;
import org.mdw.stc.entity.exception.TSException;
import org.mdw.stc.entity.mongo.MIDAccesoEntity;
import org.mdw.stc.entity.mongo.MIDAccesoTokenEntity;
import org.mdw.stc.entity.mongo.MIDModuloEntity;
import org.mdw.stc.entity.mongo.MIDServicioEntity;
import org.mdw.stc.entity.sap.SapEntity;
import org.mdw.stc.repository.mongo.MIDAccesoRepository;
import org.mdw.stc.repository.mongo.MIDAccesoTokenRepository;
import org.mdw.stc.repository.mongo.MIDModuloRepository;
import org.mdw.stc.repository.mongo.MIDServicioRepository;
import org.mdw.stc.util.Helpers;
import org.mdw.stc.util.UtilDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

@Repository
public class SapAuthentication {

	@Autowired
	private MIDAccesoRepository accesoRepo;

	@Autowired
	private MIDModuloRepository moduloRepo;

	@Autowired
	private MIDServicioRepository servicioRepo;

	@Autowired
	private MIDAccesoTokenRepository accesoTokenRepo;

	public MIDModuloEntity validate(String method, SapEntity sapEntity) throws TSException {
		try {
			MIDModuloEntity acceso = this.moduloRepo.findByCodigo(sapEntity.Usuario.Codigo);
			if (acceso == null)
				throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

//			Se busca los servicios
			MIDServicioEntity service = this.servicioRepo.findByMetodourlAndEstadoAndServiciourl(method, "A",
					acceso.getSigla());

			if (service == null)
				service = this.servicioRepo.findByNombreMetodoAndEstado(method, "A");

			if (service == null)
				throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

			if ((acceso.getGeneraToken() != null && acceso.getGeneraToken())
					&& (service.getRequiereToken() != null && service.getRequiereToken()))
				withToken(sapEntity, service);
			else
				noToken(sapEntity, service);

			return acceso;
		} catch (ParseException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), null);
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	private JsonArray noToken(SapEntity sapEntity, MIDServicioEntity service) throws TSException {
		try {
			List<MIDAccesoEntity> l = this.accesoRepo.findByCodigoModuloAndCodigoServicio(sapEntity.Usuario.Codigo,
					service.getCodigo());

			if (l.size() == 0)
				throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

			JsonArray listaAcceso = new JsonArray();

			for (MIDAccesoEntity midAccesoEntity : l) {

				JsonObject ob_acceso = (JsonObject) Helpers.toJson(midAccesoEntity);
				ob_acceso.addProperty("permite", true);
				ob_acceso.addProperty("message", "Registro Exitoso.");

				if (!midAccesoEntity.getTipoTransaccion().trim().equals("O")) {

					String dayCurrent = UtilDate.getDayCurrent();
					String[] days = midAccesoEntity.getDias().split("\\,", -1);
					List<String> listDays = Arrays.asList(days);

					if (!listDays.contains(dayCurrent))
						throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

					String[] arrayHorario = midAccesoEntity.getHorario().split("\\-", -1);

					if (arrayHorario.length != 2)
						throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

					if (!UtilDate.betweenTime(arrayHorario[0], arrayHorario[1]))
						throw new TSException("Permiso denegado", CodeEnum.UNAUTHORIZED);
				}

				listaAcceso.add(ob_acceso);
			}

			return listaAcceso;
		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

	private void withToken(SapEntity sapEntity, MIDServicioEntity service) throws TSException, ParseException {
		try {

			if (sapEntity.sToken == null)
				throw new TSException("Permiso denegado.", CodeEnum.BAD_REQUEST);

			if (sapEntity.sToken.trim().isEmpty())
				throw new TSException("Permiso denegado.", CodeEnum.BAD_REQUEST);

			List<MIDAccesoTokenEntity> accesoToken = this.accesoTokenRepo
					.findByUsuarioAndToken(sapEntity.Usuario.Codigo, sapEntity.sToken.trim());

			if (accesoToken == null)
				throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

			Boolean permitir = true;
			SimpleDateFormat _format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			Date currentDate = new Date();
			String _stringDate = _format.format(currentDate);
			currentDate = _format.parse(_stringDate);

			for (MIDAccesoTokenEntity midAccesoTokenEntity : accesoToken) {

				Date _fechaGenera = UtilDate.convertDate(midAccesoTokenEntity.getFechagenera());
				Date _fechaCaduca = UtilDate.convertDate(midAccesoTokenEntity.getFechacaduca());

				if (!currentDate.after(_fechaGenera))
					permitir = false;

				if (!currentDate.before(_fechaCaduca))
					permitir = false;
			}

			if (!permitir)
				throw new TSException("Permiso denegado.", CodeEnum.UNAUTHORIZED);

		} catch (TSException e) {
			// TODO: handle exception
			throw new TSException(e.getMessage(), e.getCode());
		}
	}

}

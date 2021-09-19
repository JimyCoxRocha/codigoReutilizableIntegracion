package org.mdw.stc.repository.mongo;

import java.util.List;

import org.mdw.stc.entity.mongo.MIDServicioEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDServicioRepository extends MongoRepository<MIDServicioEntity, String> {

	MIDServicioEntity findByNombreMetodoAndEstado(String nombreMetodo, String estado);

	MIDServicioEntity findByMetodourlAndEstado(String nombreMetodo, String estado);

	MIDServicioEntity findByMetodourlAndEstadoAndServiciourl(String nombreMetodo, String estado, String proyecto_url);

	List<MIDServicioEntity> findByCodigo(String codigo);

}

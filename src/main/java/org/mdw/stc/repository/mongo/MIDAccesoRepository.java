package org.mdw.stc.repository.mongo;

import java.util.List;

import org.mdw.stc.entity.mongo.MIDAccesoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDAccesoRepository extends MongoRepository<MIDAccesoEntity, String> {

	List<MIDAccesoEntity> findByCodigoModuloAndCodigoServicio(String codigoModulo, String codigoServicio);

	List<MIDAccesoEntity> findByCodigoModulo(String codigoModulo);
}

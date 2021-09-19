package org.mdw.stc.repository.mongo;

import org.mdw.stc.entity.mongo.MIDModuloEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDModuloRepository extends MongoRepository<MIDModuloEntity, String> {

	MIDModuloEntity findByCodigo(String codigo);

}

package org.mdw.stc.repository.mongo;

import org.mdw.stc.entity.mongo.MIDServidorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDServidorRepository extends MongoRepository<MIDServidorEntity, String> {

	MIDServidorEntity findByCodigo(String codigo);
}

package org.mdw.stc.repository.mongo;

import org.mdw.stc.entity.mongo.MIDAuditoriaCentralFileV1;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDAuditoriaCentralFileV1Repository extends MongoRepository<MIDAuditoriaCentralFileV1, String> {

}

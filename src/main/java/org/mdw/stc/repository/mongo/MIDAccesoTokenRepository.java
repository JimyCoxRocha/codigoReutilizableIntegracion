package org.mdw.stc.repository.mongo;

import java.util.List;

import org.mdw.stc.entity.mongo.MIDAccesoTokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDAccesoTokenRepository extends MongoRepository<MIDAccesoTokenEntity, String> {

	@Query("{usuario: ?0, fechagenera: {$regex : ?1}}")
	List<MIDAccesoTokenEntity> findByUsuarioAndFechagenera(String usuario, String fechagenera);

	List<MIDAccesoTokenEntity> findByUsuarioAndToken(String usuario, String token);
}

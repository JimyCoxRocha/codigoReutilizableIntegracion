package org.mdw.stc.repository.mongo;

import java.util.Date;
import java.util.List;

import org.mdw.stc.entity.mongo.Dashboard2Entity;
import org.mdw.stc.entity.mongo.MIDLogErrorEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDLogErrorRepository extends MongoRepository<MIDLogErrorEntity, String> {

	public static final String match = "{ '$match' : { 'fecha': { '$gte': ?0, '$lte': ?1 }} }";
	public static final String group = "{ '$group' : { '_id' : '$metodo', 'count' : { $sum: 1 }} }";

	@Query(value = "{'data.Metodo':?0, 'usuario':?1, fecha: {$gte: ?2 , $lte: ?3}}")
	List<MIDLogErrorEntity> findByMetodoAndUsuarioAndFechaBetween(String metodo, String usuario, Date fecha_desde,
			Date fecha_hasta);

	@Query(value = "{'usuario':?0, fecha: {$gte: ?1 , $lte: ?2}}")
	List<MIDLogErrorEntity> findByUsuarioAndFechaBetween(String usuario, Date fecha_desde, Date fecha_hasta);

	@Query(value = "{'data.Metodo':?0, 'usuario':?1}")
	List<MIDLogErrorEntity> findByMetodoAndUsuario(String metodo, String usuario);

	@Query(value = "{'data.Metodo':?0}")
	List<MIDLogErrorEntity> findByMetodo(String metodo);

	@Query(value = "{fecha: {$gte: ?0 , $lte: ?1}}")
	List<MIDLogErrorEntity> findByFechaBetween(Date fecha_desde, Date fecha_hasta);

	@Aggregation(pipeline = { match, group })
	List<Dashboard2Entity> aggregateByMetodo(Date fecha_desde, Date fecha_hasta);

}

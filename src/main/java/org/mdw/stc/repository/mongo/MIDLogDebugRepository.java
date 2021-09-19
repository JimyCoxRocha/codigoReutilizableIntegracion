package org.mdw.stc.repository.mongo;

import java.util.Date;
import java.util.List;

import org.mdw.stc.entity.mongo.Dashboard2Entity;
import org.mdw.stc.entity.mongo.MIDLogDebugEntity;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MIDLogDebugRepository extends MongoRepository<MIDLogDebugEntity, String> {

	public static final String match = "{ '$match' : { 'fecha': { '$gte': ?0, '$lte': ?1 }} }";
	public static final String group = "{ '$group' : { '_id' : '$metodo', 'count' : { $sum: 1 }} }";

	@Query(value = "{'usuario':?0, fecha: {$gte: ?1 , $lte: ?2}}")
	List<MIDLogDebugEntity> findByUsuarioAndFechaBetween(String usuario, Date fecha_desde, Date fecha_hasta);

	@Query(value = "{'usuario':?0, 'metodo': ?1, fecha: {$gte: ?2 , $lte: ?3}}")
	List<MIDLogDebugEntity> findByUsuarioAndMetodoAndFechaBetween(String usuario, String metodo, Date fecha_desde,
			Date fecha_hasta);

	@Query(value = "{fecha: {$gte: ?0 , $lte: ?1}}")
	List<MIDLogDebugEntity> findByFechaBetween(Date fecha_desde, Date fecha_hasta);

	@Aggregation(pipeline = { match, group })
	List<Dashboard2Entity> aggregateByMetodo(Date fecha_desde, Date fecha_hasta);

}

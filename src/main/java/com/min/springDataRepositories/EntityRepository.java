package com.min.springDataRepositories;

import com.min.springDataModel.EntityNode;
import com.min.springDataModel.EventNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EntityRepository extends GraphRepository<EntityNode> {
    EntityNode findByEntityId();

    @Query("START entity=node:EntityNode(entityId={entityId}) MATCH entity-[:ENTITYRELATEDEVENT]->event RETURN event")
    List<EventNode> findAllRelatedEvents(@Param("entityId") String entityId);
}

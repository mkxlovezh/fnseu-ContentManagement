package com.min.springDataRepositories;

import com.min.springDataModel.EntityNode;
import com.min.springDataModel.EventNode;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends GraphRepository<EventNode> {
    EventNode findByEventId(String eventId);

    @Query("START event=node:EventNode(eventId={eventId}) MATCH event-[:EVENTRELATEDENTITY]->entity RETURN entity")
    List<EntityNode> findAllRelatedEntity(@Param("eventId") String eventId);
}

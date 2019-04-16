package com.min.springDataModel;

import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = RelationshipTypes.ENTITYRELATEDEVENT)
public class EntityRelatedEventRelationship {
    @GraphId
    private Long graphId;

    @StartNode
    private EntityNode entityStart;

    @EndNode
    private EventNode eventEnd;

    @Property(name = "relatedDegree")
    private float relatedDegree;


    public EntityRelatedEventRelationship() {/* NOOP */}

    public EntityRelatedEventRelationship(EntityNode entityStart, EventNode eventEnd)
    {
        this.entityStart = entityStart;
        this.eventEnd = eventEnd;
    }


    public EventNode getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(EventNode eventEnd) {
        this.eventEnd = eventEnd;
    }

    public EntityNode getEntityStart() {
        return entityStart;
    }

    public void setEntityStart(EntityNode entityStart) {
        this.entityStart = entityStart;
    }

    public float getRelatedDegree() {
        return relatedDegree;
    }

    public void setRelatedDegree(float relatedDegree) {
        this.relatedDegree = relatedDegree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntityRelatedEventRelationship that = (EntityRelatedEventRelationship) o;

        if (eventEnd != null ? !eventEnd.equals(that.eventEnd) : that.eventEnd != null) return false;
        if (entityStart != null ? !entityStart.equals(that.entityStart) : that.entityStart != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (entityStart != null ? entityStart.hashCode() : 0);
        result = 31 * result + (eventEnd != null ? eventEnd.hashCode() : 0);
        return result;
    }
}

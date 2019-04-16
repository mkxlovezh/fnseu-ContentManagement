package com.min.springDataModel;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = RelationshipTypes.EVENTRELATEDENTITY)
public class EventRelatedEntityRelationship {
    public EventRelatedEntityRelationship(int relatedDegree,EventNode eventNode,EntityNode entityNode){
        this.relatedDegree=relatedDegree;
        this.eventNode=eventNode;
        this.entityNode=entityNode;
    }
    @GraphId
    private Long graphId;

    @StartNode

    private EventNode eventNode;

    @EndNode

    private EntityNode entityNode;

    @Property
    private float relatedDegree;


    public EntityNode getEntityNode() {
        return entityNode;
    }

    public void setEntityNode(EntityNode entityNode) {
        this.entityNode = entityNode;
    }

    public EventNode getEventNode() {
        return eventNode;
    }

    public void setEventNode(EventNode eventNode) {
        this.eventNode = eventNode;
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

        EventRelatedEntityRelationship that = (EventRelatedEntityRelationship) o;

        if (entityNode != null ? !entityNode.equals(that.entityNode) : that.entityNode != null) return false;
        if (eventNode != null ? !eventNode.equals(that.eventNode) : that.eventNode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (eventNode != null ? eventNode.hashCode() : 0);
        result = 31 * result + (entityNode != null ? entityNode.hashCode() : 0);
        return result;
    }
}

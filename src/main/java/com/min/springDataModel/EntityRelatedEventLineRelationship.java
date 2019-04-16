package com.min.springDataModel;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = RelationshipTypes.ENTITYRELATEDEVENTLINE)
public class EntityRelatedEventLineRelationship {
    public EntityRelatedEventLineRelationship(int relatedDegree,EventLineNode eventLineNode,EntityNode entityNode){
        this.relatedDegree=relatedDegree;
        this.eventLineNode=eventLineNode;
        this.entityNode=entityNode;
    }
    @GraphId
    private Long graphId;

    @StartNode

    private EntityNode entityNode;

    @EndNode


    private EventLineNode eventLineNode;

    @Property
    private float relatedDegree;


    public EntityNode getEntityNode() {
        return entityNode;
    }

    public void setEntityNode(EntityNode entityNode) {
        this.entityNode = entityNode;
    }

    public EventLineNode getEventLineNode() {
        return eventLineNode;
    }

    public void setEventLineNode(EventLineNode eventLineNode) {
        this.eventLineNode = eventLineNode;
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

        EntityRelatedEventLineRelationship that = (EntityRelatedEventLineRelationship) o;

        if (entityNode != null ? !entityNode.equals(that.entityNode) : that.entityNode != null) return false;
        if (eventLineNode != null ? !eventLineNode.equals(that.eventLineNode) : that.eventLineNode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = 31 * result + (eventLineNode != null ? eventLineNode.hashCode() : 0);
        result = 31 * result + (entityNode != null ? entityNode.hashCode() : 0);
        return result;
    }

}

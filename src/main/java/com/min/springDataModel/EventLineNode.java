package com.min.springDataModel;


import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class EventLineNode extends IdentifiableEntity{

    @Property(name = "eventLineId")
    private String eventLineId;

    @Relationship(type = RelationshipTypes.EVENTLINERELATEDENTITY)
    private List<EventLineRelatedEntityRelationship> eventLineRelatedEntityRelationships;

    public EventLineNode(){}
    public EventLineNode(String eventLineId){
        this.eventLineId=eventLineId;
    }
    public String getEventId() {
        return eventLineId;
    }

    public void setEventId(String eventId) {
        this.eventLineId = eventId;
    }

    public List<EventLineRelatedEntityRelationship> getEventLineRelatedEntityRelationships() {
        return eventLineRelatedEntityRelationships;
    }

    public void setEventLineRelatedEntityRelationships(List<EventLineRelatedEntityRelationship> eventLineRelatedEntityRelationships) {
        this.eventLineRelatedEntityRelationships = eventLineRelatedEntityRelationships;
    }

    @Override
    public String toString() {
        return "EventLine{" +
                "graphId=" + this.getGraphId() +
                ", eventLineId=" + this.eventLineId +

                //", #clickedProductsRelationships=" + this.clickedProductsRelationships.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EventLineNode eventLine = (EventLineNode) o;

        if (eventLineId != null ? !eventLineId.equals(eventLine.eventLineId) : eventLine.eventLineId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (eventLineId != null ? eventLineId.hashCode() : 0);
        return result;
    }
}

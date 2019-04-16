package com.min.springDataModel;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;


@NodeEntity
public class EventNode extends IdentifiableEntity{
    @Property(name = "eventId")
    private String eventId;


    @Relationship(type = RelationshipTypes.EVENTRELATEDENTITY )
    private List<EventRelatedEntityRelationship> eventRelatedEntityRelationships;


    public EventNode(){}
    public EventNode(String eventId){
        super();
        this.eventId=eventId;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public List<EventRelatedEntityRelationship> getEventRelatedEntityRelationships() {
        return eventRelatedEntityRelationships;
    }

    public void setEventRelatedEntityRelationships(List<EventRelatedEntityRelationship> eventRelatedEntityRelationships) {
        this.eventRelatedEntityRelationships = eventRelatedEntityRelationships;
    }

    @Override
    public String toString() {
        return "Event{" +
                "graphId=" + this.getGraphId() +
                ", eventId=" + this.eventId +

                //", #clickedProductsRelationships=" + this.clickedProductsRelationships.size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EventNode event = (EventNode) o;

        if (eventId != null ? !eventId.equals(event.eventId) : event.eventId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        return result;
    }


}

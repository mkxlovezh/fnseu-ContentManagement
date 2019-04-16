package com.min.springDataModel;



import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import java.util.List;

@NodeEntity
public class EntityNode extends IdentifiableEntity {
    @Property(name = "entityId")
    private String entityId;

    @Property(name = "content")
    private String content;

    @Relationship(type = RelationshipTypes.ENTITYRELATEDEVENT)
    private List<EntityRelatedEventRelationship> entityRelatedEventRelationships;

    @Relationship(type = RelationshipTypes.ENTITYRELATEDEVENTLINE)
    private  List<EntityRelatedEventLineRelationship> entityRelatedEventLineRelationships;

    public EntityNode() {/* NOOP */}

    public EntityNode(String entityId, String content) {
        super();
        this.entityId = entityId;
        this.content = content;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<EntityRelatedEventRelationship> getEntityRelatedEventRelationships() {
        return entityRelatedEventRelationships;
    }

    public void setEntityRelatedEventRelationships(List<EntityRelatedEventRelationship> entityRelatedEventRelationships) {
        this.entityRelatedEventRelationships = entityRelatedEventRelationships;
    }

    public List<EntityRelatedEventLineRelationship> getEntityRelatedEventLineRelationships() {
        return entityRelatedEventLineRelationships;
    }

    public void setEntityRelatedEventLineRelationships(List<EntityRelatedEventLineRelationship> entityRelatedEventLineRelationships) {
        this.entityRelatedEventLineRelationships = entityRelatedEventLineRelationships;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "graphId=" + this.getGraphId() +
                ", entityId=" + entityId +
                ", content=" + content +
                //", #productsRecommendRelationships=" + productsRecommendRelationships.size() +
                //", #userClicked=" + usersClicked.size() +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        EntityNode entityNode = (EntityNode) o;

        if (entityId != null ? !entityId.equals(entityNode.entityId) : entityNode.entityId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (entityId != null ? entityId.hashCode() : 0);
        return result;
    }

}



package com.min.springDataModel;

import org.neo4j.ogm.annotation.GraphId;

public abstract class IdentifiableEntity {
    @GraphId
    private Long graphId;

    public Long getGraphId() {
        return graphId;
    }

    @Override
    public boolean equals(Object obj){
        return obj instanceof IdentifiableEntity && graphId.equals(((IdentifiableEntity) obj).getGraphId());
    }


    public int hasCode(){
        return 0;
    }
}

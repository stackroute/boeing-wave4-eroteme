package com.stackroute.graph.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class User {
    @Id
//    private int userId;
    private String userName;
    private int reputation;
}

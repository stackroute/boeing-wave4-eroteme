package com.stackroute.recommendationservice.model;

import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
public class User {
    @Id
    String username;
    int reputation;
}

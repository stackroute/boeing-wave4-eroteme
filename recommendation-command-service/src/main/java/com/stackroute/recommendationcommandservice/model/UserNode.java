package com.stackroute.recommendationcommandservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserNode {
    @Id
    private String email;
    private int reputation;
}

package com.stackroute.recommendationservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("userDTO")
public class UserRequested {
    @Id
    private String username;
    private String firstName;
    private String imageUrl;
}

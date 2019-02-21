package com.stackroute.recommendationservice.model;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class UserRequested {
    private String email;
    private String firstName;
    private  String imageUrl;
}

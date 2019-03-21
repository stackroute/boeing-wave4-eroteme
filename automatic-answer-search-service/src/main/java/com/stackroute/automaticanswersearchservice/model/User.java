package com.stackroute.automaticanswersearchservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User implements Serializable {

    //Variables
    private String email;
    private String firstName;
    private String imageUrl;
}

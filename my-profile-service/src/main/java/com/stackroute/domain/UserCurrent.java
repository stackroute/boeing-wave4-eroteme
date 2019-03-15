package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(value = "profile")
public class UserCurrent {

    @Id
    private String emailaddress;
    private String username;
    private int reputation;
    private List<String> interests;
    private int views;
    private List<Question> questions;
    private List<Question> answers;
    private String imageURL;

}

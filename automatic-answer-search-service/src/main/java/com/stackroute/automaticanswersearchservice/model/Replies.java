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
public class Replies implements Serializable {
    //private member variables for replies class
    User user;
    private String reply;
    private long likes;
    private long timestamp;
}

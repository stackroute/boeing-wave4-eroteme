package com.stackroute.searchservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comments {
    //private member variable declaration//
    private String comments;
    private long timestamp;
    private int likes;
    List<Replies> replies;
    User user;
}

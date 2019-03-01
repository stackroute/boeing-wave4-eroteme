package com.stackroute.recommendationcommandservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    com.stackroute.recommendationcommandservice.domain.User user;
    List<com.stackroute.recommendationcommandservice.domain.Replies> replies;
    private String comment;
    private long timestamp;
    private long likes;
}

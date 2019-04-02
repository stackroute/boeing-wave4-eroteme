package com.stackroute.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment{
    private String comment;
    private long timestamp;
    private long likes;
    User user;
    List<Replies> replies;
}

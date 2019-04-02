package com.stackroute.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Replies{
    private String reply;
    private long likes;
    private long timestamp;
    User user;
}

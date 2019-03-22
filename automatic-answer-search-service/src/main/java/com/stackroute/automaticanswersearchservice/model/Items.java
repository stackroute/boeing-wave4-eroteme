package com.stackroute.automaticanswersearchservice.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@RedisHash
public class Items implements Serializable {

    @JsonUnwrapped
    //private member variables for Items class
    private Owner owner;
    private int score;
    private String link;
    private boolean is_answered;
    private String title;
    private long question_id;
    private List<String> tags;

}


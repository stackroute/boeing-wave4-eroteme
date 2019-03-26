package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//Domain for list of notifications to be sent to a particular user
public class Notifications implements Serializable {
    private String email;
    private List<String> notification;
}

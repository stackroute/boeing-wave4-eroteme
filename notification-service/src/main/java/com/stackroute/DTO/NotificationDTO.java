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
//Notification DTO received from AES through rabbitmq to notify particular users that the question has been posted
public class NotificationDTO {
        List<String> emails;
        String question;
}

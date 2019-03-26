package com.stackroute.repository;

import com.stackroute.domain.Notifications;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
// Repository to store the notifications in redis database
public class NotificationRepository {

    private RedisTemplate<String,Notifications> redisTemplate;
    private HashOperations hashOperations;

    public NotificationRepository(RedisTemplate<String,Notifications> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

//method to return all the notifications for all the users
    public Map<String, Notifications> findAll() {

        return hashOperations.entries("NOTIFICATION");
    }

 //method to save notifications
    public void save(Notifications notifications) {
        hashOperations.put("NOTIFICATION",notifications.getEmail(), notifications);
    }

//method to find the list of notifications for a particular email
    public Notifications findById(String email) {

        return (Notifications) hashOperations.get("NOTIFICATION", email);
    }

//method to update the list of notifications
    public void update(Notifications notifications) {

        save(notifications);
    }

//method to delete all the notifications for a particular email
    public void delete(String email) {

        hashOperations.delete("NOTIFICATION", email);
    }
}

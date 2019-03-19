package com.stackroute.repository;

import com.stackroute.domain.Notifications;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class NotificationRepository {

    private RedisTemplate<String,Notifications> redisTemplate;
    private HashOperations hashOperations;

    public NotificationRepository(RedisTemplate<String,Notifications> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public Map<String, Notifications> findAll() {
        return hashOperations.entries("NOTIFICATION");
    }
    public void save(Notifications notifications) {
        hashOperations.put("NOTIFICATION",notifications.getEmail(), notifications);
    }

    public Notifications findById(String email) {
        return (Notifications) hashOperations.get("NOTIFICATION", email);
    }


    public void update(Notifications notifications) {
        save(notifications);
    }


    public void delete(String email) {
        hashOperations.delete("NOTIFICATION", email);
    }
}

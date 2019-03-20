package com.stackroute.automaticanswersearchservice.Repository;


import com.stackroute.automaticanswersearchservice.model.Items;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AASRepoImpl implements AASRepo {

    private RedisTemplate<List<Items>, Items> redisTemplate;

    private HashOperations hashOperations;

    public AASRepoImpl(RedisTemplate<List<Items>, Items> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public void save(List<com.stackroute.StackOverflowAdaptor.domain.Items> items) {

        hashOperations.put("ITEMS", items, items);


    }


    @Override
    public List<com.stackroute.StackOverflowAdaptor.domain.Items> findAll() {
        return hashOperations.values("ITEMS");
    }


}

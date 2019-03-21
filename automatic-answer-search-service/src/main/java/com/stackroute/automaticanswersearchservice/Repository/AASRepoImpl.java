package com.stackroute.automaticanswersearchservice.Repository;


import com.stackroute.automaticanswersearchservice.model.Items;
import com.stackroute.automaticanswersearchservice.model.Question;
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
    public void save(List<Question> question) {

        hashOperations.put("SALT", question, question);


    }


    @Override
    public List<Question> findAll() {
        return hashOperations.values("SALT");
    }


}

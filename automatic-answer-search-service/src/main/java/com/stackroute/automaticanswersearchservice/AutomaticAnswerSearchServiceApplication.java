package com.stackroute.automaticanswersearchservice;

import com.stackroute.automaticanswersearchservice.model.Items;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@EnableAutoConfiguration
//@ComponentScan scans the base package of StackOverflowAdapter and the current base package
@ComponentScan({"com.stackroute.automaticanswersearchservice", "com.stackroute.StackOverflowAdaptor"})
@Configuration
@EnableCaching
@SpringBootApplication
@EnableEurekaClient
public class AutomaticAnswerSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticAnswerSearchServiceApplication.class, args);
    }

    //Returns a Jedis instance to be used as a Redis connection.
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<List<Items>, Items> redisTemplate() {
        RedisTemplate<List<Items>, Items> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}

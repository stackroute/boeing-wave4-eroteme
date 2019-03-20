package com.stackroute.automaticanswersearchservice;

import com.stackroute.automaticanswersearchservice.model.Items;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@EnableAutoConfiguration
@ComponentScan({"com.stackroute.automaticanswersearchservice", "com.stackroute.StackOverflowAdaptor"})
@Configuration
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class AutomaticAnswerSearchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticAnswerSearchServiceApplication.class, args);
    }

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

package com.example.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

//@Configuration
//@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Serializable> redisCacheTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    /**
     * @MethodName: initRedis
     * @Description: 不带密码的redis初始化
     * @Param: [indexDb, host, port, redisTemplate]
     * @Return: org.springframework.data.redis.core.StringRedisTemplate
     * @Author: LiuZW
     * @Date: 2019/10/17/017 11:55
     **/

    public static StringRedisTemplate initRedis(Integer indexDb, String host, Integer port, StringRedisTemplate redisTemplate) {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(host);
        redisConnectionFactory.setPort(port);
        redisConnectionFactory.setDatabase(indexDb);
        redisConnectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    public static StringRedisTemplate initRedis(Integer indexDb, StringRedisTemplate redisTemplate) {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setDatabase(indexDb);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * @MethodName: initRedis
     * @Description: 带密码的redis初始化
     * @Param: [indexDb, host, port, password, redisTemplate]
     * @Return: org.springframework.data.redis.core.StringRedisTemplate
     * @Author: LiuZW
     * @Date: 2019/10/17/017 11:55
     **/

    public static StringRedisTemplate initRedis(Integer indexDb,String host,  Integer port,String password, StringRedisTemplate redisTemplate) {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(host);
        redisConnectionFactory.setPort(port);
        redisConnectionFactory.setDatabase(indexDb);
        redisConnectionFactory.setPassword(password);
        redisConnectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

}
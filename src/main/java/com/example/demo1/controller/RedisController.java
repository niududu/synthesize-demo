package com.example.demo1.controller;

import com.example.demo1.config.RedisConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: LiuZW
 * @CreateDate: 2019/10/17/017 10:11
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "redis")
@Log4j2
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //添加
    @GetMapping(value="/add")
    public void saveRedis(){
        stringRedisTemplate.opsForValue().set("a","test");
    }

    //获取
    @GetMapping(value="/get")
    public String getRedis(){
        return stringRedisTemplate.opsForValue().get("a");
    }



    /**
     * @MethodName: redisTest
     * @Description: 根据不同index重置redis对象
     * @Param: []
     * @Return: java.lang.String
     * @Author: LiuZW
     * @Date: 2019/10/17/017 13:53
     **/

    @RequestMapping(value = "test")
    public String redisTest(){

        StringRedisTemplate index0RedisTemplate = RedisConfig.initRedis(0,"139.199.77.147",6379,
                "EVS@2019djs", this.stringRedisTemplate);
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) index0RedisTemplate.getConnectionFactory();
        log.info(jedisConnectionFactory.getDatabase());
        index0RedisTemplate.opsForValue().set("b","test");
        log.info("index = 0 => b : {{}}",index0RedisTemplate.opsForValue().get("b"));
        StringRedisTemplate index1RedisTemplate = RedisConfig.initRedis(1,"127.0.0.1",6379, this.stringRedisTemplate);
        JedisConnectionFactory jedisConnectionFactory1 = (JedisConnectionFactory) index1RedisTemplate.getConnectionFactory();
        log.info(jedisConnectionFactory1.getDatabase());
        index1RedisTemplate.opsForValue().set("b","tests");
        log.info("index = 1 => b : {{}}",index1RedisTemplate.opsForValue().get("b"));
        StringRedisTemplate stringRedisTemplate = RedisConfig.initRedis(3, this.stringRedisTemplate);
        stringRedisTemplate.opsForValue().set("b","test3");
        log.info("index = 3 => b : {{}}",stringRedisTemplate.opsForValue().get("b"));
        return "success";

    }

    /*
    @RequestMapping(value = "test1")
    public String redisTestFail(){
        redisUtil.set("redisTemplate","这是一条测试数据", 10);
        String value = redisUtil.get("redisTemplate",10).toString();
        log.info("redisValue="+value);
        log.info("读取redis成功");
        return "success";
    }
    */















}

package com.example.demo1.config;

/**
 * @Description:
 * @Author:  LiuZW
 * @CreateDate: 2019/10/17/017 10:09
 * @Version: 1.0
 */
//@Component
public class RedisOperator  {

 /*
    public static StringRedisTemplate initRedis(Integer indexDb, String host, Integer port, StringRedisTemplate redisTemplate) {
        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
        redisConnectionFactory.setHostName(host);
        redisConnectionFactory.setPort(port);
        redisConnectionFactory.setDatabase(indexDb);
        redisConnectionFactory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }


    @Autowired
    @Qualifier("customStringTemplate")
    private StringRedisTemplate stingRedisTemplate;


    @Autowired
    @Qualifier("customRedisTemplate")
    private RedisTemplate redisTemplate;



    public  StringRedisTemplate getStringRedisTemplate(int index){
        return (StringRedisTemplate) getTemplate(stingRedisTemplate,index);
    }

    public  RedisTemplate getRedisTemplate(int index){
        return  getTemplate(redisTemplate,index);
    }

    public RedisTemplate getTemplate(RedisTemplate redisTemplate,int index) {
        JedisConnectionFactory jedisConnectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        jedisConnectionFactory.setDatabase(index);
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        return redisTemplate;
    }

    public  void set(int index,String key, String value) {
        getStringRedisTemplate(index).opsForValue().set(key, value);
    }

    public  void set(int index,String key, Object value) {
        getRedisTemplate(index).opsForValue().set(key,value);
    }

    public String get(int index,String key) {
        return (String)getStringRedisTemplate(index).opsForValue().get(key);
    }

    public Object getObject(int index,String key) {
        return getRedisTemplate(index).opsForValue().get(key);
    }
    */
}
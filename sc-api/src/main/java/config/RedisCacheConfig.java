package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置
 * @author Administrator
 *
 */
@Configuration
@EnableCaching   //开启缓存
public class RedisCacheConfig extends CachingConfigurerSupport{
	//设置过期时间，默认为3600
	@Value("${redis.cache.expiration:3600}")
    private Long expiration;
	
	/**
	 * 配置缓存管理
	 * @param redisTemplate
	 * @return
	 */
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
        rcm.setDefaultExpiration(expiration);
        return rcm;
    }
    
    /**
     * 注入RedisTemplate，使用连接工厂
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        //提简单来说调用incr后得到 值不会出错是没有经过redistemplate的deserialize, 而get必须经过 ，
        // 所以只要设置redistemplate的ValueSerializer即可
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        return template;
    }
}

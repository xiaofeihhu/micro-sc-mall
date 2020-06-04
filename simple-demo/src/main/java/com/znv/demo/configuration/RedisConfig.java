package com.znv.demo.configuration;//package com.znv.demo.configuration;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.core.RedisOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
//
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
//
//
//@Configuration
//@EnableRedisHttpSession
//public class RedisConfig {
//	 	@Bean
//	 	@ConditionalOnClass(RedisOperations.class)
//	    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//	        redisTemplate.setConnectionFactory(redisConnectionFactory);
//	        
//	        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
//	        // 全局开启AutoType，不建议使用
//	         ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//	        // 建议使用这种方式，小范围指定白名单
////	        ParserConfig.getGlobalInstance().addAccept("com.xiaolyuh.");
//	 
//	        // 设置值（value）的序列化采用FastJsonRedisSerializer。
//	        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
//	        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
//	        // 设置键（key）的序列化采用StringRedisSerializer。
//	        redisTemplate.setKeySerializer(new StringRedisSerializer());
//	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//	        redisTemplate.afterPropertiesSet();
//	        return redisTemplate;
//	    }
//	    @Bean
//	    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//	        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
//	        stringRedisTemplate.setConnectionFactory(factory);
//	        return stringRedisTemplate;
//	    }
//}

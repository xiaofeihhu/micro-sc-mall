package com.znv.demo.configuration;//package com.znv.demo.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;

import java.time.Duration;


@Configuration
public class RedisConfig {
	 	@Bean
	 	@ConditionalOnClass(RedisOperations.class)
	    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
	        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(redisConnectionFactory);

	        FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
	        // 全局开启AutoType，不建议使用
	         ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
	        // 建议使用这种方式，小范围指定白名单
//	        ParserConfig.getGlobalInstance().addAccept("com.xiaolyuh.");

	        // 设置值（value）的序列化采用FastJsonRedisSerializer。
	        redisTemplate.setValueSerializer(fastJsonRedisSerializer);
	        redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
	        // 设置键（key）的序列化采用StringRedisSerializer。
	        redisTemplate.setKeySerializer(new StringRedisSerializer());
	        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
	        redisTemplate.afterPropertiesSet();
	        return redisTemplate;
	    }
	    @Bean
	    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
	        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
	        stringRedisTemplate.setConnectionFactory(factory);
	        return stringRedisTemplate;
	    }

//	@Bean
//	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//		// 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
//		RedisSerializer<String> strSerializer = new StringRedisSerializer();
//		Jackson2JsonRedisSerializer jacksonSerial = new Jackson2JsonRedisSerializer(Object.class);
//		// 解决查询缓存转换异常问题
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		jacksonSerial.setObjectMapper(om);
//		// 定制缓存数据序列化方式及时效
//		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//				.entryTtl(Duration.ofDays(1))
//				.serializeKeysWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(strSerializer))
//				.serializeValuesWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(jacksonSerial))
//				.disableCachingNullValues();
//		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
//		return cacheManager;
//	}

	// 想要基于注解的Redis缓存实现也是用自定义序列化机制，就需要自定义RedisCacheManager
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
		// 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
		RedisSerializer<String> strSerializer = new StringRedisSerializer();
		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
		// 解决查询缓存转换异常问题
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		fastJsonRedisSerializer.setObjectMapper(om);
		// 定制缓存数据序列化方式及时效
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofDays(1))
				.serializeKeysWith(RedisSerializationContext.SerializationPair
						.fromSerializer(strSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair
						.fromSerializer(fastJsonRedisSerializer))
				.disableCachingNullValues();
		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
		return cacheManager;
	}
}

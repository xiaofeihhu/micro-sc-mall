package com.znv.demo.configuration;//package com.znv.demo.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;


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
//	@Bean
//	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//		// 解决查询缓存转换异常问题
////		ObjectMapper om = new ObjectMapper();
////		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
////		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
////		fastJsonRedisSerializer.setObjectMapper(om);
//
//		// 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
//		RedisSerializer<String> strSerializer = new StringRedisSerializer();
//		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
//		// 定制缓存数据序列化方式及时效
//		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//				.entryTtl(Duration.ofDays(1))
//				.serializeKeysWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(strSerializer))
//				.serializeValuesWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(fastJsonRedisSerializer))
//				.disableCachingNullValues();
//		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
//		return cacheManager;
//
//	}

	@Bean
	public RedisCacheManager cacheManager(RedisTemplate redisTemplate) {
		// 分别创建String和JSON格式序列化对象，对缓存数据key和value进行转换
//		RedisSerializer<String> strSerializer = new StringRedisSerializer();
//		FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);
		// 解决查询缓存转换异常问题
//		ObjectMapper om = new ObjectMapper();
//		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//		fastJsonRedisSerializer.setObjectMapper(om);
		// 定制缓存数据序列化方式及时效
//		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//				.entryTtl(Duration.ofDays(1))
//				.serializeKeysWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(strSerializer))
//				.serializeValuesWith(RedisSerializationContext.SerializationPair
//						.fromSerializer(fastJsonRedisSerializer))
//				.disableCachingNullValues();
//		RedisCacheManager cacheManager = RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(config).build();
//		return cacheManager;

		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisTemplate.getConnectionFactory());
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getValueSerializer()));
		//return new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
		return new CustomRedisCacheManager(redisCacheWriter, redisCacheConfiguration);
	}

	@Bean("myKeyGenerator1")
	public KeyGenerator keyGenerator1() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder key = new StringBuilder();
				key.append(target.getClass().getSimpleName()).append(":").append(method.getName()).append(":");
				if (params.length == 0) {
					return key.toString();
				}
				for (int i = 0; i < params.length; i++) {
					Object param = params[i];
					if (param == null) {
						del(key);
					} else if (ClassUtils.isPrimitiveArray(param.getClass())) {
						int length = Array.getLength(param);
						for (int j = 0; j < length; j++) {
							key.append(Array.get(param, j));
							key.append(',');
						}
					} else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
						key.append(param);
					} else {
						key.append(param.toString());
					}
					key.append('-');
				}
				del(key);
				return key.toString();
			}
			private StringBuilder del(StringBuilder stringBuilder) {
				if (stringBuilder.toString().endsWith("-")) {
					stringBuilder.deleteCharAt(stringBuilder.length() - 1);
				}
				return stringBuilder;
			}
		};
	}

	@Bean("myKeyGenerator2")
	public KeyGenerator keyGenerator2() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder key = new StringBuilder();
				key.append(target.getClass().getSimpleName()).append(":").append(method.getName()).append(":");
				key.append(Arrays.asList(params));
				return key.toString();
			}
		};
	}

	@Bean("myKeyGenerator3")
	public KeyGenerator keyGenerator3() {
		return new KeyGenerator() {
			@Override
			public Object generate(Object target, Method method, Object... params) {
				StringBuilder key = new StringBuilder();
				key.append(target.getClass().getSimpleName()).append(":").append(method.getName()).append(":");
				key.append(StringUtils.arrayToCommaDelimitedString(params));
				return key.toString();
			}
		};
	}


	/**
	 * 对hash类型的数据操作
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
	}

	/**
	 * 对redis字符串类型数据操作
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
	}

	/**
	 * 对链表类型的数据操作
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
	}

	/**
	 * 对无序集合类型的数据操作
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
	}

	/**
	 * 对有序集合类型的数据操作
	 *
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
	}
}

package com.znv.mall.servicecommon.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @Auther: yf
 * @Date: 2019-6-27
 * @Description:
 */
@Slf4j
public class UniqueIdGeneratorUtil {
    /**
     * 通过uuid生成
     * @return
     */
    public static String createUniqueIdIdByUUid() {
        return UUID.randomUUID().toString();
    }

    /**
     * 通过mysql自增主键生成
     * @return
     */
    public static String createUniqueIdIdByMysqlInc() {
        // TODO 集成mybatis查询数据库自增主键
        return "";
    }

    private static long serverId;

    @Value("${server.id}")
    public void setServerId(long serverId){
        this.serverId = serverId;
    }

    /**
     * 通过twiter的snowflake雪花算法生成id
     * @return
     */
    public static long createUniqueIdIdBySnowflake() {
        return generatorId(serverId);
    }

    private static long lastGeneratorId = 0;

    /** 开始时间截 (2015-01-01) */
    private static final long twepoch = 1420041600000L;
    private static long sequence = 0L;

    /* 41为时间戳，10位serverid，12位seq*/
    private static synchronized long generatorId(long serverId){
        long timestamp = System.currentTimeMillis();
        lastGeneratorId = ((timestamp - twepoch) << (10 + 12))
                | (serverId << 12)
                | (sequence++);
        log.info("createUniqueIdIdBySnowflake:generatorId:{}",lastGeneratorId);
        return lastGeneratorId;
    }

    /**
     * 通过redis的自增key生成id
     * @return
     */
    public static String createUniqueIdIdByRedisInc() {
        String thisDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String store_key = String.join(UniqueIdGeneratorUtil.class.getName(), "orderId", thisDate);
        long orderId = RedisUtil.incr(store_key, 1);
        if (orderId==1) {
            RedisUtil.expire(store_key,60*60*24);
        }
        String uniqueId= getSequence(orderId);
        log.info("createUniqueIdIdBySnowflake:generatorId:{}",uniqueId);
        return uniqueId;
    }

    private static final int DEFAULT_LENGTH = 12;

    public static String getSequence(long seq) {
        String str = String.valueOf(seq);
        int len = str.length();
        if (len >= DEFAULT_LENGTH) {
            return str;
        }
        int rest = DEFAULT_LENGTH - len;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rest; i++) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * //TODO
     * 通过zk的znode生成id
     * @return
     */
    public static String createUniqueIdIdByzk() {
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getSequence(1));
    }
}

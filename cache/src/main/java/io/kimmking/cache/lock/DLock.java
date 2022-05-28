package io.kimmking.cache.lock;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class DLock {

    @Autowired
    private RedisTemplate redisTemplate;

    private ThreadLocal<String> lockFlag = new ThreadLocal<>();

    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

    public boolean tryLock(String key, long expire){
        Boolean result = (Boolean) redisTemplate.execute((RedisCallback) redisConnection -> {
            String lockValue = UUID.randomUUID().toString();
            lockFlag.set(lockValue);
            return redisConnection.set(key.getBytes(StandardCharsets.UTF_8), lockValue.getBytes(StandardCharsets.UTF_8), Expiration.milliseconds(expire), RedisStringCommands.SetOption.SET_IF_ABSENT);
        });
        return result;
    }


    public boolean releaseLock(String key){
        try {
            Boolean result = (Boolean) redisTemplate.execute((RedisCallback) redisConnection -> {
                String lockValue = lockFlag.get();
                return redisConnection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, key.getBytes(StandardCharsets.UTF_8), lockValue.getBytes(StandardCharsets.UTF_8));
            });
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lockFlag.remove();
        }
        return false;
    }
}

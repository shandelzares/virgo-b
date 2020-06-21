package com.virgo.member.service;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CloudStoreService {
    final Auth auth = Auth.create("1flxOUsHBxCRFN2MEc7cVuJNyQSxnxYp5E0Vf37v", "THRS3HfYf7SpiNI5BhlJ6ek4XaUWD33az3gW7z2r");


    private static final String prefix = "qiniu:";
    private static final String bucket = "virgo-exam";
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public String getToken(String key) {
        if (StringUtils.isEmpty(key)) {//返回这个，这个时候是不需要key的
            return this.getToken();
        }
        String rKey = prefix + key;

        String token = redisTemplate.opsForValue().get(rKey);
        if (StringUtils.isEmpty(token)) {
            log.info("七牛token not found bucket is {}", "stmt-com");
            token = auth.uploadToken(bucket, key);
            redisTemplate.opsForValue().set(rKey, token, 30, TimeUnit.MINUTES);
        }
        return token;
    }

    public String getToken() {

        String key = prefix;
        String token = redisTemplate.opsForValue().get(key);
        if (StringUtils.isEmpty(token)) {
            token = auth.uploadToken(bucket);
            redisTemplate.opsForValue().set(key, token, 30, TimeUnit.MINUTES);
        }
        return token;
    }
}

package com.elearningplatform.util;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;

@Service
@AllArgsConstructor
public class RateLimiter {

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String key, String role) {
        String bucketKey = role + "_" + key;
        return buckets.computeIfAbsent(key, this::newBucket);
    }
    private Bucket newBucket(String role){
        Bandwidth limit;

        switch (role.toUpperCase()){
            case "SUPER_ADMIN":
                limit = Bandwidth.classic(100, Refill.greedy(100, Duration.ofMinutes(1)));
                break;
            case "ADMIN":
                limit = Bandwidth.classic(50, Refill.greedy(50, Duration.ofMinutes(1)));
                break;
            case "CLIENT":
                limit = Bandwidth.classic(30, Refill.greedy(30, Duration.ofMinutes(1)));
                break;
            default:
                limit = Bandwidth.classic(30, Refill.greedy(30, Duration.ofMinutes(1)));
        }
        return Bucket.builder().addLimit(limit).build();
    }


}

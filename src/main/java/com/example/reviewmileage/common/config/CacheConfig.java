package com.example.reviewmileage.common.config;


import com.example.reviewmileage.domain.review.Review;
import com.example.reviewmileage.domain.review.ReviewInfo;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager ehcacheManager() {
        CachingProvider provider = Caching.getCachingProvider();
        CacheManager cacheManager = provider.getCacheManager();
        MutableConfiguration<String, Review> configuration =
                new MutableConfiguration<String, Review>()
                        .setTypes(String.class, Review.class)
                        .setStoreByValue(false)
                        .setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));

        Cache<String, Review> cache = cacheManager.createCache("reviewCache", configuration);
        return cacheManager;
    }
}

package io.devpers.asynchronous.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {
    @Value("${async.general.corePoolSize}")
    private Integer generalCorePoolSize;
    @Value("${async.general.maxPoolSize}")
    private Integer generalMaxPoolSize;
    @Value("${async.general.queueCapacity}")
    private Integer generalQueueCapacity;
    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(generalCorePoolSize);
        executor.setMaxPoolSize(generalMaxPoolSize);
        executor.setQueueCapacity(generalQueueCapacity);
        executor.setThreadNamePrefix("io-devpers-");
        executor.initialize();
        return executor;
    }
}

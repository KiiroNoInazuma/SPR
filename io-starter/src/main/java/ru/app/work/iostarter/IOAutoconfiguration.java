package ru.app.work.iostarter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.app.work.iostarter.service.IOService;
import ru.app.work.iostarter.service.StreamsIOService;

@ConditionalOnProperty(value = "ru.app.work.io-enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class IOAutoconfiguration {

    @ConditionalOnMissingBean(IOService.class)
    @Bean
    public IOService ioService() {
        return new StreamsIOService(System.out, System.in);
    }
}

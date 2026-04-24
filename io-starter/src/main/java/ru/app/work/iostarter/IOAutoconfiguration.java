package ru.app.work.iostarter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.app.work.iostarter.config.DefaultLocaleProvider;
import ru.app.work.iostarter.config.LocaleProvider;
import ru.app.work.iostarter.service.IOService;
import ru.app.work.iostarter.service.LocalizationService;
import ru.app.work.iostarter.service.LocalizationServiceImpl;
import ru.app.work.iostarter.service.StreamsIOService;

@ConditionalOnProperty(value = "ru.app.work.io-enabled", havingValue = "true", matchIfMissing = true)
@Configuration
public class IOAutoconfiguration {

    @ConditionalOnMissingBean(IOService.class)
    @Bean
    public IOService ioService() {
        return new StreamsIOService(System.out, System.in);
    }

    @ConditionalOnMissingBean(LocaleProvider.class)
    @Bean
    public LocaleProvider localeProvider(@Value("${ru.app.work.locale:ru-RU}") String localeTag) {
        return new DefaultLocaleProvider(localeTag);

    }

    @ConditionalOnMissingBean(LocalizationService.class)
    @ConditionalOnBean(MessageSource.class)
    @Bean
    public LocalizationService localizationService(LocaleProvider localeProvider,
                                                   MessageSource messageSource) {
        return new LocalizationServiceImpl(localeProvider, messageSource);
    }
}

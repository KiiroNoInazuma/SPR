package ru.app.work.greetingapp.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ToString
@ConfigurationProperties(prefix = "ru.app.work")
public class OtusIOConfig {

    @Getter
    private final boolean ioEnabled;

    @ConstructorBinding
    public OtusIOConfig(boolean ioEnabled) {
        this.ioEnabled = ioEnabled;
    }
}

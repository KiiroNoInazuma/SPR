package ru.app.work.greetingapp.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.app.work.greetingapp.config.OtusIOConfig;
import ru.app.work.iostarter.service.IOService;
import ru.app.work.iostarter.service.LocalizationService;

import static java.util.Objects.isNull;

@Component
public class AppRunner implements ApplicationRunner {

    private final OtusIOConfig ioConfig;

    private final IOService ioService;

    private final LocalizationService localizationService;

    public AppRunner(@Autowired(required = false) OtusIOConfig ioConfig,
                     @Autowired(required = false) IOService ioService,
                     @Autowired(required = false) LocalizationService localizationService) {
        this.ioService = ioService;
        this.ioConfig = ioConfig;
        this.localizationService = localizationService;
    }


    @Override
    public void run(ApplicationArguments args) {
        if (isNull(ioService)) {
            System.err.println("Отсутствует сервис ввода-вывода. Включите настройку \"otus.custom.io.enabled\"");
            return;
        }
        ioService.outputString("---------------------------------------------");

        ioService.outputAsString(ioConfig);

        ioService.outputString("---------------------------------------------");

        var greetingTarget = localizationService.getMessage("greeting.target");
        var greeting = localizationService.getMessage("greeting", greetingTarget);
        ioService.outputString(greeting);

        ioService.outputString("---------------------------------------------");
    }
}
package ru.app.work.greetingapp.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import ru.app.work.iostarter.service.IOService;

import static java.util.Objects.isNull;

@Component
public class AppRunner implements ApplicationRunner {

    private final IOService ioService;


    public AppRunner(@Autowired(required = false) IOService ioService) {
        this.ioService = ioService;

    }


    @Override
    public void run(ApplicationArguments args) {
        if (isNull(ioService)) {
            System.err.println("Отсутствует сервис ввода-вывода. Включите настройку \"otus.custom.io.enabled\"");
            return;
        }
        ioService.outputString("---------------------------------------------");

        ioService.outputAsString(null);

        ioService.outputString("---------------------------------------------");

        Object greetingTarget = null;
        Object greeting = null;
        ioService.outputString(null);

        ioService.outputString("---------------------------------------------");
    }
}
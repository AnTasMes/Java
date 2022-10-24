package antasmes.tech.demo;

import java.io.IOException;

import antasmes.tech.demo.events.StageSwitchEvent;
import antasmes.tech.demo.events.StageReadyEvent;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class FXApp extends Application {

    private static ConfigurableApplicationContext applicationContext;

    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        applicationContext = new SpringApplicationBuilder()
                .sources(DemoApplication.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        applicationContext.publishEvent(new StageReadyEvent(primaryStage));
    }

    public static void switchTo(Class<?> controller) {
        System.out.println("Switch to -> " + controller);
        applicationContext.publishEvent(new StageSwitchEvent(controller));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    public static void main(String[] args) {
        launch();
    }

}

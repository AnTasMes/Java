package antasmes.tech.demo.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.config.AppConfig;
import antasmes.tech.demo.controllers.javaFx.DefaultPageController;
import antasmes.tech.demo.events.StageReadyEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
class PrimaryStageInitializer implements ApplicationListener<StageReadyEvent> {

    private final FxWeaver fxWeaver;
    private final AppConfig app;
    private final Navigator navigator;

    @Autowired
    public PrimaryStageInitializer(FxWeaver fxWeaver, AppConfig app, Navigator navigator) {
        this.fxWeaver = fxWeaver;
        this.app = app;
        this.navigator = navigator;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        Scene scene = new Scene(fxWeaver.loadView(DefaultPageController.class));
        stage.setScene(scene);
        stage.setTitle(app.getTitle());
        stage.setWidth(app.getWidth());
        stage.setHeight(app.getHeight());
        navigator.setStage(stage);
        navigator.setScene(scene);

        navigator.switchTo(DefaultPageController.class);
    }
}

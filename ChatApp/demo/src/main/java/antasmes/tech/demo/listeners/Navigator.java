package antasmes.tech.demo.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.FXApp;
import antasmes.tech.demo.events.StageSwitchEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

@Component
public class Navigator implements ApplicationListener<StageSwitchEvent> {

    private Scene scene;

    private Stage stage;

    private FxWeaver fxWeaver;

    @Autowired
    public Navigator(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageSwitchEvent event) {
        Class<?> controller = event.getController();
        scene.setRoot(loadParent(controller));
        stage.show();
    }

    public void switchTo(Class<?> controller) {
        FXApp.switchTo(controller);
    }

    private Parent loadParent(Class<?> controller) {
        return fxWeaver.loadView(controller);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}

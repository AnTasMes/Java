package antasmes.tech.demo.events;

import org.springframework.context.ApplicationEvent;
import javafx.stage.Stage;

public class StageReadyEvent extends ApplicationEvent {

    private final Stage stage;

    public StageReadyEvent(Stage stage) {
        super(stage);
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
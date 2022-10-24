package antasmes.tech.demo.events;

import org.springframework.context.ApplicationEvent;

public class StageSwitchEvent extends ApplicationEvent {

    private final Class<?> controller;

    public StageSwitchEvent(Class<?> controller) {
        super(controller);
        this.controller = controller;
    }

    public Class<?> getController() {
        return controller;
    }

}

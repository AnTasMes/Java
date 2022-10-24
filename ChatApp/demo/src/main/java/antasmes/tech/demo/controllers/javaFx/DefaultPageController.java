package antasmes.tech.demo.controllers.javaFx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.listeners.Navigator;
import antasmes.tech.demo.services.UserService;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/defaultPage.fxml")
public class DefaultPageController {

    private final UserService userService;
    private final Navigator navigator;

    @Autowired
    public DefaultPageController(UserService userService, Navigator navigator) {
        this.userService = userService;
        this.navigator = navigator;
    }

    public void switchToSignUp() {
        navigator.switchTo(SignUpPageController.class);
    }

    public void login() {
        userService.testService();
    }
}

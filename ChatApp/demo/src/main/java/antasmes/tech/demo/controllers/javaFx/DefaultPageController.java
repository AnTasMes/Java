package antasmes.tech.demo.controllers.javaFx;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.listeners.Navigator;
import antasmes.tech.demo.models.CurrentUser;
import antasmes.tech.demo.services.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/defaultPage.fxml")
public class DefaultPageController implements Initializable {

    private final UserService userService;
    private final Navigator navigator;

    @FXML
    TextField usernameInput;
    @FXML
    TextField passwordInput;

    @Autowired
    public DefaultPageController(UserService userService, Navigator navigator) {
        this.userService = userService;
        this.navigator = navigator;
    }

    public void switchToSignUp() {
        navigator.switchTo(SignUpPageController.class);
    }

    /**
     * This method is called when the user clicks the login button
     *
     */
    public void login() {
        if (fieldsSet()) {
            String username = usernameInput.getText();
            String password = passwordInput.getText();

            CurrentUser.setUser(userService.authorize(username, password));

            if (CurrentUser.getUser() == null) {
                setWarnBorder(passwordInput);
                setWarnBorder(usernameInput);
            } else {
                removeBorder(passwordInput);
                removeBorder(usernameInput);

                System.out.println("Login success");

                navigator.switchTo(MainPageController.class);
            }
        }
    }

    public void loginAuto() {
        CurrentUser.setUser(userService.authorize("AnTasMes", "1234"));
        navigator.switchTo(MainPageController.class);
    }

    private Boolean fieldsSet() {
        if (usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty())
            return false;
        return true;
    }

    private void setWarnBorder(TextField field) {
        field.setBorder(new Border(new BorderStroke(
                Color.valueOf("#F42B00"),
                BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY,
                new BorderWidths(1))));
    }

    private void removeBorder(TextField field) {
        field.setBorder(null);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        System.out.println("Init DefaultPage");
    }
}

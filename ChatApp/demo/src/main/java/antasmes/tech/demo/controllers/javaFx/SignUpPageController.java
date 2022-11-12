package antasmes.tech.demo.controllers.javaFx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.listeners.Navigator;
import antasmes.tech.demo.models.User;
import antasmes.tech.demo.services.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/signUpPage.fxml")
public class SignUpPageController {

    private final UserService userService;
    private final Navigator navigator;

    private Boolean isReady = false;

    @FXML
    TextField usernameInput;
    @FXML
    TextField passwordInput;
    @FXML
    TextField passwordConfirmInput;
    @FXML
    TextField emailInput;

    @Autowired
    public SignUpPageController(UserService userService, Navigator navigator) {
        this.userService = userService;
        this.navigator = navigator;
    }

    public void switchToLogin() {
        navigator.switchTo(DefaultPageController.class);
    }

    public void create() {
        System.out.println("creating before");
        if (usernameInput.getText().isEmpty() || emailInput.getText().isEmpty() || !this.isReady)
            return;
        System.out.println("creating");
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        String email = emailInput.getText();

        if (userService.usernameExists(username)) {
            setWarnBorder(usernameInput);
        } else {
            removeBorder(usernameInput);
            createUser(new User(username, password, email));
        }
    }

    public void confirmPassword() {
        String password = passwordInput.getText();
        String cPwd = passwordConfirmInput.getText();

        if (!password.equals(cPwd)) {
            setWarnBorder(passwordConfirmInput);

            this.isReady = false;
        } else {
            removeBorder(passwordConfirmInput);

            this.isReady = true;
        }
    }

    private void clearFields() {
        usernameInput.clear();
        passwordInput.clear();
        passwordConfirmInput.clear();
        emailInput.clear();
    }

    private void createUser(User user) {
        try {
            userService.createUser(user);
            clearFields();
            System.out.println("User created");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}

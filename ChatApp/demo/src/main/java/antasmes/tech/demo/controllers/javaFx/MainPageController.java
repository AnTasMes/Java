package antasmes.tech.demo.controllers.javaFx;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antasmes.tech.demo.models.CurrentUser;
import antasmes.tech.demo.models.Message;
import antasmes.tech.demo.models.User;
import antasmes.tech.demo.services.MessageService;
import antasmes.tech.demo.services.UserService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/mainPage.fxml")
public class MainPageController implements Initializable {

    UserService userService;
    MessageService messageService;

    @FXML
    private ListView<User> friendsList;

    @FXML
    private ListView<Message> messagesList;

    @FXML
    private Button btnAddFriend;

    @FXML
    private Button btnSettings;

    @FXML
    private TextField messageInput;

    private User selectedFriend;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        setupFriendsList();
        setupMessageList();
        fillFriendsList();
    }

    @Autowired
    public MainPageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    public void openSettings() {
        // System.out.println("settings");
        // System.out.println(CurrentUser.getUser().getObjectId());
        // User user = userService.getUserByUID(CurrentUser.getUser().getObjectId());
        // System.out.println(user);

    }

    public void sendMessage() {
        String messageContent = messageInput.getText();
        Message message = new Message(
                messageContent,
                CurrentUser.getUser().getObjectId(),
                selectedFriend.getObjectId());

        messageService.insert(message);

        fillMessageList(selectedFriend);
    }

    public void addFriend() {
        System.out.println("add friend");
    }

    // Should fix this method
    private void fillMessageList(User friend) {
        messagesList.getItems().clear();
        messagesList.getItems()
                .addAll(messageService.getBySenderID(CurrentUser.getUser().getObjectId(), friend.getObjectId()));
    }

    private void fillFriendsList() {
        List<User> friends = userService.getFriends(CurrentUser.getUser()); // Loads friends from database
        friendsList.getItems().addAll(friends); // Adds friends to the list
    }

    private void setupMessageList() {
        messagesList.setCellFactory(param -> new ListCell<Message>() {
            @Override
            protected void updateItem(Message item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getText_value() == null) {
                    setText(null);
                } else {
                    // align messages to the right if they are from the current user
                    if (item.getfromUserId().equals(CurrentUser.getUser().getObjectId())) {
                        setStyle("-fx-alignment: center-right;");
                    } else {
                        setStyle("-fx-alignment: center-left;");
                    }
                    // Display time of the message below the message
                    setText(item.getText_value() + "\n" + item.getTimestamp());
                }
            }
        });

        messagesList.scrollTo(messagesList.getItems().size() - 1);
    }

    private void setupFriendsList() {

        // Enable selection of single items
        friendsList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {

            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                System.out.println("Selected item: " + newValue);
                selectedFriend = newValue;

                fillMessageList(newValue);
            }
        });

        // Show user name in list
        friendsList.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getUsername() == null) {
                    setText(null);
                } else {
                    setText(item.getUsername());
                }
            }
        });
    }

}

package antas.tech.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String username;
    private String nickname;
    private String discriminator;

    public User() {
    }

    public User(String username, String nickname, String discriminator) {
        this.username = username;
        this.nickname = nickname;
        this.discriminator = discriminator;
    }

    public User(String username, String discriminator) {
        this.username = username;
        this.discriminator = discriminator;
    }

    @Override
    public String toString() {
        return String.format("User: %s#%s", this.username, this.discriminator);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(String discriminator) {
        this.discriminator = discriminator;
    }
}

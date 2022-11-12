package antasmes.tech.demo.models;

public class CurrentUser {
    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }

    public static void clear() {
        user = null;
    }
}

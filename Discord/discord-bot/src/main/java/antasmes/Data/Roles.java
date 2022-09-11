package antasmes.Data;

public class Roles {
    // moram da sredim ovo i da automatski dodam role na serveru
    public enum RoleEnum {
        ADMINISTRATOR("Administrator", "Owner of the server", "561316118027960351", 0),
        MODERATOR("Moderator", "Moderator", "545274121462874123", 1),
        MEMBER("Member", "Every member on the server", "806672346508820541", 2);

        private String name;
        private String description;
        private String id;
        private int index;

        RoleEnum(String name, String description, String id, int index) {
            this.name = name;
            this.description = description;
            this.id = id;
            this.index = index;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public String getId() {
            return id;
        }

        public int getIndex() {
            return index;
        }
    }
}

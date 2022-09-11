package antasmes.MongoDB;

public class LogManager {
    private String databaseName;
    private final String host = "localhost";

    public LogManager(String databaseName) {
        this.databaseName = databaseName;
    }

    public void log(Insertable insertable) {
        Database database = new Database(host, databaseName, insertable.getCollectionName());
        database.insert(insertable.toDocument());
    }
    // Srediti da moze da se uradi pretraga loga po vremenu i po tipu
}

package antasmes.tech;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;

import java.util.List;
import java.util.Set;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            MongoClient mongoClient = new MongoClient("localhost");

            DB db = mongoClient.getDB("Fakultet");

            DBCollection dbCollection = db.getCollection("Student");

            // insertUser(dbCollection);

            Cursor cursor = dbCollection.find();

            printCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertUser(DBCollection dbCollection) {
        BasicDBObject dbObject = new BasicDBObject("First_name", "Jovan")
                .append("Last_name", "Markovic")
                .append("id", "333/20");

        dbCollection.insert(dbObject);
    }

    public static void printCursor(Cursor cursor) {
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
    }
}

package antasmes.MongoDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Database {
    private MongoClient _mongoClient = null;
    private MongoDatabase _mongoDatabase = null;
    private MongoCollection<Document> _mongoCollection = null;

    /**
     * Database class that connects to MongoDB database and provides methods for
     * CRUD
     * 
     * @param host   - host of the database
     * @param dbName - name of the database
     */
    public Database(String host, String dbName) {
        try {
            _mongoClient = MongoClients.create();
            _mongoDatabase = _mongoClient.getDatabase(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Database class that connects to MongoDB database and provides methods for
     * CRUD
     * 
     * @param host     - host of the database
     * @param dbName   - name of the database
     * @param collName - name of the specific collection
     */
    public Database(String host, String dbName, String collName) {
        try {
            _mongoClient = MongoClients.create();
            _mongoDatabase = _mongoClient.getDatabase(dbName);
            _mongoCollection = _mongoDatabase.getCollection(collName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDatabase(String dbName) {
        _mongoDatabase = _mongoClient.getDatabase(dbName);
    }

    public void setCollection(String collName) {
        _mongoCollection = _mongoDatabase.getCollection(collName);
    }

    /**
     * Insert a document into the collection
     * 
     * @param doc Document to insert
     */
    public void insert(Document doc) {
        _insert(doc);
    }

    /**
     * Inserts a map based document into the collection
     * 
     * @param map map to be translated into a document
     */
    public void insert(Map<String, String> map) {
        Document doc = new Document(map);
        _insert(doc);
    }

    /**
     * Inserts a list of documents that previously don't exist into the collection
     * The method checks the existance of each document before inserting
     * 
     * @param mapList List of maps for each document
     */
    public void insert(List<Map<String, String>> mapList) {
        List<Document> docList = _convertMapListToDocList(mapList);
        _insertMany(docList);
    }

    /**
     * Inserts a list of documents that previously don't exist into the collection
     * The method checks the existance of each document before inserting
     * 
     * Existance check done by comparing parital values of the document
     * with {@code key}
     * 
     * 
     * @param mapList List of maps for each document
     * @param key     Key for database search
     */
    public void insert(List<Map<String, String>> mapList, String key) {
        mapList.forEach(map -> {
            Document doc = new Document(map);

            Map<String, String> partialFind = new HashMap<String, String>();
            partialFind.put(key, map.get(key));

            if (!exists(partialFind)) {
                _insert(doc);
            }
        });

    }

    /**
     * Checks for existance of a Map
     * 
     * @param map
     * @return True if the document exists, and false if it doesn't
     */
    public Boolean exists(Map<String, String> map) {

        // Pogledati da napravim da se proverava po odredjenom keyword-u
        // Ovako ako nije potpuno isti dokument, mogu se ubaciti dva skoro ista
        // dokumenta
        Document doc = new Document(map);

        if (_mongoCollection.find(doc).first() == null) {
            return false;
        }
        return true;
    }

    /**
     * Checks for existance of a Document
     * 
     * @param map
     * @return True if the document exists, and false if it doesn't
     */
    public Boolean exists(Document doc) {
        if (_mongoCollection.find(doc).first() == null) {
            return false;
        }
        return true;
    }

    public Document get(Map<String, String> map) {
        Document doc = new Document(map);
        return _mongoCollection.find(doc).first();
    }

    /*
     * ***********************************************************
     * ***********************************************************
     * ******************** PRIVATE FUNCTIONS ********************
     * ***********************************************************
     * ***********************************************************
     */

    /**
     * Does the final step of insertion into the collection
     * 
     * @param doc document for insertion
     * @return true if inserted, and false otherwise
     */
    private Boolean _insert(Document doc) {
        if (exists(doc))
            return false;

        try {
            _mongoCollection.insertOne(doc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }

    /**
     * Validates and inserts all documents in the list into the collection
     * 
     * @param docList lsit of documents
     * @return true if all documents are inserted, and false otherwise
     */
    private Boolean _insertMany(List<Document> docList) {
        if (docList.isEmpty()) {
            return false;
        }

        try {
            _mongoCollection.insertMany(docList);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Converts a list of maps into a list of documents
     * 
     * @param mapList list of maps to be converted
     * @return list of documents
     */
    private List<Document> _convertMapListToDocList(List<Map<String, String>> mapList) {
        List<Document> docList = new ArrayList<Document>();

        if (mapList.isEmpty()) {
            return docList;
        }

        mapList.forEach(map -> {
            Document doc = new Document(map);
            docList.add(doc);
        });

        return docList;
    }
}

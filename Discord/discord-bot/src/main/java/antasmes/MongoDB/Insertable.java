package antasmes.MongoDB;

import java.util.HashMap;
import java.util.Map;

import org.bson.Document;

import antasmes.MongoDB.Types.DBCollections;
import antasmes.MongoDB.Types.LogKeys;

public abstract class Insertable {
    protected Map<LogKeys, Object> map;
    protected DBCollections collection;

    public Insertable() {
        this.map = new HashMap<LogKeys, Object>();
    }

    public Insertable(Map<LogKeys, Object> map) {
        this.map = map;
    }

    public Document toDocument() {
        Document document = new Document();
        for (Map.Entry<LogKeys, Object> entry : map.entrySet()) {
            document.append(entry.getKey().getKey(), entry.getValue().toString());
        }
        return document;
    }

    public String getCollectionName() {
        return this.collection.getCollectionName();
    }
}

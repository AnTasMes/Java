package antasmes.MongoDB.Records;

import java.util.Map;

import antasmes.MongoDB.IAddKeys;
import antasmes.MongoDB.Insertable;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;

public abstract class Record extends Insertable {

    public Record(Map<LogKeys, Object> map) {
        super(map);
    }

    public Record() {
        super();
    }

    public void addKeys(IAddKeys addKeys) {
        addKeys.addKeys(map);
    }

    public void addKeys(Map<LogKeys, Object> map) {
        this.map = map;
    }

    public void addKey(LogKeys key, Object value) {
        map.put(key, value);
    }

    public Map<LogKeys, Object> getKeys() {
        return this.map;
    }

    public void setType(LogType type) {
        this.map.put(LogKeys.LOG_TYPE, (Object) type);
    }
}

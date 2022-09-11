package antasmes.MongoDB.Records;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import antasmes.MongoDB.IAddKeys;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;

public class RecordBuilder {
    // napraviti klasu koja vraca record kada se zavrs build
    private Map<LogKeys, Object> map;

    public RecordBuilder() {
        this.map = new HashMap<LogKeys, Object>();

        this.map.put(LogKeys.TIMESTAMP, new Timestamp(System.currentTimeMillis()));
    }

    public Record build(LogType type) {

        this.map.put(LogKeys.LOG_TYPE, type);

        if (type == LogType.EXCEPTION) {
            return new ErrorLogRecord(map);
        } else {
            return new LogRecord(map);
        }
    }

    public RecordBuilder setTimestamp(Timestamp timestamp) {
        this.map.put(LogKeys.TIMESTAMP, timestamp);
        return this;
    }

    public RecordBuilder addKeys(Map<LogKeys, Object> map) {
        this.map.putAll(map);
        return this;
    }

    public RecordBuilder addKey(LogKeys key, Object value) {
        this.map.put(key, value);
        return this;
    }

    public RecordBuilder addKeys(IAddKeys addKeys) {
        this.map = addKeys.addKeys(map);
        return this;
    }
}

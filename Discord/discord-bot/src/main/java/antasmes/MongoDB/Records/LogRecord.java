package antasmes.MongoDB.Records;

import java.util.Map;

import antasmes.MongoDB.Types.DBCollections;
import antasmes.MongoDB.Types.LogKeys;

public class LogRecord extends Record {

    public LogRecord(Map<LogKeys, Object> map) {
        super(map);
        this.collection = DBCollections.STANDARD_LOG;
    }

    public LogRecord() {
        super();
        this.collection = DBCollections.STANDARD_LOG;
    }
}

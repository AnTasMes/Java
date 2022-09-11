package antasmes.MongoDB.Records;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import antasmes.MongoDB.Types.DBCollections;
import antasmes.MongoDB.Types.LogKeys;

public class ErrorLogRecord extends Record {
    public ErrorLogRecord(Map<LogKeys, Object> map) {
        super(map);
        this.collection = DBCollections.ERROR_LOG;

        validateException();
        translateException();
    }

    private void translateException() {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        Exception e = (Exception) map.get(LogKeys.EXCEPTION);
        e.printStackTrace(pw);

        String stackTrace = sw.toString();

        map.put(LogKeys.STACK_TRACE, stackTrace);
    }

    private void validateException() {
        if (map.get(LogKeys.EXCEPTION) == null) {
            throw new IllegalArgumentException("Exception is null -> Must set an exception");
        }
    }
}

package antasmes.MongoDB;

import java.util.Map;

import antasmes.MongoDB.Types.LogKeys;

@FunctionalInterface
public interface IAddKeys {
    public Map<LogKeys, Object> addKeys(Map<LogKeys, Object> map);
}

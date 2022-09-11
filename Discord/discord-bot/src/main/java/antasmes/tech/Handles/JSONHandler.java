package antasmes.tech.Handles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {
    private String filePath;
    private JSONObject jsonObject;

    public String getFile() {
        return filePath;
    }

    public JSONHandler(String filePath) {
        this.filePath = filePath;
        this.jsonObject = _readJSON();
    }

    public void printJSON() {
        System.out.println("JSONObject: " + getObject());
    }

    public Boolean find(Object key, Object value) {
        try {
            return jsonObject.get(key).equals(value);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * This function inserts value into a {@code JSONArray}
     * If the key is not an array, it will be converted to an array and the value
     * will be inserted, along with the current value
     * 
     * @param key
     * @param value
     */
    @SuppressWarnings("unchecked")
    public void insertIntoArray(Object key, Object value) {
        JSONArray jsonArray = checkArray(key);
        Object currentValue = getValue(key);
        if (jsonArray == null) {
            if (value.getClass().isArray()) {
                jsonArray = setArray(value);
            } else {
                jsonArray = setArray(new Object[] { value });
            }

            if (currentValue != null) {
                jsonArray.add(currentValue);
            }
        } else {
            if (value.getClass().isArray()) {
                jsonArray = setArray(key, value);
            } else {
                jsonArray.add(value);
            }
        }

        jsonObject.put(key, jsonArray);

        write();
    }

    @SuppressWarnings("unchecked")
    public void setValue(Object key, Object value) {
        if (value.getClass().isArray()) {
            jsonObject.put(key, setArray(value));
        } else {
            jsonObject.put(key, value);
        }
        write();
    }

    public Object getValue(Object key) {
        try {
            return jsonObject.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * ***********************************************************
     * ***********************************************************
     * ******************** PRIVATE FUNCTIONS ********************
     * ***********************************************************
     * ***********************************************************
     */

    private void write() {
        try {
            Files.write(Paths.get(filePath), jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public JSONArray setArray(Object key, Object value) {
        JSONArray jsonArray = checkArray(key);
        for (Object o : (Object[]) value) {
            jsonArray.add(o);
        }
        return jsonArray;
    }

    @SuppressWarnings("unchecked")
    public JSONArray setArray(Object value) {
        JSONArray jsonArray = new JSONArray();
        for (Object o : (Object[]) value) {
            jsonArray.add(o);
        }
        return jsonArray;
    }

    public JSONObject getObject() {
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONArray checkArray(Object key) {
        try {
            return (JSONArray) jsonObject.get(key);
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private JSONObject _readJSON() {
        // reads the json file
        try (FileReader reader = new FileReader(filePath)) {
            Object value = new JSONParser().parse(reader);

            JSONArray array = new JSONArray();
            array.add(value);
            JSONObject object = (JSONObject) array.get(0);

            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

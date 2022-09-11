package antasmes.tech.Handles;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

// mora podhitno da se sredi
@Deprecated(since = "1.0.0", forRemoval = true)
public class JSONHandler_1 {
    private static String jsonFile;
    private static JSONParser parser;
    private static JSONObject _jsonObject;

    public JSONHandler_1(String file) {
        jsonFile = file;
        parser = new JSONParser(); // mislim da mi ne treba ovaj parser kao deo klase.... samo za read
        _jsonObject = _readJSON();
    }

    public Object getValue(Object key) {
        // gets the value from the json object
        try {
            return _jsonObject.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public void setValue(String key, Object[] value) {
        JSONArray array = _isArray(key);

        if (value.length > 1) {
            // if value is supposed to be an array
            // make the key value an array type
            JSONArray nonArray = _setArray(new JSONArray(), value);
            _jsonObject.put(key, nonArray);
        } else if (value.length == 1) {
            _jsonObject.put(key, value[0]);
        } else {
            if (array != null) {
                _jsonObject.put(key, new JSONArray());
            } else {
                _jsonObject.put(key, "");
            }
        }
        _writeToJSON();
    }

    @SuppressWarnings("unchecked")
    public void insertIntoJSON(String key, Object[] value) {
        JSONArray jsonArray = _isArray(key);
        if (jsonArray != null) {
            // put value to JSONArray type
            _setArray(jsonArray, value);
        } else {
            // srediti ovo ugnjezdavanje ovde sa if else
            if (value.length > 1) {
                // if value is supposed to be an array
                // make the key value an array type
                JSONArray nonArray = _setArray(new JSONArray(), value);
                _jsonObject.put(key, nonArray);
            } else {
                // put value to key
                _jsonObject.put(key, value[0]);
            }
        }
        _writeToJSON();
    }

    /*
     * ***********************************************************
     * ***********************************************************
     * *********************PRIVATE FUNCTIONS*********************
     * ***********************************************************
     * ***********************************************************
     */

    @SuppressWarnings("unchecked")
    private static JSONObject _readJSON() {
        // reads the json file
        try (FileReader reader = new FileReader(jsonFile)) {
            Object value = parser.parse(reader);

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

    private static void _writeToJSON() {
        // finalize JSON writing
        try {
            Files.write(Paths.get(jsonFile), _jsonObject.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONArray _isArray(String key) {
        // check if key is an array
        try {
            JSONArray outArray = (JSONArray) _jsonObject.get(key);
            return outArray;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private static JSONArray _setArray(JSONArray jsonArray, Object[] array) {
        // puts values to JSONArray type in file
        for (int i = 0; i < array.length; i++) {
            jsonArray.add(array[i]);
        }
        return jsonArray;
    }
}
package antasmes.MongoDB;

import org.json.simple.JSONArray;

import antasmes.tech.bot;
import antasmes.tech.Handles.JSONHandler;
import kotlin.jvm.functions.Function0;

public class Types {
    // make enum
    public enum LogType {
        STARTUP,
        MESSAGE,
        JOIN,
        LEAVE,
        KICK,
        BAN,
        UNBAN,
        CREATE_CHANNEL,
        DELETE_CHANNEL,
        RENAME_CHANNEL,
        BITRATE_CHANGE_CHANNEL,

        CREATE_ROLE,
        DELETE_ROLE,
        RENAME_ROLE,
        COLOR_CHANGE_ROLE,
        PERMISSION_CHANGE_ROLE,

        VOICE_STATE_UPDATE,
        COMMAND_EXECUTION,
        LOG,
        EXCEPTION
    }

    public enum LogKeys {
        CHANNEL_TYPE("channel_type"),
        CHANNEL_ID("channel_id"),
        CHANNEL_NAME("channel_name"),
        MESSAGE("message"),
        TIMESTAMP("timestamp"),
        STACK_TRACE("stack_trace"),
        LOG_TYPE("type"),
        USER_ID("user_id"),
        USER_NAME("user_name"),
        USER_DISCRIMINATOR("user_discriminator"),
        USER_AVATAR_URL("user_avatar_url"),
        USER_AVATAR_ID("user_avatar_id"),
        BOT_STATUS("bot_status"),
        CHANNEL_OLD_NAME("channel_old_name"),
        CHANNEL_NEW_NAME("channel_new_name"),
        EXCEPTION("exception"),
        DAY("day"),
        DATE("date"),
        HIGH("high"),
        LOW("low"),
        PRECIPITATION("precipitation"),
        HAS_ALERT("has_alert"),
        WIND("wind"),
        HUMIDITY("humidity"),
        UV("uv"),
        HOUR("hour"),
        TEMP("temp"),
        REAL_FEEL("real_feel"),
        REAL_FEEL_SHADE("real_feel_shaded"),
        AIR_QUALITY("air_quality");

        private String key;

        private LogKeys(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

    }

    public enum ICType {

        VOICE_INF("infChannelIDs", ICType::getInf),
        VOICE_TMP("tmpChannelIDs", ICType::getTmp);

        private String JSONKey;
        private Function0<JSONArray> handler;

        private ICType(String JSONKey, Function0<JSONArray> function) {
            this.JSONKey = JSONKey;
            this.handler = function;
        }

        public Function0<JSONArray> getHandler() {
            return handler;
        }

        public void setHandler(Function0<JSONArray> handler) {
            this.handler = handler;
        }

        public String getJSONKey() {
            return this.JSONKey;
        }

        public void setJSONKey(String JSONKey) {
            this.JSONKey = JSONKey;
        }

        public static JSONArray getInf() {
            return (JSONArray) new JSONHandler(bot.channels).getValue("infChannelIDs");

        }

        public static JSONArray getTmp() {
            return (JSONArray) new JSONHandler(bot.channels).getValue("tmpChannelIDs");
        }
    }

    public enum DBCollections {
        USERS("users"),
        STANDARD_LOG("standard_log"),
        ERROR_LOG("error_log"),
        FORECAST("forecast");

        private String collectionName;

        private DBCollections(String collectionName) {
            this.collectionName = collectionName;
        }

        public String getCollectionName() {
            return this.collectionName;
        }

        public void setCollectionName(String collectionName) {
            this.collectionName = collectionName;
        }
    }
}

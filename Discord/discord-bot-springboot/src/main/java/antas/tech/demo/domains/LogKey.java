package antas.tech.demo.domains;

public enum LogKey {
    CHANNEL_TYPE("channel_type"),
    CHANNEL_ID("channel_id"),
    CHANNEL_NAME("channel_name"),
    USER_ID("user_id"),
    USER_NAME("user_name"),
    USER_DISCRIMINATOR("user_discriminator"),
    USER_AVATAR_URL("user_avatar_url"),
    USER_AVATAR_ID("user_avatar_id"),
    BOT_STATUS("bot_status"),
    CHANNEL_OLD_NAME("channel_old_name"),
    CHANNEL_NEW_NAME("channel_new_name"),
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
    AIR_QUALITY("air_quality"),
    COMMAND_NAME("command_name"),
    COMMAND_CLASS("command_class"),
    COMMAND_ID("command_id"),
    COMMAND_OPTIONS("command_options");

    private String key;

    private LogKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
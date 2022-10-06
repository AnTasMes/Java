package antasmes.tech;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import antasmes.MongoDB.Database;
import antasmes.MongoDB.LogManager;
import antasmes.MongoDB.Records.LogRecord;
import antasmes.MongoDB.Records.RecordBuilder;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;
import antasmes.tech.Handles.JSONHandler;
import antasmes.tech.Listeners.ChannelUpdateListener;
import antasmes.tech.Listeners.MessageListener;
import antasmes.tech.Listeners.VoiceStateListener;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class bot {
    public static String prefix = "-";
    public static String secrets = "src\\main\\java\\antasmes\\secrets.json";
    public static String channels = "src\\main\\java\\antasmes\\channels.json";
    public static String test = "src\\main\\java\\antasmes\\tech\\Handles\\test.json";
    public static JDA jda;
    public static Guild mainGuild;
    public static Database database;
    public static LogManager logManager;

    private static String main_database = "DiscordUnitedHub";
    private static JSONHandler jsonHandler;

    private static String token;

    public static void main(String[] args) {
        jsonHandler = new JSONHandler(secrets);
        token = (String) jsonHandler.getValue("token");
        database = new Database("localhost", main_database);

        String guildID = (String) jsonHandler.getValue("guild");

        startBot(token);

        mainGuild = jda.getGuildById(guildID);

        jda.addEventListener(new MessageListener());
        jda.addEventListener(new VoiceStateListener());
        jda.addEventListener(new ChannelUpdateListener());

        logManager = new LogManager(main_database);
        // Waits for bot to be fully connected

        // Inserts all players from the server into the database
        database.setCollection("Users");
        database.insert(getMembers(), "id");

        // Dodati da se korisnici korisnici promene ako im se neki podatak promeni (id
        // im uvek ostaje isti, pa po tome proveriti)

        // Napraviti metodu 'isFullySame' ili tako nesto za detaljnu proveru ili
        // (isUpdated)

        LogRecord botStartup = (LogRecord) new RecordBuilder()
                .addKeys(map -> {
                    map.put(LogKeys.MESSAGE, "Bot started");
                    map.put(LogKeys.USER_NAME, jda.getSelfUser().getName());
                    map.put(LogKeys.USER_ID, jda.getSelfUser().getId());
                    map.put(LogKeys.BOT_STATUS, jda.getStatus().toString());
                    return map;
                }).build(LogType.STARTUP);

        // logManager.log(botStartup);

        // AccuWeather accuWeather = new AccuWeather("Zagreb");
        // accuWeather.getForecast(ForecastType.CURRENT); // srediti ovaj exception
    }

    /**
     * Waits for the bot to reach the specified status
     * 
     * @param status
     * @param seconds number of seconds to wait for the bot to reach the specified
     *                status
     * @return true if the bot has reached the specified status, false otherwise
     * 
     * @see https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/JDA.Status.html
     */
    private static Boolean waitForStatus(JDA.Status status, int seconds) {
        int cnt = 0;
        while (jda.getStatus() != status && cnt <= seconds) {
            try {
                Thread.sleep(1000);
                cnt += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (jda.getStatus() != status) {
            return false;
        }
        return true;
    }

    /**
     * Starts the bot
     * 
     * @param token Bot authentication token
     * 
     * @see https://discord.com/developers/docs/topics/oauth2
     */
    private static void startBot(String token) {
        try {
            jda = JDABuilder
                    .createDefault(token,
                            GatewayIntent.MESSAGE_CONTENT,
                            GatewayIntent.GUILD_MESSAGES,
                            GatewayIntent.GUILD_MEMBERS,
                            GatewayIntent.GUILD_VOICE_STATES)
                    .setActivity(Activity.listening("your call"))
                    .build();

            if (waitForStatus(JDA.Status.CONNECTED, 10)) {
                System.out.println("Bot is connected");
            } else {
                System.out.println("Bot is not connected");
            }
        } catch (LoginException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Moves a user to a new channel based on a channel id
     * 
     * @param member    user to be moved
     * @param channelID channel to be moved to
     * @param guildID
     */
    public static void moveUser(Member member, String channelID, String guildID) {
        try {
            jda.getGuildById(guildID)
                    .moveVoiceMember(member, jda.getVoiceChannelById(channelID))
                    .queue();
        } catch (Exception e) {
            System.out.println("Error moving user");
        }
    }

    // public static void insertIntoJSON(String key, String[] value) {
    // JSONHandler handler = new JSONHandler(tmps);
    // handler.insertIntoJSON(key, value);
    // }

    /**
     * @return List of members put into maps
     */
    public static List<Map<String, String>> getMembers() {
        List<Member> loadedMembers = mainGuild.loadMembers().get();

        List<Map<String, String>> out = new ArrayList<Map<String, String>>();

        // Formats each member into a map
        loadedMembers.forEach(member -> {
            Map<String, String> memberMap = new HashMap<String, String>();
            memberMap.put("id", member.getUser().getId());
            memberMap.put("username", member.getUser().getName());
            memberMap.put("discriminator", member.getUser().getDiscriminator());
            memberMap.put("nickname", member.getNickname());

            out.add(memberMap);
        });

        return out;
    }
}

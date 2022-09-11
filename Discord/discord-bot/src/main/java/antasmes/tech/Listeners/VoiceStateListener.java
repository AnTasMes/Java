package antasmes.tech.Listeners;

import antasmes.MongoDB.Records.LogRecord;
import antasmes.MongoDB.Records.RecordBuilder;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;
import antasmes.tech.bot;
import antasmes.tech.Handles.InfiniteVoiceHandler;
import antasmes.tech.Handles.VoiceStateHandler;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class VoiceStateListener extends ListenerAdapter {
    private VoiceStateHandler vsHandler;

    private InfiniteVoiceHandler infVoiceHandler;

    public void onGuildVoiceUpdate(GuildVoiceUpdateEvent event) {
        vsHandler = new VoiceStateHandler(event);

        vsHandler.setMember(event.getMember());
        vsHandler.setUser(event.getMember().getUser());

        String[] channels = vsHandler.getCurrentChannels();
        String leftChannelID = channels[0];
        String joinedChannelID = channels[1];

        LogRecord guildVoiceUpdateRecord = (LogRecord) new RecordBuilder()
                .addKeys(map -> {
                    map.put(LogKeys.USER_ID, vsHandler.getUser().getId());
                    map.put(LogKeys.USER_NAME, vsHandler.getUser().getName());
                    map.put(LogKeys.MESSAGE,
                            String.format("leftChannelID: %s, joinedChannelID: %s", leftChannelID, joinedChannelID));

                    return map;
                }).build(LogType.VOICE_STATE_UPDATE);

        bot.logManager.log(guildVoiceUpdateRecord);

        infVoiceHandler = new InfiniteVoiceHandler(event, vsHandler);
        infVoiceHandler.create(joinedChannelID, leftChannelID);
    }

    public void onGuildVoiceMove(GuildVoiceMoveEvent event) {

    }

    public void onGUildVoiceJoin(GuildVoiceJoinEvent event) {

    }

    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {

    }

}

package antasmes.tech.Listeners;

import antasmes.MongoDB.Records.LogRecord;
import antasmes.MongoDB.Records.RecordBuilder;
import antasmes.MongoDB.Types.ICType;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;
import antasmes.tech.bot;
import antasmes.tech.Handles.InfiniteVoiceHandler;
import net.dv8tion.jda.api.events.channel.ChannelCreateEvent;
import net.dv8tion.jda.api.events.channel.ChannelDeleteEvent;
import net.dv8tion.jda.api.events.channel.update.ChannelUpdateNameEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChannelUpdateListener extends ListenerAdapter {
    LogRecord record = new LogRecord();

    public void onChannelUpdateName(ChannelUpdateNameEvent event) {
        LogRecord channelUpdateRecord = (LogRecord) new RecordBuilder()
                .addKeys(map -> {
                    map.put(LogKeys.CHANNEL_ID, event.getChannel().getId());
                    map.put(LogKeys.CHANNEL_OLD_NAME, event.getOldValue());
                    map.put(LogKeys.CHANNEL_NEW_NAME, event.getNewValue());
                    map.put(LogKeys.CHANNEL_TYPE, event.getChannel().getType());
                    return map;
                }).build(LogType.RENAME_CHANNEL);

        bot.logManager.log(channelUpdateRecord);
    }

    public void onChannelCreate(ChannelCreateEvent event) {
        LogRecord channelCreateRecord = (LogRecord) new RecordBuilder()
                .addKeys(map -> {
                    map.put(LogKeys.CHANNEL_ID, event.getChannel().getId());
                    map.put(LogKeys.CHANNEL_NAME, event.getChannel().getName());
                    map.put(LogKeys.CHANNEL_TYPE, event.getChannel().getType());
                    return map;
                }).build(LogType.CREATE_CHANNEL);

        bot.logManager.log(channelCreateRecord);
    }

    public void onChannelDelete(ChannelDeleteEvent event) {
        LogRecord channelDeleteRecord = (LogRecord) new RecordBuilder()
                .addKeys(map -> {
                    map.put(LogKeys.CHANNEL_ID, event.getChannel().getId());
                    map.put(LogKeys.CHANNEL_NAME, event.getChannel().getName());
                    map.put(LogKeys.CHANNEL_TYPE, event.getChannel().getType());
                    return map;
                }).build(LogType.DELETE_CHANNEL);

        new InfiniteVoiceHandler().delete(event.getChannel().getId(),
                ICType.VOICE_INF);
        new InfiniteVoiceHandler().delete(event.getChannel().getId(),
                ICType.VOICE_TMP);

        bot.logManager.log(channelDeleteRecord);
    }
}

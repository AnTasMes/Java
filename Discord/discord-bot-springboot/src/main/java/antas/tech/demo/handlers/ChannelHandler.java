package antas.tech.demo.handlers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import antas.tech.demo.exceptions.InvalidChannelTypeException;
import antas.tech.demo.models.ServerChannel;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.entities.channel.middleman.StandardGuildChannel;

public class ChannelHandler {

    public static StandardGuildChannel createStandardGuildChannel(@Nonnull String type, @Nonnull String channelName,
            @Nonnull String categoryID, Guild guild) throws InvalidChannelTypeException {
        switch (type) {
            case "VOICE":
                return guild.createVoiceChannel(channelName, guild.getCategoryById(categoryID)).complete();
            case "TEXT":
                return guild.createTextChannel(channelName, guild.getCategoryById(categoryID)).complete();
            case "STAGE":
                return guild.createStageChannel(channelName, guild.getCategoryById(categoryID)).complete();
            case "NEWS":
                return guild.createNewsChannel(channelName, guild.getCategoryById(categoryID)).complete();
            default:
                throw new InvalidChannelTypeException(
                        "Channel type" + type + " is invalid => (VOICE, TEXT, STAGE, NEWS)");
        }
    }

    public static List<ServerChannel> resolveChannels(List<GuildChannel> channels) {
        return channels.stream()
                .map(channel -> new ServerChannel(
                        channel.getName(),
                        channel.getId(),
                        channel.getType().toString()))
                .collect(Collectors.toList());
    }

    public static ServerChannel resolveChannel(StandardGuildChannel channel) {
        return new ServerChannel(
                channel.getName(),
                channel.getId(),
                channel.getType().toString());
    }

    public static void syncChannel(@Nonnull StandardGuildChannel channel) {
        channel.getManager().sync().complete();
    }
}

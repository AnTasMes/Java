package antasmes.tech.Handles;

import antasmes.tech.bot;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.StandardGuildChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;

public class ChannelHandler {
    private Guild guild;

    public ChannelHandler() {
        this.guild = bot.mainGuild;
    }

    public static Boolean isEmpty(String channelID) {
        return bot.jda.getVoiceChannelById(channelID).getMembers().size() == 0;
    }

    public VoiceChannel createVoiceChannel(String name) {
        try {
            return guild.createVoiceChannel(name).complete();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean deleteVoiceChannel(String channelID) {
        try {
            guild.getVoiceChannelById(channelID).delete().queue();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void renameChannel(String id, String new_name) {
        guild.getVoiceChannelById(id).getManager().setName(new_name).queue();
    }

    public ChannelType getChannelType(String channelID) {
        return guild.getVoiceChannelById(channelID).getType();
    }

    public VoiceChannel getVoiceChannel(String channelID) {
        return guild.getVoiceChannelById(channelID);
    }

    public StandardGuildChannel getChannelById(String channelID) {
        try {
            return guild.getVoiceChannelById(channelID);
        } catch (Exception e) {
            try {
                return guild.getTextChannelById(channelID);
            } catch (Exception e2) {
                return null;
            }
        }
    }

    public TextChannel getTextChannel(String channelID) {
        return guild.getTextChannelById(channelID);
    }

    public String getVoiceChannelName(String channelID) {
        return guild.getVoiceChannelById(channelID).getName();
    }

    public String getTextChannelName(String channelID) {
        return guild.getTextChannelById(channelID).getName();
    }

}

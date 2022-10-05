package antas.tech.demo.commands.category_management;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.handlers.RoleHandler;
import antas.tech.demo.models.UserRole;
import antas.tech.demo.services.ManagedChannelService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("create_channel_cat")
public class CreateChannel extends SlashCommand {

    ManagedChannelService channelService;

    public CreateChannel(ManagedChannelService channelService) {
        this.name = "add";
        this.help = "Adds a channel to your designated category";

        List<OptionData> options = new ArrayList<>();

        options.add(new OptionData(
                OptionType.STRING, "type", "Channel type")
                .setRequired(true)
                .addChoice("voice", "VOICE")
                .addChoice("text", "TEXT"));
        options.add(new OptionData(OptionType.STRING, "name", "Name of your channel").setRequired(true));

        this.options = options;

        this.channelService = channelService;
    }

    @Override
    @SuppressWarnings("null")
    protected void execute(SlashCommandEvent event) {
        event.deferReply().queue();

        String type = event.getOption("type").getAsString();
        String channelName = event.getOption("name").getAsString();

        List<UserRole> roles = RoleHandler.resolveRoles(event.getMember().getRoles());

        String categoryID = channelService.isAuthorized(roles);
        if (categoryID == null) {
            event.getHook().sendMessage("You dont have the required permissions").setEphemeral(true).queue();
            return;
        }

        if (type.equals("VOICE")) {
            resolveVoice(channelName, categoryID, event.getGuild());
        } else if (type.equals("TEXT")) {
            resolveText(channelName, categoryID, event.getGuild());
        }

        event.getHook().sendMessage("Completed").setEphemeral(true).queue();
    }

    private void resolveVoice(@Nonnull String channelName, @Nonnull String categoryID, Guild guild) {
        VoiceChannel voiceChannel = guild.createVoiceChannel(channelName,
                guild.getCategoryById(categoryID)).complete();

        voiceChannel.getManager().sync().complete();
    }

    private void resolveText(@Nonnull String channelName, @Nonnull String categoryID, Guild guild) {
        TextChannel textChannel = guild.createTextChannel(channelName,
                guild.getCategoryById(categoryID)).complete();

        textChannel.getManager().sync().complete();
    }
}

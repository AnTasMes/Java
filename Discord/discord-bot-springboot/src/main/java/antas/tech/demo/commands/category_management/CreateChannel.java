package antas.tech.demo.commands.category_management;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.exceptions.InvalidChannelTypeException;
import antas.tech.demo.handlers.ChannelHandler;
import antas.tech.demo.handlers.RoleHandler;
import antas.tech.demo.models.UserRole;
import antas.tech.demo.services.CategoryService;
import net.dv8tion.jda.api.entities.channel.middleman.StandardGuildChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("create_channel_cat")
public class CreateChannel extends SlashCommand {

    CategoryService categoryService;

    public CreateChannel(CategoryService categoryService) {
        this.name = "add";
        this.help = "Adds a channel to your designated category";

        List<OptionData> options = new ArrayList<>();

        options.add(new OptionData(
                OptionType.STRING, "type", "Channel type")
                .addChoice("voice", "VOICE")
                .addChoice("text", "TEXT")
                .setRequired(true));
        options.add(new OptionData(OptionType.STRING, "name", "Name of your channel").setRequired(true));

        this.options = options;

        this.categoryService = categoryService;
    }

    @Override
    @SuppressWarnings("null")
    protected void execute(SlashCommandEvent event) {
        event.deferReply().queue();

        String channelType = event.getOption("type").getAsString();
        String channelName = event.getOption("name").getAsString();
        List<UserRole> roles = RoleHandler.resolveRoles(event.getMember().getRoles());

        String categoryID = categoryService.isAuthorized(roles);

        if (categoryID == null) {
            event.getHook().sendMessage("You dont have the required permissions").setEphemeral(true).queue();
            return;
        }

        try {
            StandardGuildChannel channel = ChannelHandler.createStandardGuildChannel(channelType, channelName,
                    categoryID,
                    event.getGuild());

            ChannelHandler.syncChannel(channel);

            categoryService.addChild(categoryID, ChannelHandler.resolveChannel(channel));
        } catch (InvalidChannelTypeException typeException) {
            event.getHook().sendMessage("Channel type is invalid");
        }

        event.getHook().sendMessage("Completed").setEphemeral(true).queue();
    }

}

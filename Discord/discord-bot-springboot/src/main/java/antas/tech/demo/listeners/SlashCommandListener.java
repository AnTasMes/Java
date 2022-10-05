package antas.tech.demo.listeners;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import antas.tech.demo.domains.LogKey;
import antas.tech.demo.domains.LogType;
import antas.tech.demo.models.StdLog;
import antas.tech.demo.services.StdLogService;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

@Component("slashCommandListener")
public class SlashCommandListener extends ListenerAdapter {
    private final StdLogService stdLogService;

    @Autowired
    public SlashCommandListener(StdLogService stdLogService) {
        this.stdLogService = stdLogService;
    }

    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        resolveLog(event);
    }

    private void resolveLog(@Nonnull SlashCommandInteractionEvent event) {
        SlashCommandInteraction interaction = event.getInteraction();
        User user = event.getUser();

        StdLog commandLog = new StdLog(LogType.COMMAND_EXECUTION)
                .addContent(LogKey.COMMAND_NAME, interaction.getName())
                .addContent(LogKey.COMMAND_ID, interaction.getCommandId())
                .addContent(LogKey.CHANNEL_ID, interaction.getChannel().getId())
                .addContent(LogKey.CHANNEL_NAME, interaction.getChannel().getName())
                .addContent(LogKey.USER_ID, user.getId())
                .addContent(LogKey.USER_NAME, user.getName());

        List<String> optionMaps = event.getOptions()
                .stream()
                .map(option -> option.toString())
                .collect(Collectors.toList());

        if (!optionMaps.isEmpty()) {
            commandLog.addContent(LogKey.COMMAND_OPTIONS, optionMaps);
        }

        stdLogService.log(commandLog);
    }
}

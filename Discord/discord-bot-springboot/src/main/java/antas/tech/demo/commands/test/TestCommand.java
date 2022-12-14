package antas.tech.demo.commands.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("test")
public class TestCommand extends SlashCommand {

    public TestCommand() {
        this.name = "test";
        this.help = "Dev Testing command";

        List<OptionData> options = new ArrayList<>();

        options.add(new OptionData(OptionType.STRING, "category", "id"));
        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        event.reply("This is a test command").setEphemeral(true).queue();
    }
}

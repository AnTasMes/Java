package antas.tech.demo.commands.deleteuser;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.services.UserService;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

@Component("delete")
public class DeleteUser extends SlashCommand {

    private UserService userService;
    // private ErrLogService errLogService;

    @Autowired
    public DeleteUser(UserService userService) {
        this.name = "delete";
        this.help = "This deletes user from MongoDB";

        List<OptionData> options = new ArrayList<>();

        options.add(new OptionData(OptionType.STRING, "uid", "UID of user to delete").setRequired(true));

        this.options = options;

        this.userService = userService;
        // this.errLogService = errLogService;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        OptionMapping optionMap = event.getOption("uid");
        if (optionMap == null) {
            return;
        }
        String uid = optionMap.getAsString();
        if (userService.deleteUser(uid)) {
            event.reply("User deleted").setEphemeral(true).queue();
        } else {
            event.reply("No user deleted").setEphemeral(true).queue();
        }
    }
}

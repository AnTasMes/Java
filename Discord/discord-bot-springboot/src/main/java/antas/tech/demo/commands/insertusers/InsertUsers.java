package antas.tech.demo.commands.insertusers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;

import antas.tech.demo.handlers.RoleHandler;
import antas.tech.demo.models.User;
import antas.tech.demo.services.UserService;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;

@Component("insert")
public class InsertUsers extends SlashCommand {

    UserService userService;

    @Autowired
    public InsertUsers(UserService userService) {
        this.name = "insert";
        this.help = "Imports users";

        this.userService = userService;
    }

    @Override
    protected void execute(SlashCommandEvent event) {
        Guild guild = event.getGuild();

        if (guild == null) {
            return;
        }

        event.deferReply().queue();
        ;

        guild.loadMembers()
                .onSuccess(members -> {
                    int countSavedUsers = resolveSuccess(members).size();

                    event.getHook().sendMessage("Rows saved: " + countSavedUsers).setEphemeral(true).queue();
                }).onError(exception -> {
                    exception.printStackTrace();

                    event.getHook().sendMessage("There has been an error").setEphemeral(true).queue();
                });
    }

    private List<User> resolveSuccess(List<Member> members) {
        List<User> users = members.stream()
                .map(member -> new User(
                        member.getUser().getName(),
                        member.getUser().getDiscriminator(),
                        member.getUser().getId(),
                        RoleHandler.resolveRoles(member.getRoles())))
                .collect(Collectors.toList());

        return userService.insertUsers(users);
    }
}

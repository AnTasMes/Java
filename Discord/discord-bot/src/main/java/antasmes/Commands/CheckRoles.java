package antasmes.Commands;

import java.util.List;

import antasmes.Data.Roles.RoleEnum;
import antasmes.tech.Utilities;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CheckRoles extends Command {
    private RoleEnum role = RoleEnum.ADMINISTRATOR;

    public CheckRoles(MessageReceivedEvent event, String[] args) {
        super(event, args);
        super.role = this.role;

        if (checkRole()) {
            execute(args);
        }
    }

    @Override
    public void execute(String[] args) {
        MessageReceivedEvent mrce = (MessageReceivedEvent) event;
        List<Role> roles = mrce.getGuild().getRoles();

        System.out.println(mrce.getGuild().getName() + "");
        Utilities.printArray(roles.toArray());

    }
}

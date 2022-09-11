package antasmes.Commands;

import java.io.File;

import antasmes.Data.Roles.RoleEnum;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class ListCommands extends Command {
    private RoleEnum role = RoleEnum.ADMINISTRATOR;

    public ListCommands(MessageReceivedEvent event, String[] args) {
        super(event, args);
        super.role = this.role;

        if (checkRole()) {
            execute(args);
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            File f = new File("./src/main/java/antasmes/Commands/");
            String[] commandList = f.list();

            event.getChannel().sendMessage(getCommands(commandList).toString()).queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private StringBuilder getCommands(String[] commandList) {
        StringBuilder command = new StringBuilder();
        command.append("List of commands: \n");
        for (String s : commandList) {
            if (s.endsWith(".java") && !s.equals("Command.java")) {
                command.append(s.substring(0, s.length() - 5));
                command.append("\n");
            }
        }

        return command;
    }

}

package antasmes.Commands;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CreateChannel extends Command {

    public CreateChannel(MessageReceivedEvent event, String[] args) {
        super(event, args);
        // TODO Auto-generated constructor stub

        execute(args);
    }

    @Override
    public void execute(String[] args) {
        // TODO Auto-generated method stub

        System.out.println("Creating channel");
        Guild g = event.getGuild();

        VoiceChannel action = g.createVoiceChannel("Ch1").complete();

        // VoiceChannel vc = action.complete();

        System.out.println(action);
    }

}

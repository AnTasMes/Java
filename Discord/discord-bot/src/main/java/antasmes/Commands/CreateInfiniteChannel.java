package antasmes.Commands;

import antasmes.Data.Roles.RoleEnum;
import antasmes.tech.bot;
import antasmes.tech.Handles.ChannelHandler;
import antasmes.tech.Handles.JSONHandler;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class CreateInfiniteChannel extends Command {
    private RoleEnum role = RoleEnum.ADMINISTRATOR;
    private ChannelHandler channelHandler = new ChannelHandler();

    public CreateInfiniteChannel(MessageReceivedEvent event, String[] args) {
        super(event, args);
        super.role = this.role;

        if (checkRole()) {
            execute(args);
        }
    }

    @Override
    public void execute(String[] args) {
        try {
            // gets channel name
            String channelName = args[1];

            // creates channel and gets its id
            String infChannelID = channelHandler.createVoiceChannel(channelName).getId();

            // sends message to the origin channel
            event.getChannel().sendMessage("Infiinite channel created").queue();

            // add this channel id to the json file
            JSONHandler channelsJSON = new JSONHandler(bot.channels);
            channelsJSON.insertIntoArray("infChannelIDs", infChannelID);

        } catch (ArrayIndexOutOfBoundsException outofBoundsException) { //
            event.getChannel().sendMessage("You must provide the channel name").queue();
            outofBoundsException.printStackTrace();
        } catch (Exception e) {
            event.getChannel().sendMessage("Something went wrong").queue();
            e.printStackTrace();
        }
    }
}

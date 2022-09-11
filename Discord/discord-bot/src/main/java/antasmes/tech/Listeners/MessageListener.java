package antasmes.tech.Listeners;

import java.lang.reflect.InvocationTargetException;

import antasmes.MongoDB.Records.LogRecord;
import antasmes.MongoDB.Records.RecordBuilder;
import antasmes.MongoDB.Types.LogKeys;
import antasmes.MongoDB.Types.LogType;
import antasmes.tech.bot;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter {
    public String prefix = "-";
    private MessageReceivedEvent event = null;
    private String[] args = null;

    private LogRecord messageRecord;

    public void onMessageReceived(MessageReceivedEvent event) {

        messageRecord = (LogRecord) new RecordBuilder().addKeys(map -> {
            map.put(LogKeys.MESSAGE, event.getMessage().getContentRaw());
            map.put(LogKeys.CHANNEL_NAME, event.getChannel().getName());
            map.put(LogKeys.CHANNEL_ID, event.getChannel().getId());
            map.put(LogKeys.USER_NAME, event.getAuthor().getName());
            map.put(LogKeys.USER_ID, event.getAuthor().getId());
            return map;
        }).build(LogType.MESSAGE);

        if (event.getAuthor().isBot()) {
            return;
        }

        this.args = event.getMessage().getContentRaw().split("\\s+");
        this.event = event;

        // find command class from class loader: antasmes.Commands
        Class<?> commandClass = getCommand(args[0]);

        executeCommand(commandClass);

        bot.logManager.log(messageRecord);
    }

    /**
     * Returns the class of the command that is being called based on the name of
     * the command given on Discord
     * 
     * @param command the name of the command
     * 
     * @return the class of the command
     *
     */
    private Class<?> getCommand(String command) {
        try {
            command = command.replace(prefix, "");

            Class<?> act = Class.forName("antasmes.Commands." + command.replace(prefix,
                    ""));
            return act;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void executeCommand(Class<?> clazz) {
        // executes the command from the class
        if (clazz == null) {
            return;
        }

        try {
            clazz.getDeclaredConstructor(MessageReceivedEvent.class, String[].class).newInstance(event, args);

            messageRecord.setType(LogType.COMMAND_EXECUTION);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

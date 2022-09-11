package antasmes.tech.Handles;

import org.json.simple.JSONArray;

import antasmes.MongoDB.Types.ICType;
import antasmes.tech.Utilities;
import antasmes.tech.bot;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;

public class InfiniteVoiceHandler {
    private JSONHandler jsonHandler;
    private ChannelHandler channelHandler = new ChannelHandler();
    private GuildVoiceUpdateEvent event;
    private Member member;

    private JSONArray infChannels;
    private JSONArray tmpChannels;

    public InfiniteVoiceHandler(GuildVoiceUpdateEvent event, VoiceStateHandler vsHandler) {
        this.event = event;
        this.member = event.getMember();
        this.jsonHandler = new JSONHandler(bot.channels);
    }

    public InfiniteVoiceHandler() {
        this.jsonHandler = new JSONHandler(bot.channels);
    }

    /**
     * Manages the creation and deletion of temporary channels
     * If the user enter the channel which is in the json file, it creates a
     * temporary channel
     * and moves the user to it
     * 
     * If the user leaves the temporary channel, and the channel is therefore empty,
     * it gets deleted
     * 
     * @param joinedChannelID ID of the channel the user just joined
     * @param leftChannelID   ID of the channel the user just left
     */
    public void create(String joinedChannelID, String leftChannelID) {

        // gets the array of infinite, and temporary channels
        setInfJSONArray();
        setTmpJSONArray();

        String tmpChannelID = null;

        if (Utilities.isIn(joinedChannelID, infChannels.toArray())) {
            // if the user joined an infinite channel, it creates a temporary channel

            // gets the if of the temporary channel just created
            tmpChannelID = channelHandler.createVoiceChannel("tmpChannel-" + member.getUser().getName()).getId();

            // inserts new tmpChannelID into JSON
            jsonHandler.insertIntoArray("tmpChannelIDs", tmpChannelID);

            // moves the user to the new channel
            bot.moveUser(member, tmpChannelID, ((GuildVoiceUpdateEvent) event).getGuild().getId());
        }
        if (Utilities.isIn(leftChannelID, tmpChannels.toArray())) {
            // if the user left a temporary channel, it deletes it if it's empty

            // just delete the tmpChannel if empty
            if (ChannelHandler.isEmpty(leftChannelID)) {
                // removes value from JSONArray in the json file
                delete(leftChannelID, ICType.VOICE_TMP);
            }
        }
    }

    /**
     * Handles deletion and removal of data from the json file
     * Given the {@code type}, the data removal from json happens only for the given
     * channel type
     * 
     * The channel is then deleted by the {@code channelID}
     * 
     * 
     * @param channelID id to be removed from the json file
     * @param type      type of channel to be removed
     */
    public void delete(String channelID, ICType type) {
        JSONArray array = type.getHandler().invoke();

        if (array.remove(channelID)) {
            try { // There is a chance that the channel might be deleted by the user
                  // bot.jda.getVoiceChannelById(channelID).delete().queue(); // if the channel
                  // still exists, delete it
                channelHandler.deleteVoiceChannel(channelID);
            } finally {
                jsonHandler.setValue(type.getJSONKey(), array.toArray()); // removes the value from JSON
            }
        }
    }

    public void setTmpJSONArray() {
        this.tmpChannels = (JSONArray) jsonHandler.getValue("tmpChannelIDs");
    }

    public void setInfJSONArray() {
        this.infChannels = (JSONArray) jsonHandler.getValue("infChannelIDs");
    }
}
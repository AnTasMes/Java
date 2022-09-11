package antasmes.tech.Handles;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;

public class VoiceStateHandler {
    private GenericEvent event;
    private Member member;
    private User user;

    public String joinChannelID;
    public String leaveChannelID;

    public VoiceStateHandler(GenericEvent event) {
        this.event = event;
    }

    public String[] getCurrentChannels() {
        String leftChannelID = getChannelLeft();
        String joinedChannelID = getChannelJoin();

        return new String[] { leftChannelID, joinedChannelID };
    }

    private String getChannelLeft() {
        try {
            return ((GuildVoiceUpdateEvent) event).getChannelLeft().getId();
        } catch (Exception e) {
            return null;
        }
    }

    private String getChannelJoin() {
        try {
            return ((GuildVoiceUpdateEvent) event).getChannelJoined().getId();
        } catch (Exception e) {
            return null;
        }
    }

    public void setEvent(GenericEvent event) {
        this.event = event;
    }

    public GenericEvent getEvent() {
        return event;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJoinChannel() {
        return joinChannelID;
    }

    public void setJoinChannel(String joinChannel) {
        this.joinChannelID = joinChannel;
    }

    public String getLeaveChannel() {
        return leaveChannelID;
    }

    public void setLeaveChannel(String leaveChannel) {
        this.leaveChannelID = leaveChannel;
    }

}

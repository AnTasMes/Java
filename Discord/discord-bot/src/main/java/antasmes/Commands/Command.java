package antasmes.Commands;

import antasmes.Data.Roles.RoleEnum;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class Command {
    protected RoleEnum role;
    public MessageReceivedEvent event;
    public Member member;

    public Command(MessageReceivedEvent event, String[] args) {
        this.event = event;
        this.member = event.getMember();
    }

    public abstract void execute(String[] args);

    public Boolean checkRole() {
        return member.getRoles().stream().anyMatch(role -> role.getId().equals(this.role.getId()));
    }
}

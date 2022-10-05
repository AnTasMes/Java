package antas.tech.demo.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import antas.tech.demo.commands.category_management.CombineCatRole;
import antas.tech.demo.commands.category_management.CreateChannel;
import antas.tech.demo.commands.deleteuser.DeleteUser;
import antas.tech.demo.commands.insertusers.InsertUsers;
import antas.tech.demo.commands.test.TestCommand;
import antas.tech.demo.listeners.SlashCommandListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

@RequiredArgsConstructor
@Service
public class BotService {

        private final DeleteUser deleteUser;
        private final InsertUsers insertUsers;
        private final TestCommand testCommand;
        private final SlashCommandListener slashCommandListener;
        private final CombineCatRole combineCatRole;
        private final CreateChannel createChannel;

        public static JDA client;

        @Value("${bot.config.token}")
        private String token;

        @PostConstruct
        public void init() {
                client = JDABuilder
                                .createDefault(token,
                                                GatewayIntent.MESSAGE_CONTENT,
                                                GatewayIntent.GUILD_MESSAGES,
                                                GatewayIntent.GUILD_MEMBERS,
                                                GatewayIntent.GUILD_VOICE_STATES)
                                .setActivity(Activity.listening("your call"))
                                .build();

                CommandClientBuilder builder = new CommandClientBuilder()
                                .setOwnerId(client.getSelfUser().getId())
                                .addSlashCommand(deleteUser).forceGuildOnly("545273843338575893")
                                .addSlashCommand(insertUsers).forceGuildOnly("545273843338575893")
                                .addSlashCommand(testCommand).forceGuildOnly("545273843338575893")
                                .addSlashCommand(combineCatRole).forceGuildOnly("545273843338575893")
                                .addSlashCommand(createChannel).forceGuildOnly("545273843338575893");

                CommandClient cmdClient = builder.build();

                client.addEventListener(cmdClient);
                client.addEventListener(slashCommandListener);
        }
}

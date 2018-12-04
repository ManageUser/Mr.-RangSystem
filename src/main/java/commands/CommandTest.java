package commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;

public class CommandTest extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("!t")){
            event.getGuild().getDefaultChannel().sendMessage(new EmbedBuilder().setColor(Color.CYAN).setDescription("Check Successful!").build()).complete();
        }
    }
}

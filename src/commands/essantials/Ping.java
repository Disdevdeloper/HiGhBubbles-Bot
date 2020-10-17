package commands.essantials;

import main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		long time = System.currentTimeMillis();
		if(e.getMessage().getContentDisplay().equalsIgnoreCase(Main.prefix + "ping")) {
			e.getChannel().sendMessage("Pong").queue(response /* => Message */ -> {
                response.editMessage("> Ping: `" + (System.currentTimeMillis() - time) + " ms`").queue();
            });
		}
	}
}


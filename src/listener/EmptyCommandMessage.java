package listener;

import main.Main;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class EmptyCommandMessage extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		if(e.getMessage().getContentRaw().equalsIgnoreCase(Main.prefix )) {
			e.getChannel().sendMessage("You forgot somethink").queue();;
		}
	}

}

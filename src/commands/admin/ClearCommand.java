package commands.admin;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ClearCommand extends ListenerAdapter {

	List<Color> ColorList = Arrays.asList(Color.GREEN, Color.BLACK, Color.CYAN, Color.BLUE, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
	Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
	
		public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		Member m = e.getMember();
		TextChannel channel = e.getChannel();
		Message message = e.getMessage();
		if(message.getContentRaw().startsWith(Main.prefix + "clear")) {
			
		if(m.hasPermission(channel, Permission.MESSAGE_MANAGE)) {
			message.delete().queue();
			String[] args = message.getContentDisplay().split(" ");
			
			// !clear 3
			if(args.length == 2) {
				
				try {	
					
					int amount = Integer.parseInt(args[1]);
					channel.purgeMessages(get(channel, amount));
					channel.sendMessage(new EmbedBuilder()
							.setDescription(amount + " messages deleted!")
							.setColor(Color.PINK)
							.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
					return;
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		}else
			channel.sendMessage(Main.noPerm .build()).queue();
	
		}
		}
	public List<Message> get(MessageChannel channel, int amount) {
		List<Message> messages = new ArrayList<>();
		int i = amount + 1;
		
		for(Message message : channel.getIterableHistory().cache(false)) {
			if(!message.isPinned()) {
				messages.add(message);
			}
			if(--i <= 0) break;
		}
		
		
		return messages;
	}
	
}

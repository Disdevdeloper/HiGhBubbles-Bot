package commands.developer;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.LiteSQL;

public class Shutdown extends ListenerAdapter{
	
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		TextChannel ch = e.getChannel();
		if(e.getMessage().getContentDisplay().startsWith(Main.prefix + "notaus")) {
			if(Main.OwnerIds.contains(e.getAuthor().getIdLong())) {
				ch.sendMessage(new EmbedBuilder()
						.setDescription("Bot fährt runter!")
						.setColor(Color.YELLOW)
						.setFooter(Main.footer)
						.build()).queue();
				e.getMessage().addReaction("U+1F44C").queue();
				LiteSQL.disconnect();
				

				
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						timer.cancel();
						e.getJDA().shutdownNow();
					}
				}, 1*1000);
				
				
				
				
			}else
				ch.sendMessage(Main.noPerm.build()).complete().delete().queueAfter(3, TimeUnit.SECONDS);
		}
		
	}

}
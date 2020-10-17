package commands.essantials;

import java.awt.Color;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Region;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Guild.VerificationLevel;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ServerInfo extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		TextChannel ch = event.getChannel();
		Message mes = event.getMessage();
		Guild g = event.getGuild();
		
		//Guild daten
		String name = event.getGuild().getName();
		String owner = g.getOwner().getAsMention();
		VerificationLevel veri = g.getVerificationLevel();
		int boost = g.getBoostCount();
		int count = g.getMemberCount();

		Region region = g.getRegion();
	
		if(g.getSystemChannel() == null) {
			String infoch = "None";
			if(mes.getContentDisplay().equalsIgnoreCase(Main.prefix + "serverinfo")) {
				
				ch.sendMessage(new EmbedBuilder()
				.setTitle("Server Infos:")
				.setFooter(Main.footer)
				.setColor(Color.GREEN)	

				.setDescription("Name: " + name + "\n Owner: " + owner + "\n Verification Level: " + veri + "\n Boost Level: " + boost + "\n Memeber Count: " +  count + " \n Picture: " + g.getIconUrl() + "\n Region: " + region + "\n SystemChannel: " + infoch)
				
				.build()).queue();
				
			}
		}else {
		String	infoch = g.getSystemChannel().getName();
		if(mes.getContentDisplay().equalsIgnoreCase(Main.prefix + "serverinfo")) {
			
			ch.sendMessage(new EmbedBuilder()
			.setTitle("Server Infos:")
			.setFooter(Main.footer)
			.setColor(Color.GREEN)	

			.setDescription("Name: " + name + "\n Owner: " + owner + "\n Verification Level: " + veri + "\n Boost Level: " + boost + "\n Memeber Count: " +  count + " \n Picture: " + g.getIconUrl() + "\n Region: " + region + "\n SystemChannel: " + infoch)
			
			.build()).queue();
			
		}
		}
		
		
		
	}
}
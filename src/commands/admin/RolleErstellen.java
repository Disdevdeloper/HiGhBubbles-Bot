package commands.admin;

import java.awt.Color;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RolleErstellen extends ListenerAdapter{
	
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
	if(event.getMessage().getContentDisplay().startsWith(Main.prefix + "addrole")){
		TextChannel channel = event.getChannel();
		Guild guild = event.getGuild();
		Message message = event.getMessage();
		String[] args = message.getContentDisplay().split(" ");
		int length = args.length;
		
		if(length > 1) {
			StringBuilder builder = new StringBuilder();
			
			if(args[length-1].startsWith("#") && length > 2){
				
				for(int i = 1; i < length-1; i++) builder.append(args[i] + " ");
				String hexcode = args[length-1];
				
				String rolename = builder.toString().trim();
				channel.sendTyping().queue();
				
				guild.createRole().queue(role -> {
					Color color = Color.decode(hexcode);
					role.getManager().setName(rolename).setColor(color).queue();
					EmbedBuilder emb= new EmbedBuilder();
					emb.setColor(color);
					emb.setDescription("Role " + rolename + " is created");
					channel.sendMessage(emb.build()).queue();			});
				
			}else {
				for(int i = 1; i < length; i++) builder.append(args[i] + " ");
				String rolename = builder.toString().trim();
				channel.sendTyping();
				guild.createRole().queue(role -> {
			
					role.getManager().setName(rolename).queue();
					EmbedBuilder emb= new EmbedBuilder();
					
					emb.setDescription("Role " + rolename + " is created");
					channel.sendMessage(emb.build()).queue();			});
				
			}
			
		}else 
			channel.sendMessage(Main.Eingabe.build()).queue();
		
	
		
	}
	}
}

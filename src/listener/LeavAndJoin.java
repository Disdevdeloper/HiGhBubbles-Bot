package listener;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@SuppressWarnings("deprecation")
public class LeavAndJoin extends ListenerAdapter{
	
	@Override
	   public void onGuildMemberJoin(GuildMemberJoinEvent event) {
		
		net.dv8tion.jda.api.entities.Member m = event.getMember();
		
		
		TextChannel channel; 
		
		
		if((channel = event.getGuild().getSystemChannel()) != null) {
			channel.sendMessage("Welcome to our Server " + m.getAsMention()).queue();
			
		}
		
		
	}
	
	
	@Override
	   public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
		
		net.dv8tion.jda.api.entities.Member m = event.getMember();
		
		TextChannel channel; 
		
		
		if((channel = event.getGuild().getSystemChannel()) != null) {
			channel.sendMessage(m.getAsMention() + " leave our Server. Say Goodbye").queue();
			
		}
		
		
	}

}
package commands.admin;

import java.awt.Color;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
		TextChannel ch = e.getChannel();
		Member m = e.getMember();
		if(e.getMessage().getContentDisplay().startsWith(Main.prefix + "ban")) {
		if(m.hasPermission(Permission.BAN_MEMBERS)) {
			Message message = e.getMessage();
			List<Member> bannedname = e.getMessage().getMentionedMembers();
			Member banned = bannedname.get(0);
			String[] args = message.getContentDisplay().replaceAll("  ", " ").split(" ");
			int length = args.length;
			long bannedid = banned.getIdLong();
			if(banned.hasPermission(Permission.BAN_MEMBERS)) {
				ch.sendMessage(new EmbedBuilder().setDescription("You cant ban a member how has the Permission: Ban User").setFooter(Main.footer).setColor(Color.RED).build()).queue();
			}else {
			if(length == 2) {
			Main.jda.getUserById(bannedid).openPrivateChannel().queue(channel ->{
				channel.sendMessage("You are banned from the Server: " + e.getGuild().getName());
			});
			ch.sendMessage("The User: "+ banned.getAsMention() + " was banned from " +e.getMember().getAsMention() ).queue();
			
			Timer timee = new Timer();
			timee.schedule(new TimerTask() {
				
				@Override
				public void run() {
					e.getGuild().ban(banned.getId(), 0).queue();
					timee.cancel();
					
				}
			}, 1*1000);	

			}else if(length >= 3) {
				
				StringBuilder strbuild = new StringBuilder();
				
				for(int i = 2; i < length; i++) strbuild.append(args[i] + " ");
				
				String grund = strbuild.toString().trim();
				
				Main.jda.getUserById(bannedid).openPrivateChannel().queue(channel -> { 
	           	       
	          		  channel.sendTyping();
	        	        channel.sendMessage(new EmbedBuilder()
	                          .setColor(Color.YELLOW)
	                          .setDescription("The User: "+ banned.getAsMention() + " was banned from " +e.getMember().getAsMention() + " because: " + grund)
	                          .setFooter(Main.footer)
	                        .build()).queue();
	        	        		
	        	    });
				 ch.sendMessage("The User: "+ banned.getAsMention() + " was banned from " +e.getMember().getAsMention() + " because: " + grund);
				
				Timer time = new Timer();
				time.schedule(new TimerTask() {
					
					@Override
					public void run() {
						e.getGuild().ban(banned.getId(), 0, grund).queue();
						time.cancel();
						
					}
				}, 1*1000);

			}else	
				ch.sendMessage(Main.Eingabe.build()).queue();
			}
		}else
			ch.sendMessage(Main.noPerm.build()).queue();
		}
		
	}


}
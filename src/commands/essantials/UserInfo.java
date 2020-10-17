package commands.essantials;

import java.awt.Color;
import java.time.OffsetDateTime;
import java.util.EnumSet;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class UserInfo extends ListenerAdapter{
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		Message mes = event.getMessage();
		if(mes.getContentDisplay().startsWith(Main.prefix + "userinfo")) {
			
		Member m = (Member) mes.getMentionedMembers().get(0);
		TextChannel ch = event.getChannel();
	
		String[] args = event.getMessage().getContentDisplay().replaceAll("  ", " ").split(" ");
		
		//User Daten
		OnlineStatus status = m.getOnlineStatus();
		String mention = m.getAsMention();
		String Nickname = m.getNickname();
		String name = event.getMessage().getMentionedUsers().get(0).getName();
		long id = m.getIdLong();
		OffsetDateTime joined = m.getTimeJoined();
		OffsetDateTime create = m.getTimeCreated();	
		EnumSet<Permission> perms = m.getPermissions();
		OffsetDateTime boosted = m.getTimeBoosted();
		
		if(Nickname == null) {
			Nickname = "Keiner";
		}
		if(boosted == null) {
			String keinnBoost = "Keine Boosts aktiv";
			
			if(args.length == 2) {
				ch.sendMessage(new EmbedBuilder() 
						.setFooter(Main.footer)
						.setColor(Color.BLUE)
						.setDescription("Member : " + name + "\n Nickname : " + Nickname + "\n Mention : " + mention + "\n ID : " + id + "\n Gejoint : " + joined + "\n Createt at : " + create + "\n Status : " +status + "\n Permissions : " + perms + "\n Boost: " + keinnBoost)
						
						.build()).queue();
			}
				
		}else {
			if(args.length == 2) {
				ch.sendMessage(new EmbedBuilder() 
						.setFooter(Main.footer)
						.setColor(Color.BLUE)
						.setDescription("Member : " + name + "\n Nickname : " + Nickname + "\n Mention : " + mention + "\n ID : " + id + "\n Gejoint : " + joined + "\n Createt at : " + create + "\n Status : " +status + "\n Permissions : " + perms + "\n Boost: " + boosted)
						
						.build()).queue();
			}
		}

		}
	}
}
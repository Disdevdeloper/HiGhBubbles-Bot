package listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.LiteSQL;

public class TempChannelsListen extends ListenerAdapter {

	

	public List<Long> tempch;
	public TempChannelsListen() {
		this.tempch = new ArrayList<Long>();
	}
	
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent e) {
		onJoin(e.getChannelJoined(),  e.getEntity());
		
	}
	
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent e) {
		onLeave(e.getChannelLeft());

		
	}
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent e) {
		onLeave(e.getChannelLeft());
		onJoin(e.getChannelJoined(), e.getEntity());
	}
	public void onJoin(VoiceChannel joined, net.dv8tion.jda.api.entities.Member member) {

		ResultSet set = LiteSQL.onQuery("SELECT channelid FROM tempchannels  WHERE guildid = " + joined.getGuild().getIdLong());
		
		try {
			if(set.next()) {
			long channelid = set.getLong("channelid");
			if(joined.getIdLong() == channelid) {
			Category cat = joined.getParent();
			VoiceChannel vc = cat.createVoiceChannel("Temp | " + ((net.dv8tion.jda.api.entities.Member) member).getEffectiveName()).complete();

			vc.getManager().setUserLimit(joined.getUserLimit()).queue();
			vc.getManager().setBitrate(joined.getBitrate()).queue();
	
			vc.getGuild().moveVoiceMember((net.dv8tion.jda.api.entities.Member) member, vc).queue();
			
			
			
			this.tempch.add(vc.getIdLong());
			}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

			
	
	}
	public void onLeave(VoiceChannel ch) {
	
		if(ch.getMembers().size() <= 0) {
			if(this.tempch.contains(ch.getIdLong())) {
				this.tempch.remove(ch.getIdLong());
				ch.delete().queue();;
			}
		}
	}
}
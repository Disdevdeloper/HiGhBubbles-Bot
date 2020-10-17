package commands.admin;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.LiteSQL;

public class TempChannels extends ListenerAdapter {


	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "tempchannel")) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Guild g = event.getGuild();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().split(" ");
			int length = args.length;
			
			//Hier gehts los
          	    if(m.hasPermission(Permission.MANAGE_CHANNEL)) {
          	    	
          	    	ResultSet set = LiteSQL.onQuery("SELECT channelid FROM tempchannels WHERE guildid = " + event.getGuild().getIdLong() + "");
          	    	
          	    	
          	    	try {
          				if(set.next()) {
          					long channelid = set.getLong("channelid");

              						ch.sendMessage("Tempchannels are deletet").queue();
                  					Main.jda.getVoiceChannelById(channelid).delete().queue();
                  					LiteSQL.onUpdate("DELETE FROM tempchannels WHERE guildid = " + event.getGuild().getIdLong());

          				}else {
          					g.createCategory("Tempchannel").queue(cat ->{
          	          	    	cat.createVoiceChannel("Join to create").queue(chan ->{
          	          	    		long idd = chan.getIdLong();
          	          	    		LiteSQL.onUpdate("INSERT INTO tempchannels(guildid, channelid) VALUES(" + g.getIdLong() +", " + idd  +")" );
          	          	    		ch.sendMessage("Tempchannel createt!").queue();
          	          	    	});
          	          	    		
          	          	    	});
      					}
          			} catch (SQLException e) {
          				e.printStackTrace();
          				
          			}
          	    	
          	    	
          	    
          	    }else
          	    	ch.sendMessage(Main.noPerm.build()).queue();
        }
    }
        
 }
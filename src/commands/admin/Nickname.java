package commands.admin;

import java.awt.Color;
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

public class Nickname extends ListenerAdapter {


	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "nick")) {
        	if(event.getMember().hasPermission(Permission.NICKNAME_MANAGE)) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Guild g = event.getGuild();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().replace(mes.getMentionedMembers().get(0).getEffectiveName(), "").split(" ");
			int length = args.length;
			List<Member> dm = mes.getMentionedMembers();
			
		
			
			 StringBuilder strbuild = new StringBuilder();
				
				for(int i = 2; i < length; i++) strbuild.append(args[i] + " ");
				
				String nick = strbuild.toString().trim();
				nick.replace(Main.prefix + "nick", "");
			
			//Hier gehts los
			if(args.length >= 2) {
				
				
				
				  
				  if(nick.contains("default")) {
					  g.modifyNickname(dm.get(0), null).queue();
					  ch.sendMessage("The User: " + dm.get(0).getAsMention() + " has none Nichname anymore!").queue();
				  }else {
				 g.modifyNickname(dm.get(0), nick).queue();
				 ch.sendMessage("The User: " + dm.get(0).getAsMention() + " has now the Nickname: " + nick).queue();
				  }
				
			}else
				ch.sendMessage(Main.Eingabe.build()).queue();
        	}
          	   
        }
    }
        
 }

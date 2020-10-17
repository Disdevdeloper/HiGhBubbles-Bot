package commands.admin;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DirektMessage extends ListenerAdapter {



	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "dm")) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().split(" ");
			int length = args.length;
			List<User> dm = mes.getMentionedUsers();
			
			//Hier gehts los
			if(m.hasPermission(Permission.MESSAGE_MANAGE)) {
				
          	   dm.get(0).openPrivateChannel().queue(dmm -> {
          		   
          		   
          		 StringBuilder strbuild = new StringBuilder();
 				
 				for(int i = 2; i < length; i++) strbuild.append(args[i] + " ");
 				
 				String dmmes = strbuild.toString().trim();
 				
 				
 				dmmes.replace(Main.prefix + "dm", "");
 				dmmes.replace(dm.get(0).getAsMention(), "");
 				
 				ch.sendMessage("The User: " + dm.get(0).getAsMention() + " has been send this DM: " + dmmes).queue();
 				
          		   dmm.sendMessage(new EmbedBuilder().setDescription(dmmes).setColor(Color_RANDOM).setTitle(":gift: Server DM :eyes:").setFooter("From Server: " + event.getGuild().getName(), event.getGuild().getIconUrl()).build()).queue();
          	   });
        }
    }
	}  
 }

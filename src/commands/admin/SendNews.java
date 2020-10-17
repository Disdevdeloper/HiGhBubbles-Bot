package commands.admin;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class SendNews extends ListenerAdapter {
    
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
    	String mes = event.getMessage().getContentRaw().replace(Main.prefix + "sendnews", "");
            
          if (event.getMessage().getContentRaw().startsWith(Main.prefix + "sendnews")) {
        	  if(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)){   	
        		  event.getMessage().delete().queue();	
              	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
    			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
        		  mes.replace(Main.prefix + "sendnews", "");
                      event.getChannel().sendMessage(new EmbedBuilder()  
                    		  		  
                     
                      .setTitle(event.getGuild().getName() + " |  Server News ")	  
                      .setColor(Color_RANDOM)
                      .setDescription(mes)
                      .setFooter("Server News")
                    .build()).queue();   
        		  
        	  }else
        		  event.getChannel().sendMessage(Main.noPerm .build()).queue();
        	  
        }
        	
    }
}
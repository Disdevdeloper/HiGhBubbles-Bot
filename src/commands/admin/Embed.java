package commands.admin;

import java.awt.Color;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Embed extends ListenerAdapter {
    
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        
    	String mes = event.getMessage().getContentRaw().replace(Main.prefix + "embed", "");
            
          if (event.getMessage().getContentRaw().startsWith(Main.prefix + "embed")) {
        	  if(event.getMember().hasPermission(Permission.MESSAGE_MANAGE)){   	
        		  event.getMessage().delete().queue();	
                      event.getChannel().sendMessage(new EmbedBuilder()  	  	  
                    		  
                      .setColor(Color.BLUE)
                      .setDescription(mes)
                      .setFooter("Message by " + event.getMember().getEffectiveName())
                    .build()).queue();   
        		  
        	  }else
        		  event.getChannel().sendMessage(Main.noPerm .build()).queue();
        	  
        }
        	
    }
}
package commands.admin;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import main.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Nuke extends ListenerAdapter {


	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "nuke")) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().split(" ");
			int length = args.length;
			
			if(m.hasPermission(Permission.MANAGE_CHANNEL)) {
			//Hier gehts los
          	Guild g = event.getGuild();
          	Category cat = ch.getParent();
          	
          	ch.sendMessage("Please wait a monment").queue();
          	
          	Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				
				
				@Override
				public void run() {
					timer.cancel();
					g.createCopyOfChannel(ch).queue(chan ->{
		          		chan.sendMessage("Channel are nuked!").queue();
		          		chan.sendMessage("https://tenor.com/view/explosion-explode-clouds-of-smoke-gif-17216934").queue();
		          		ch.delete().queue();
		          	});
					
				}
			}, 1*3000);
          	
          
          	
          	
          ;
        }else
        	ch.sendMessage(Main.noPerm.build().getDescription()).queue();
    }
}    
 }
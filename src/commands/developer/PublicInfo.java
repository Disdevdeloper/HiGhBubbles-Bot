package commands.developer;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PublicInfo extends ListenerAdapter {


	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "development")) {
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
          	    ch.sendMessage(new EmbedBuilder().setDescription(" \n [Java](https://www.java.com/de/download/)          [Jdk](https://www.oracle.com/java/technologies/javase-jdk15-downloads.html)          [Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-09/R/eclipse-inst-jre-mac64.dmg) \n \n [Saros](https://www.saros-project.org/)          [LiteSql](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc)         [Jda](https://github.com/DV8FromTheWorld/JDA) \n \n [Developerportal](https://discord.com/developers/applications)          [lavaplayer](https://github.com/sedmelluq/lavaplayer)").setTitle("Entwiklungsinfos:").setColor(Color_RANDOM).setFooter("If you don't understand anything, this is not a proplem :). The people who know it know the terms").build()).queue();  
        }
    }
        
 }

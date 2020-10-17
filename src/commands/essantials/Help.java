package commands.essantials;

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

public class Help extends ListenerAdapter {


	@SuppressWarnings("unused")
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
                
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "help")) {
        	//Angaben
        	TextChannel ch = event.getChannel();
        	Member m = event.getMember();
        	Guild g = event.getGuild();
        	Message mes = event.getMessage();
        	List<Color> ColorList = Arrays.asList(Color.GREEN,Color.BLUE, Color.CYAN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
			Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
			String[] args = event.getMessage().getContentDisplay().split(" ");
			int length = args.length;
			 StringBuilder strbuild = new StringBuilder();
																										//1 	2
			for(int i = 2; i < length; i++) strbuild.append(args[i] + " "); //i = anzahl von suvfix: disdev.ichbincool
				
			String argsstring = strbuild.toString().trim();
			
			//Hier gehts los
          	 ch.sendMessage(new EmbedBuilder()
          			 .setFooter(Main.footer)
          			 .setColor(Color_RANDOM)
          			 .setDescription(":question:Thats my help menü: \n \n:newspaper: Informations: \n .help >> Show this menü \n .serverinfo >> Show all Infosmations about the Server \n .unserinfo @User >> Show all Informations about an User \n .ping >> Pong \n .devlopment >> Show all development Infos\n  \n :partying_face: Fun:\n .meme >> Send a meme \n .coinflip >> Flip a coin \n .say <Message> >> The bot sends your Message \n .embed <Message> >> The Bot sends your Message in an Embed \n \n :police_officer: Moderation: \n .tempchannel >> Create a Join Me Channel \n .clear <amout> >> Clears messages \n .ban @User [Desciption] >> Bans a member \n .kick @User [Description] >> Kick a member \n .sendnews <Message> >> Send a Servernews \n .dm @User <Description> >> Send a DM to a User \n .nuke >> Nukes a channel  \n .reactrole #channel <message id> <emote> @Role >> Create a Reactrole \n .nichname @User <Description> >> Nick a User \n .addrole [hex] <Name> >> Create a Role \n .stats [delte] >> Create/Delete a Stats Catecory \n \n \n <> Must there | [] optional")
          			 .build()).queue();       
        }
    }
        
 }

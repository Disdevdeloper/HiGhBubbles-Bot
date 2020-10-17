package commands.fun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Meme extends ListenerAdapter
{
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
	
		if(event.getMessage().getContentRaw().equalsIgnoreCase(Main.prefix + "meme")) {
    
            String title = "";
            String imageurl = "";
            String postlink = "";
            boolean isNsfw = false;
            boolean isSpoiler = false;
            try {
                while(true) {
                    URL memeurl = new URL("https://meme-api.herokuapp.com/gimme");
                    BufferedReader bf = new BufferedReader(new InputStreamReader(memeurl.openConnection().getInputStream()));
                    String input = bf.readLine();
                    title = input.substring(input.indexOf("\"title\":") + "\"title\":\"".length(), input.indexOf("\",\"url\":"));
                    imageurl = input.substring(input.indexOf("\"url\":\"") + "\"url\":\"".length(), input.indexOf("\",\"nsfw\":"));
                    postlink = input.substring(input.indexOf("\"postLink\":\"") + "\"postLink\":\"".length(), input.indexOf("\",\"subreddit\":"));
                    if(input.substring(input.indexOf("\"nsfw\":") + "\"nsfw\":".length(), input.indexOf(",\"spoiler\":")).equalsIgnoreCase("true")) {
                        isNsfw = true;
                    } else {
                        isNsfw = false;
                    }
                    if(input.substring(input.indexOf(",\"spoiler\":") + ",\"spoiler\":".length(), input.indexOf("}")).equalsIgnoreCase("true")) {
                        isSpoiler = true;
                    } else {
                        isSpoiler = false;
                    }
                    if(isNsfw == false && isSpoiler == false) {
                        break;
                    } else {
                        continue;
                    }
                    //If you are ok with both spoilers and nsfw memes then replace lines 31-45 with "break;"
                }
                event.getChannel().sendMessage(new EmbedBuilder()
                        .setTitle(title)
                        .setImage(imageurl)
                        .setFooter(postlink)
                        .build()).queue();
            } catch(Exception e) {
                event.getChannel().sendMessage("**Something went wrong!** Please try again later!").queue();
            }
         

        
    }
    }
   
}
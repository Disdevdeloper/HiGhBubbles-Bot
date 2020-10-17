package commands.admin.reactroles;

import java.util.List;

import main.Main;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import sql.LiteSQL;

public class ReactRoleCommand extends ListenerAdapter {

	
	
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    
    	if (event.getMessage().getContentRaw().startsWith(Main.prefix + "reactrole")) {
    		if(event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
    	
    	String[] args = event.getMessage().getContentDisplay().replaceAll("  ", " ").split(" ");
    	
    	Message message = event.getMessage();
    	
    	if(args.length >= 5) {
    	 List<TextChannel> channel = event.getMessage().getMentionedChannels();
    	 List<Role> roles = message.getMentionedRoles();
    	 
    	 
    	 if(!channel.isEmpty() && !roles.isEmpty()) {
    		 TextChannel tc = channel.get(0);
    		 Role role = roles.get(0);
    		 
    		 String messageIDString = args[2];
    		 try {
    			 Long messageId = Long.parseLong(messageIDString);
    			 
    			String emote = args[3];
    		
    				tc.addReactionById(messageId, emote).queue();
    				
    				LiteSQL.onUpdate("INSERT INTO reactroles(guildid, channelid, messageid, emote, rollenid) VALUES(" + event.getChannel().getGuild().getIdLong() + ", " + tc.getIdLong() + ", " + messageId +", '" + emote + "', " + role.getIdLong()  + ")");
    				
    			message.delete().queue();
    			 
    		 } catch (NumberFormatException e) { 
    			 
    		 			}
    	 			}
    			}else {
    				event.getChannel().sendMessage("Bitte schau nochmal in e.help nach!").queue();;
    			}
    		}else
    			event.getChannel().sendMessage(Main.noPerm .build()).queue();
    	}
    }
}
package commands.fun;

import java.awt.Color;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Say extends ListenerAdapter {
	

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
    	
           	
    	
        if (event.getMessage().getContentRaw().startsWith(Main.prefix + "say")) {
        	if (event.getMember().hasPermission(Permission.MESSAGE_MANAGE)) {
                if(!(event.getMember().hasPermission(Permission.MESSAGE_MENTION_EVERYONE))) {
                	if(event.getMessage().getContentRaw().replace(Main.prefix + "say","").contains("@everyone")) {
            			event.getMessage().getContentRaw().replace("@everyone", "");
            			event.getMessage().delete().queue();
            			event.getChannel().sendMessage(new EmbedBuilder() .setDescription("You dont have the permission to ping @everyone!").setColor(Color.RED).setFooter(Main.footer).build()).queue();
            		}else {

                		event.getChannel().sendMessage(event.getMessage().getContentRaw().replace(Main.prefix + "say","")).queue();
                        event.getMessage().delete().queue();
            		}
                }else {

            		event.getChannel().sendMessage(event.getMessage().getContentRaw().replace(Main.prefix + "say","")).queue();
                    event.getMessage().delete().queue();
                }

        		}else
        			event.getChannel().sendMessage(Main.noPerm.build()).queue();

            }

        }

    }

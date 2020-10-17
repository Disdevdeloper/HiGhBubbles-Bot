package commands.fun;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import main.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Coinflip extends ListenerAdapter {
	

    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMessage().getContentRaw().equalsIgnoreCase(Main.prefix + "coinflip")) {
        	List<String> Statuss = Arrays.asList("Head", "Number");
        	Random r = new Random();

           int randomitem = r.nextInt(Statuss.size());
           String Ergb = Statuss.get(randomitem);
        	List<Color> ColorList = Arrays.asList(Color.GREEN, Color.CYAN, Color.BLUE, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
        	Color Color_RANDOM = ColorList.get(new Random().nextInt(ColorList.size()));
        	e.getChannel().sendMessage("https://tenor.com/view/coin-toss-coin-toss-gif-5017733").complete().delete().queueAfter(5500, TimeUnit.MILLISECONDS);
        	e.getChannel().sendMessage(new EmbedBuilder()
        			.setFooter(Main.footer)
        			.setColor(Color_RANDOM)
        			.setDescription("Wait...")
        			.build()).queue(edit -> {
        				Timer timer = new Timer();
        				timer.schedule(new TimerTask() {
        					@Override
        					public void run() {
        						timer.cancel();
        						List<Color> ColorListt = Arrays.asList(Color.GREEN, Color.BLACK, Color.CYAN, Color.BLUE, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.WHITE, Color.YELLOW, Color.decode("#0b0064"));
                				Color Color_RANDOMM = ColorListt.get(new Random().nextInt(ColorListt.size()));
                				edit.editMessage(new EmbedBuilder()
                						.setDescription("Its: " +Ergb)
                						.setColor(Color_RANDOMM)
                						.setFooter("Coinflip")
                						.build()).queue();
                				timer.cancel();
        					}
        				}, 5*1000);
        				
        			});

            }

        }

    }

package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;

import main.Main;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.internal.requests.restaction.PermissionOverrideActionImpl;
import sql.LiteSQL;

public class StatschannelCommand extends ListenerAdapter {

	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		if (event.getMessage().getContentRaw().startsWith(Main.prefix + "stats")) {
			Member m = event.getMember();
			Message message = event.getMessage();
			TextChannel channel = event.getChannel();

			if (m.hasPermission(Permission.ADMINISTRATOR)) {
				String[] args = message.getContentDisplay().split(" ");

				Guild guild = channel.getGuild();
				ResultSet set = LiteSQL.onQuery("SELECT * FROM statchannels WHERE guildid = " + guild.getIdLong());

				try {
					if (!set.next()) {
						Category category = guild.createCategory("Stats").complete();
						category.getManager().setPosition(1).queue();

						PermissionOverride override = new PermissionOverrideActionImpl(category.getJDA(), category,
								category.getGuild().getPublicRole()).complete();

						category.getManager()
								.putPermissionOverride(override.getRole(), EnumSet.of(Permission.VOICE_CONNECT), null)
								.queue();

						LiteSQL.onUpdate("INSERT INTO statchannels(guildid, categoryid) VALUES(" + guild.getIdLong()
								+ ", " + category.getIdLong() + ")");

						fillCategory(category);
					} else {
						long categoryid = set.getLong("categoryid");
						channel.sendMessage("Catecory updated.").queue();
						Category cat = guild.getCategoryById(categoryid);

						if (args.length == 2) {
							if (args[1].equalsIgnoreCase("delete")) {
								LiteSQL.onUpdate("DELETE FROM statchannels WHERE guildid = " + guild.getIdLong());

								cat.getChannels().forEach(chan -> {
									chan.delete().complete();
								});
								cat.delete().queue();

								return;
							}
						}

						cat.getChannels().forEach(chan -> {
							chan.delete().complete();
						});

						fillCategory(cat);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void fillCategory(Category cat) {
		List<Member> members = cat.getGuild().getMembers();
		int Bots = 0;
		for (Member memb : members) {
				if (memb.getUser().isBot()) {	
				Bots++;
			}
		}
	
		SimpleDateFormat df2 = new SimpleDateFormat("MM.dd.YYYY");
		cat.createVoiceChannel("BOT ONLINE").queue();
		cat.createVoiceChannel("Bots: " + Bots).queue();
		cat.createVoiceChannel("Date: " + df2.format(Calendar.getInstance().getTime())).queue();

		
		cat.createVoiceChannel("Server Members: " + members.size()).queue();
		
		
		
		int online = 0;

		for (Member memb : members) {
			if (memb.getOnlineStatus() != OnlineStatus.OFFLINE) {
				if (!memb.getUser().isBot()) {
					online++;
				}
			}
		}
		cat.createVoiceChannel("Online User: " + online).queue();
		

		PermissionOverride override = new PermissionOverrideActionImpl(cat.getJDA(), cat,
				cat.getGuild().getPublicRole()).complete();

		// System.out.println("OVerride: " + (override == null ? "NULL" :
		// override.toString()));

		cat.getManager().putPermissionOverride(override.getRole(), null, EnumSet.of(Permission.VOICE_CONNECT)).queue();
	}

	public static void sync(Category cat) {
		cat.getChannels().forEach(chan -> {
			chan.getManager().sync().queue();
		});
	}

	public static void updateCategory(Category cat) {
		

		if (cat.getChannels().size() == 5) {
			sync(cat);
			List<GuildChannel> channels = cat.getChannels();			
		
			SimpleDateFormat df2 = new SimpleDateFormat("MM.dd.YYYY");

			channels.get(2).getManager().setName("Date " + df2.format(Calendar.getInstance().getTime())).queue();
			
			List<Member> members = cat.getGuild().getMembers();
			int Bots = 0;
			for (Member memb : members) {
					if (memb.getUser().isBot()) {	
					Bots++;
				}
			}
			int online = 0;

			for (Member memb : members) {
				if (memb.getOnlineStatus() != OnlineStatus.OFFLINE) {
					if (!memb.getUser().isBot()) {
						online++;
					}
				}
			}
			channels.get(3).getManager().setName("Server Members: " + members.size()).queue();
			channels.get(4).getManager().setName("Online User: " + online).queue();
			channels.get(1).getManager().setName("Bots: " + Bots).queue();
		}
	}

	public static void checkStats() {
		
		Main.jda.getGuilds().forEach(guild -> {
			ResultSet set = LiteSQL.onQuery("SELECT categoryid FROM statchannels WHERE guildid = " + guild.getIdLong());

			try {
				if (set.next()) {
					long catid = set.getLong("categoryid");
					StatschannelCommand.updateCategory(guild.getCategoryById(catid));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public static void onStartUP() {
		System.out.println("Startup");
		Main.jda.getGuilds().forEach(guild -> {
			ResultSet set = LiteSQL.onQuery("SELECT categoryid FROM statchannels WHERE guildid = " + guild.getIdLong());

			try {
				if (set.next()) {
					System.out.println("Next");
					long catid = set.getLong("categoryid");
					Category cat = guild.getCategoryById(catid);

					cat.getChannels().forEach(chan -> {
						chan.delete().complete();
						
					});
					

					fillCategory(cat);
				}else
					System.out.println("Fehler");

				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});
	}

	public static void onShutdown() {
		Main.jda.getGuilds().forEach(guild -> {
			ResultSet set = LiteSQL.onQuery("SELECT categoryid FROM statchannels WHERE guildid = " + guild.getIdLong());

			try {
				if (set.next()) {
					long catid = set.getLong("categoryid");
					Category cat = guild.getCategoryById(catid);

					cat.getChannels().forEach(chan -> {
						chan.delete().complete();
					});

					VoiceChannel offline = cat.createVoiceChannel("BOT OFFLINE").complete();
					PermissionOverride override = new PermissionOverrideActionImpl(cat.getJDA(), offline,
							cat.getGuild().getPublicRole()).complete();

					offline.getManager()
							.putPermissionOverride(override.getRole(), null, EnumSet.of(Permission.VOICE_CONNECT))
							.queue();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

	}
}
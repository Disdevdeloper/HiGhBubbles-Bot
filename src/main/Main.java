package main;

import java.awt.Color;
import java.util.ArrayList;

import commands.StatschannelCommand;
import commands.admin.Ban;
import commands.admin.ClearCommand;
import commands.admin.DirektMessage;
import commands.admin.Embed;
import commands.admin.Kick;
import commands.admin.Nickname;
import commands.admin.Nuke;
import commands.admin.RolleErstellen;
import commands.admin.SendNews;
import commands.admin.TempChannels;
import commands.admin.reactroles.ReactRoleCommand;
import commands.admin.reactroles.ReactRoleListener;
import commands.developer.PublicInfo;
import commands.developer.Shutdown;
import commands.developer.Zugriff;
import commands.essantials.Help;
import commands.essantials.Ping;
import commands.essantials.ServerInfo;
import commands.essantials.UserInfo;
import commands.fun.Coinflip;
import commands.fun.Meme;
import commands.fun.Say;
import listener.EmptyCommandMessage;
import listener.LeavAndJoin;
import listener.TempChannelsListen;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.TextChannel;
import sql.LiteSQL;
import sql.SQLManger;

public class Main {
	public static JDA jda;
	public static JDABuilder builder;
	public static ArrayList<Long> OwnerIds = new ArrayList<Long>();
	public static String prefix = ".";
	public static EmbedBuilder noPerm = new EmbedBuilder().setDescription("You dont have the Permission to execute this command!").setColor(Color.YELLOW).setFooter(Main.footer);
	public static String footer = "Made by DisDevDeveloper for HiGhBubbles";
	public static long id = 747028201745940491l;
	public static EmbedBuilder Eingabe = new EmbedBuilder().setDescription("Please check .help").setColor(Color.YELLOW).setFooter(footer);

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		LiteSQL.connect();
		SQLManger.onCreate();

		
		builder = new JDABuilder(AccountType.BOT);

		
		builder.setToken("NzY3MDkxNzYzNzY4MDY2MDU4.X4s34A.9IpZHRHzUDZnus_NzOQaeRw3XzQ");
		builder.setAutoReconnect(true);
		
		builder.setStatus(OnlineStatus.ONLINE);
		//Register
		
		builder.addEventListeners(new Ping());
		builder.addEventListeners(new ServerInfo());
		builder.addEventListeners(new readyListener());
		builder.addEventListeners(new UserInfo());
		builder.addEventListeners(new LeavAndJoin());
		builder.addEventListeners(new ReactRoleCommand());
		builder.addEventListeners(new ReactRoleListener());
		builder.addEventListeners(new ClearCommand());
		builder.addEventListeners(new Ban());
		builder.addEventListeners(new Nuke());
		builder.addEventListeners(new DirektMessage());
		builder.addEventListeners(new Nickname());
		builder.addEventListeners(new Meme());
		builder.addEventListeners(new Kick());
		builder.addEventListeners(new TempChannels());
		builder.addEventListeners(new TempChannelsListen());
		builder.addEventListeners(new Say());
		builder.addEventListeners(new SendNews());
		builder.addEventListeners(new Embed());
		builder.addEventListeners(new StatschannelCommand());
		builder.addEventListeners(new Zugriff());
		builder.addEventListeners(new Coinflip());
		builder.addEventListeners(new RolleErstellen());
		builder.addEventListeners(new PublicInfo());
		builder.addEventListeners(new Shutdown());
		builder.addEventListeners(new EmptyCommandMessage());
		builder.addEventListeners(new Help());
		
		TimerMainSec.runen();
		try {
			jda = builder.build();
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public static void Fehler(String Code, TextChannel channel) {
		channel.sendMessage(new EmbedBuilder() .setDescription("An unexpectet Error occurred.  Errorcode: " + Code).setColor(Color.RED).setFooter(Main.footer).setTitle(":x: Error :x:").build()).queue();
		
	}
	static int next = 30;
	public static boolean hasStarted = false;
	public static void onSecond() {
		//System.out.println("Next: " + next);
		
		if(next%5 == 0) {
			if(hasStarted == false) {
				hasStarted = true;
				StatschannelCommand.onStartUP();
			}
			
			
			
			
			if(next == 0) {
				next = 60;
				
				StatschannelCommand.checkStats();
			}
			else {
				next--;
			}
		}else {
			next--;
		}
	}
		

}

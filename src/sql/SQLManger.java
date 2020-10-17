package sql;

public class SQLManger {

	public static void onCreate() {
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS reactroles (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER, messageid INTEGER, emote VARCHAR, rollenid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS tempchannels(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, channelid INTEGER)");
		LiteSQL.onUpdate("CREATE TABLE IF NOT EXISTS statchannels(id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, guildid INTEGER, categoryid INTEGER)");
		
	}
	
}
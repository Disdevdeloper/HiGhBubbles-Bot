package main;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class readyListener extends ListenerAdapter {

    public void onReady(ReadyEvent event) {

        String out = "\nDer Bot läuft auf folgenden Servern: \n";

        for (Guild g : event.getJDA().getGuilds() ) {
            out += g.getName() + " (" + g.getId() + ") \n";
        }
    	Main.jda.getPresence().setActivity(Activity.streaming(""+ event.getJDA().getUsers().size() + " are on the Discord Server", "https://www.fiverr.com/disdevdeveloper?up_rollout=true"));
        System.out.println(out);


    }
}
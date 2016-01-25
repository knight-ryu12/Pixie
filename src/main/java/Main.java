import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;

import java.util.Properties;

/**
 * Created by chroma on 16/01/19.
 */
public class Main {
    public static void main(String[] args) throws Throwable{
        BotLauncher helper = new BotLauncher();
        Configuration configuration = new Configuration.Builder()
                .setServerHostname(helper.hostname)
                .setName(helper.botname)
                .addAutoJoinChannel(helper.channel)
                //TODO:Add listener here
                //.addListener(new NumberGame())
                .addListener(new Greet())
                .buildConfiguration();
        PircBotX bot = new PircBotX(configuration);
        bot.startBot();
    }
}

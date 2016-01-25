import java.io.IOException;
import java.util.Properties;

/**
 * Created by chroma on 16/01/25.
 */
public class BotLauncher {
    public String hostname,botname,channel;
    //String hostname,botname,channel;
    Properties properties;
    BotLauncher() {
        try {
            properties = new Properties();
            properties.load(getClass().getResourceAsStream("/bot.properties"));
            hostname = properties.getProperty("bot.hostname");
            botname = properties.getProperty("bot.nickname");
            channel = properties.getProperty("bot.channel");
        } catch (IOException e) {
            System.err.println("Error while reading config.");
            System.err.println("STACKTRACE");
            e.printStackTrace();
            System.err.println("Done. aborting Launching bot.");
            System.exit(1);
        }
    }
}

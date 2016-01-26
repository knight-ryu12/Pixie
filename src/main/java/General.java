import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by chroma on 16/01/25.
 */
public class General extends ListenerAdapter {
    public String PREFIX = "?";
    public void onMessage(MessageEvent event) {
        if(event.getMessage().startsWith(PREFIX + "help")) {
            event.getUser().send().notice("HEY! I am " + event.getBot().getNick() + "!");
            event.getUser().send().notice("registered command are: SetChannelGreet");
        }
        if(event.getMessage().startsWith(PREFIX + "join")) {

        }
    }
}

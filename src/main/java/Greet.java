import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by chroma on 16/01/24.
 */
public class Greet extends ListenerAdapter {
    public String greetMsg;
    public String PREFIX = "?";
    String[] Args = new String[4];
    SQL Sqlhandler = new SQL();
    public void onJoin(JoinEvent event) {
        if (event.getUser().getNick().equals(event.getBot().getNick())) {
        } else {
            greetMsg = Sqlhandler.getGreetMessage(event.getChannel().getName());
            if(greetMsg.equals("")) {
                event.getUser().send().notice("Welcome to " + event.getChannel().getName() + "!");
            } else {
                event.getUser().send().notice(greetMsg);
            }

        }
    }
    public void onMessage(MessageEvent event) {
        if(Sqlhandler)
        if(event.getMessage().startsWith(PREFIX + "SetChannelGreet")) {
            if (event.getMessage().length() > 16) {
                Args = event.getMessage().split(" "); //TODO: make Args detection (How many of args)
                if (!Sqlhandler.getGreetMessage(event.getChannel().getName()).equals("")) {
                    Sqlhandler.setGreetMessage(Args[1],Args[2]);
                } else {
                    Sqlhandler.addGreetMessage(Args[1],Args[2]);
                }
            } else {
                event.getUser().send().notice("Usage: " + PREFIX + "SetChannelGreet "+ "<channel> <Message>");
            }
        }
    }
}

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by chroma on 16/01/19.
 */
public class NumberGame extends ListenerAdapter {
    public String PREFIX = "?";
    api api = new api();
    private boolean GameFlag;
    private int randomNo,length,intarg;
    private String arg;
    public void onMessage(MessageEvent event) {
        if(event.getMessage().startsWith(PREFIX + "NGStart")) {
            
            event.getChannel().send().message(event.getUser().getNick() + " Started Game! Use !nguess for join!");
            randomNo = api.showRandomInteger(0,99);
            GameFlag = true;
        }
        if(event.getMessage().startsWith(PREFIX + "nguess")) {
            length = event.getMessage().length();
            if(GameFlag) {
                if (length < 7) {
                    arg = event.getMessage().substring(8);
                    intarg = Integer.parseInt(arg);
                    if (intarg < randomNo) {
                        event.getChannel().send().message(event.getUser().getNick() + " guessed " + arg + " but it's too low.");

                    } else if (intarg > randomNo) {
                        event.getChannel().send().message(event.getUser().getNick() + " guessed " + arg + " but it's too high.");
                    } else {
                        event.getChannel().send().message(event.getUser().getNick() + " guessed collect Number!");
                        event.getChannel().send().message("Game Stopped.");
                        GameFlag = false;
                    }
                }
            } else {
                event.getChannel().send().message("Currently no game is running :/");
            }
        }
    }
}
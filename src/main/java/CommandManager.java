import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class CommandManager {

    private final List<iCommand> commands = new ArrayList<>();

    public CommandManager() {

    }

    private void addCommand(iCommand cmd) {
        boolean commandName = this.commands.stream().anyMatch((it -> it.getName().equalsIgnoreCase(cmd.getName())));
        if (commandName) {
            throw new IllegalArgumentException("Command is already in the list.");
        }

        commands.add(cmd);

    }

    public List<iCommand> getCommands(){
        return commands;
    }

    @Nullable
    public iCommand getCommand(String search){
        for(iCommand cmd : this.commands){
            if(cmd.getName().equals(search.toLowerCase())){
                return cmd;
            }
        }
        return null;
    }

    void handle(GuildMessageReceivedEvent event, String prefix){
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(prefix),"")
                .split("\\s+");
        String invoke = split[0].toLowerCase();
        iCommand cmd = this.getCommand(invoke);

        if(cmd != null){
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

}
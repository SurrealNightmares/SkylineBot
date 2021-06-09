import net.dv8tion.jda.api.JDA;

public class PingCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx){
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue((ping) -> ctx.getChannel().sendMessageFormat("Reset ping: %s ms\nWS ping %s ms",
                ping, jda.getGatewayPing()).queue()
        );
    }

    @Override
    public String getName(){
        return "ping";
    }

    @Override
    public String getHelp(){
        return "Shows current ping of bot to Discord servers.";
    }
}

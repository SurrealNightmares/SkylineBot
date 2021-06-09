import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class LeaveCommand implements ICommand {

    @Override
    public void handle(CommandContext ctx){
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        final AudioManager audioManager = ctx.getGuild().getAudioManager();

        if(memberVoiceState.inVoiceChannel()){
            channel.sendMessage("Leaving channel!").queue();


            audioManager.closeAudioConnection();

            return;
        }

    }
    @Override
    public String getName(){
        return "leave";
    }

    @Override
    public String getHelp() {
        return "Makes bot leave voice channel";
    }

}

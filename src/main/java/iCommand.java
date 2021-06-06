public interface iCommand {

    void handle(CommandContext ctx);

    String getName();

    String getHelp();


}

package commandService.userCommands;

import commandService.BaseCommand;
import commandService.CommandService;
import network.CommandResponse;

/**
 * <b>help : available commands info</b>
 */
public class Help extends BaseCommand {
    public static final String name = "help";
    public static final String description = ": available commands info";
    private final CommandService commandService = new CommandService();
    private CommandResponse response;
    public Help(){
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute(){
        response = new CommandResponse(commandService.printCommands(), 0);
    }
}

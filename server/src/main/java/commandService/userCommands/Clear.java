package commandService.userCommands;

import commandService.BaseCommand;
import network.CommandResponse;

/**
 * <b>clear: clear the collection</b>
 */
public class Clear extends BaseCommand {
    public static final String name = "clear";
    public static final String description = ": clear the collection";
    private CommandResponse response;

    public Clear() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
        repository.clearCollection();
        response = new CommandResponse("Finished!", 0);
    }
}
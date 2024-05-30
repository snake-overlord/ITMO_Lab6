package commandService.userCommands;

import commandService.BaseCommand;
import network.CommandResponse;

/**
 * <b>head : output the first element of the collection</b>
 */
public class Head extends BaseCommand {
    public static final String name = "head";
    public static final String description = ": output the first element of the collection";
    private CommandResponse response;
    public Head() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
            if(repository.getVector().isEmpty()){
                response = new CommandResponse("Collection is empty!", 0);
            }
            else{
                response = new CommandResponse((repository.getVector().elementAt(0)).toString(), 0);
            }
    }
}

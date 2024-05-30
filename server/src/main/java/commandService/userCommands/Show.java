package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

/**
 * <b>show : shows all elements of the collection</b>
 */
public class Show extends BaseCommand {
    public static final String name = "show";
    public static final String description = ": shows all elements of the collection";
    private CommandResponse response;
    public Show() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {
        StringBuilder builder = new StringBuilder();
        for(Organization x: this.repository.getVector()){
            builder.append(x.toString());
        }
        response = new CommandResponse(builder.toString(), 0);
        }

    }


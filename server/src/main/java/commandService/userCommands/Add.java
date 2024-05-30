package commandService.userCommands;
import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

/**
 * <b>add {element} : add a new element to the collection</b>
 */
public class Add extends BaseCommand<Organization>{
    public static final String name = "add";
    public static final String description = "{element} : add a new element to the collection";
    private Organization org;
    private CommandResponse response;


    public Add() {
        super(name, description);
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    @Override
    public void setOrg(Organization org){
        this.org = org;
    }

    public void execute() {
        repository.addItem(org);
        response = new CommandResponse("Element added!", 0);
    }


}

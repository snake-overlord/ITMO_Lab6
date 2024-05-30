package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Comparator;

/**
 * <b>add_if_max {element} : add a new element to the collection, if value {employee count} is max.</b>
 */
public class AddIfMax extends BaseCommand<Organization>{
    public static final String name = "add_if_max";
    public static final String description = "{element} : add a new element to the collection, if value {employee count} is max.";
    private Organization org;
    private CommandResponse response;

    public AddIfMax() {
        super(name, description);
    }
    @Override
    public void setOrg(Organization org){
        this.org = org;
    }
    @Override
    public CommandResponse getResponse() {
        return response;
    }

    public void execute() {

        long maxEmployees = repository.getVector().stream().max(Comparator.comparing(Organization::getEmployeesCount)).get().getEmployeesCount();

        if(org.getEmployeesCount()>maxEmployees)
                repository.addItem(org);

        response = new CommandResponse("Finished!", 0);
    }
}

package commandService.userCommands;

import commandService.BaseCommand;
import models.Organization;
import network.CommandResponse;

import java.util.Comparator;

/**
 * <b>add_if_min {element} : add a new element to the collection, if value {employee count} is min.</b>
 */
public class AddIfMin extends BaseCommand<Organization>{
    public static final String name = "add_if_min";
    public static final String description = "{element} : add a new element to the collection, if value {employee count} is min.";
    private Organization org;
    private CommandResponse response;

    public AddIfMin() {
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
        long minEmployees = repository.getVector().stream().min(Comparator.comparing(Organization::getEmployeesCount)).get().getEmployeesCount();

        if(org.getEmployeesCount()<minEmployees)
            repository.addItem(org);
        response = new CommandResponse("Finished!", 0);
    }
}
